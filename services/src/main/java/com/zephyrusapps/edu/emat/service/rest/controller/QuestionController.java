package com.zephyrusapps.edu.emat.service.rest.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import com.zephyrusapps.edu.emat.service.operations.CreateExamOp;
import com.zephyrusapps.edu.emat.service.operations.CreateQuestionOp;
import com.zephyrusapps.edu.emat.service.operations.ViewQuestionOp;
import com.zephyrusapps.edu.emat.service.rest.domain.QuestionData;
import com.zephyrusapps.edu.emat.service.services.IQuestionServices;
import com.zephyrusapps.edu.emat.service.services.result.ErrorInfo;
import com.zephyrusapps.edu.emat.service.services.result.ExamNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
@RequestMapping("/exam")
public class QuestionController {

    private static Logger LOG = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    IQuestionServices questionServices;

    public QuestionController() {
        int a = 1;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{course}/{year}/{phase}/{number}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public QuestionData viewQuestion(@PathVariable String course,
                                     @PathVariable int year,
                                     @PathVariable String phase,
                                     @PathVariable String number) {

        ViewQuestionOp op = new ViewQuestionOp();
        op.setCourse(course);
        op.setYear(year);
        op.setPhase(phase);
        op.setNumber(number);

        return questionServices.viewQuestion(op);

        /*QuestionData q = new QuestionData(2012, "1", "1", "1", Arrays.asList("a","b","c","d"), id);

        return q;*/
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createExam(@RequestBody CreateExamOp op) {
        questionServices.createExam(op);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{course}/{year}/{phase}/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void createQuestion(@RequestBody CreateQuestionOp op,
                               @PathVariable String course,
                               @PathVariable int year,
                               @PathVariable String phase) {

        op.setCourse(course);
        op.setYear(year);
        op.setPhase(phase);
        questionServices.createQuestion(op);
    }


    //@ExceptionHandler(ExamNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String examNotFound(ExamNotFoundException ex) {
        return "aaaaaa";
    }
}
