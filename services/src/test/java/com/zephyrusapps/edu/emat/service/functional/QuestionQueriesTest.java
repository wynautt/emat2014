package com.zephyrusapps.edu.emat.service.functional;

import com.zephyrusapps.edu.emat.service.rest.controller.fixture.RestFixture;
import com.zephyrusapps.edu.emat.service.rest.domain.QuestionData;
import junit.framework.Assert;
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

        ResponseEntity<QuestionData> entity = template.getForEntity(
                "http://localhost:8080/api/exam/MatematicaA12/2012/1f/2",
                QuestionData.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());

        QuestionData q = entity.getBody();

        assertEquals(2012, q.getYear());
        assertEquals("1f", q.getPhase());
        assertEquals("2", q.getNumber());
        assertEquals("dummy question", q.getText());
    }

    @Test
    public void thatExamCanBeCreated() {
        RestTemplate template = new RestTemplate();

        HttpEntity<String> requestEntity = new HttpEntity<String>(RestFixture.exExamMath2012Phase1(), getHeaders());

        ResponseEntity<Void> entity = template.postForEntity(
                "http://localhost:8080/api/exam/create",
                requestEntity,
                Void.class);

        assertEquals(HttpStatus.CREATED, entity.getStatusCode());
    }

    @Test
    public void thatQuestionCanBeCreated() {
        RestTemplate template = new RestTemplate();

        HttpEntity<String> requestEntity = new HttpEntity<String>(RestFixture.exQuestion2(), getHeaders());

        ResponseEntity<Void> entity = template.postForEntity(
                "http://localhost:8080/api/exam/MatematicaA12/2012/1f/create",
                requestEntity,
                Void.class);

        assertEquals(HttpStatus.CREATED, entity.getStatusCode());
    }
}
