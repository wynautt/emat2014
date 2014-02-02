package com.zephyrusapps.edu.emat.service.rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class QuestionQueriesIntegrationTest {

    MockMvc mockMvc;

    @InjectMocks
    QuestionQueriesController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void thatViewQuestionUsesHttpOK() throws Exception {
        this.mockMvc.perform(
                get("/emat/{id}", "1.1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void thatViewQuestionRendersCorrectly() throws Exception {
        this.mockMvc.perform(
                get("/emat/{id}", "1.1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.year").value(2012));
    }
}
