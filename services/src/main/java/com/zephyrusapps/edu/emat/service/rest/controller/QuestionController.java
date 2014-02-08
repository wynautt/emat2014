package com.zephyrusapps.edu.emat.service.rest.controller;

import com.zephyrusapps.edu.emat.service.operations.CreateQuestionOp;
import com.zephyrusapps.edu.emat.service.rest.domain.Question;
import com.zephyrusapps.edu.emat.service.services.IQuestionServices;
import com.zephyrusapps.edu.emat.service.services.QuestionServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/question")
public class QuestionController {

    private static Logger LOG = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    IQuestionServices questionServices;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Question viewQuestion(@PathVariable() String id) {
        Question q = new Question(2012, "1", "1", "1", Arrays.asList("a","b","c","d"), id);

        return q;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createQuestion(@RequestBody CreateQuestionOp op) {
        questionServices.createQuestion(op);
    }


    //@ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String notFound() {
        return "not found";
    }
}
