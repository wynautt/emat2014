package com.zephyrusapps.edu.emat.service.services;

import com.zephyrusapps.edu.emat.service.domain.Exam;
import com.zephyrusapps.edu.emat.service.domain.Question;
import com.zephyrusapps.edu.emat.service.operations.CreateExamOp;
import com.zephyrusapps.edu.emat.service.operations.CreateQuestionOp;
import com.zephyrusapps.edu.emat.service.operations.ViewQuestionOp;
import com.zephyrusapps.edu.emat.service.operations.results.Result;
import com.zephyrusapps.edu.emat.service.rest.domain.QuestionData;
import com.zephyrusapps.edu.emat.service.services.result.ExamNotFoundException;
import com.zephyrusapps.edu.emat.service.services.result.QuestionAlreadyExistsException;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

import static com.zephyrusapps.edu.emat.service.persistence.OfyService.ofy;
import static java.lang.String.format;

public class QuestionServices implements IQuestionServices {


    @Override
    public void createQuestion(CreateQuestionOp op) {
        String examId = Exam.genId(op.getCourse(), op.getYear(), op.getPhase());
        Exam exam = ofy().load().type(Exam.class).id(examId).now();

        if(exam == null) {
            throw new ExamNotFoundException(examId);
        }

        if(exam.hasQuestion(op.getNumber())) {
            throw new QuestionAlreadyExistsException(format("Exam %s already contains question %s.", examId, op.getNumberUntouched()));
        }


        Question q = new Question();
        q.setNumber(op.getNumber());
        q.setText(op.getText());
        q.setThemes(op.getThemes());
        q.setAnswers(op.getAnswers());
        q.setValidAnswerIndex(op.getValidAnswerIndex());

        exam.addQuestion(q);

        ofy().save().entity(exam);
    }

    @Override
    public void createExam(CreateExamOp op) {
        Exam exam = new Exam();

        exam.setYear(op.getYear());
        exam.setCourse(op.getCourse());
        exam.setPhase(op.getPhase());
        exam.init();

        ofy().save().entity(exam);
    }

    @Override
    public QuestionData viewQuestion(ViewQuestionOp op) {
        String examId = Exam.genId(op.getCourse(), op.getYear(), op.getPhase());
        Exam exam = ofy().load().type(Exam.class).id(examId).now();

        if(exam == null) {
            throw new ExamNotFoundException(examId);
        }

        Question q = exam.getQuestion(op.getNumber());

        QuestionData result = new QuestionData(q);

        return result;
    }
}
