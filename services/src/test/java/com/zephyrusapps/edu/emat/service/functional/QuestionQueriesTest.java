package com.zephyrusapps.edu.emat.service.functional;

import com.zephyrusapps.edu.emat.service.rest.controller.fixture.RestFixture;
import com.zephyrusapps.edu.emat.service.rest.domain.Question;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

public class QuestionQueriesTest {

    static HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        return headers;
    }

    @Test
    public void thatQuestionCanBeQueried() {
        RestTemplate template = new RestTemplate();

        ResponseEntity<Question> entity = template.getForEntity(
                "http://localhost:8080/api/question/7",
                Question.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());

        Question q = entity.getBody();

        assertEquals(2012, q.getYear());
    }

    @Test
    public void thatQuestionCanBeCreated() {
        RestTemplate template = new RestTemplate();

        HttpEntity<String> requestEntity = new HttpEntity<String>(RestFixture.exQuestion2012_1_1(), getHeaders());

        ResponseEntity<Void> entity = template.postForEntity(
                "http://localhost:8080/api/question/create",
                requestEntity,
                Void.class);

        assertEquals(HttpStatus.CREATED, entity.getStatusCode());
    }
}
