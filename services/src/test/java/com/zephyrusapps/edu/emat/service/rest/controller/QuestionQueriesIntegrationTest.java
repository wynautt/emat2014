package com.zephyrusapps.edu.emat.service.rest.controller;

import com.zephyrusapps.edu.emat.service.operations.CreateQuestionOp;
import com.zephyrusapps.edu.emat.service.operations.ViewQuestionOp;
import com.zephyrusapps.edu.emat.service.rest.controller.fixture.DataFixture;
import com.zephyrusapps.edu.emat.service.rest.controller.fixture.RestFixture;
import com.zephyrusapps.edu.emat.service.services.IQuestionServices;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class QuestionQueriesIntegrationTest {

    MockMvc mockMvc;

    @InjectMocks
    QuestionController controller;

    @Mock
    IQuestionServices questionServices;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

        doNothing().when(questionServices).createQuestion(any(CreateQuestionOp.class));
        when(questionServices.viewQuestion(any(ViewQuestionOp.class))).thenReturn(DataFixture.exMath2012Phase1Question1());
    }

    @Test
    public void thatViewQuestionUsesHttpOK() throws Exception {
        this.mockMvc.perform(
                get("/exam/{course}/{year}/{phase}/question/{number}","MatematicaA12", 2012, "1f", "1")
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void thatViewQuestionRendersCorrectly() throws Exception {
        this.mockMvc.perform(
                get("/exam/{course}/{year}/{phase}/question/{number}","MatematicaA12", 2012, "1f", "1")
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.year").value(2012))
                .andExpect(jsonPath("$.course").value("MatematicaA12"))
                .andExpect(jsonPath("$.phase").value("1f"))
                .andExpect(jsonPath("$.number").value("1"))
                .andExpect(jsonPath("$.text").value("dummy question 1"))
                .andExpect(jsonPath("$.text").value("dummy question 1"))
                .andExpect(jsonPath("$.themes[0]").value("Prob"))
                .andExpect(jsonPath("$.themes[1]").value("Succ"))
                .andExpect(jsonPath("$.answers[0]").value("10"))
                .andExpect(jsonPath("$.answers[1]").value("20"))
                .andExpect(jsonPath("$.answers[2]").value("30"))
                .andExpect(jsonPath("$.answers[3]").value("40"));
    }

    @Test
    public void thatQuestionCanBeCreated() throws Exception {
        this.mockMvc.perform(
                post("/exam/{course}/{year}/{phase}/question/create", "MatematicaA12", 2012, "1f")
                    .content(RestFixture.exQuestion1())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
