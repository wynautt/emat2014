package com.zephyrusapps.edu.emat.service.rest.controller;

import com.mangofactory.swagger.annotations.ApiError;
import com.mangofactory.swagger.annotations.ApiErrors;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zephyrusapps.edu.emat.service.operations.CreateExamOp;
import com.zephyrusapps.edu.emat.service.operations.CreateQuestionOp;
import com.zephyrusapps.edu.emat.service.operations.ViewExamOp;
import com.zephyrusapps.edu.emat.service.operations.ViewQuestionOp;
import com.zephyrusapps.edu.emat.service.rest.domain.ExamData;
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
import java.util.List;

@Controller
@RequestMapping("/exam")
public class QuestionController {

    private static Logger LOG = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    IQuestionServices questionServices;

    @ApiOperation("Get all exams from a course")
    @RequestMapping(method = RequestMethod.GET, value = "/{course}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<ExamData> viewExams(@PathVariable String course) {
        ViewExamOp op = new ViewExamOp();
        op.setCourse(course);
        return questionServices.viewExams(op);
    }

    @ApiOperation("Get all questions from an exam")
    @ApiErrors(errors = {@ApiError(code = 404, reason = "Exam does not exist")})
    @RequestMapping(method = RequestMethod.GET, value = "/{course}/{year}/{phase}/questions")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<QuestionData> viewQuestions(@PathVariable String course,
                                     @PathVariable int year,
                                     @PathVariable String phase) {

        ViewQuestionOp op = new ViewQuestionOp();
        op.setCourse(course);
        op.setYear(year);
        op.setPhase(phase);

        return questionServices.viewQuestions(op);
    }

    @ApiOperation("Get a question from an exam")
    @ApiErrors(errors = {@ApiError(code = 404, reason = "Exam or question number do not exist")})
    @RequestMapping(method = RequestMethod.GET, value = "/{course}/{year}/{phase}/question/{number}")
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

    @ApiOperation("Create a new exam")
    @ApiErrors(errors = {
            @ApiError(code = 400, reason = "Invalid data received"),
            @ApiError(code = 409, reason = "Exam already exists")})
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createExam(@RequestBody CreateExamOp op) {
        questionServices.createExam(op);
    }

    @ApiOperation("Create a new question")
    @ApiErrors(errors = {
            @ApiError(code = 400, reason = "Invalid data received") ,
            @ApiError(code = 404, reason = "Exam does not exist")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/{course}/{year}/{phase}/question/create")
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
