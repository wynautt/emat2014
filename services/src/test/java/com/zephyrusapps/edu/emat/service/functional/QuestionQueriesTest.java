package com.zephyrusapps.edu.emat.service.functional;

import com.zephyrusapps.edu.emat.service.rest.domain.Question;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static junit.framework.TestCase.assertEquals;

public class QuestionQueriesTest {

    @Test
    public void thatQuestionCanBeQueried() {
        RestTemplate template = new RestTemplate();

        ResponseEntity<Question> entity = template.getForEntity(
                "http://localhost:8080/emat/7",
                Question.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());

        Question q = entity.getBody();

        assertEquals(2012, q.getYear());
    }
}
