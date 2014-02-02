package com.zephyrusapps.edu.emat.service.rest.controller;

import com.zephyrusapps.edu.emat.service.rest.domain.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/emat")
public class QuestionQueriesController {

    private static Logger LOG = LoggerFactory.getLogger(QuestionQueriesController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Question viewQuestion(@PathVariable() String id) {
        Question q = new Question(2012, "1", "1", "1", Arrays.asList("a","b","c","d"), id);
        return q;
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {
        return "not found";
    }
}
