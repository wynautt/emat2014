package com.zephyrusapps.edu.emat.service.services;

import com.googlecode.objectify.cmd.LoadType;
import com.googlecode.objectify.cmd.Query;
import com.zephyrusapps.edu.emat.service.domain.Exam;
import com.zephyrusapps.edu.emat.service.domain.Question;
import com.zephyrusapps.edu.emat.service.operations.CreateExamOp;
import com.zephyrusapps.edu.emat.service.operations.CreateQuestionOp;
import com.zephyrusapps.edu.emat.service.operations.ViewExamOp;
import com.zephyrusapps.edu.emat.service.operations.ViewQuestionOp;
import com.zephyrusapps.edu.emat.service.persistence.ViewExamOpFilterMap;
import com.zephyrusapps.edu.emat.service.rest.domain.ExamData;
import com.zephyrusapps.edu.emat.service.rest.domain.QuestionData;
import com.zephyrusapps.edu.emat.service.services.result.ExamAlreadyExistsException;
import com.zephyrusapps.edu.emat.service.services.result.ExamNotFoundException;
import com.zephyrusapps.edu.emat.service.services.result.QuestionAlreadyExistsException;
import com.zephyrusapps.edu.emat.service.services.result.QuestionNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String examId = Exam.genId(op.getCourse(), op.getYear(), op.getPhase());
        Exam exam = ofy().load().type(Exam.class).id(examId).now();

        if(exam != null) {
            throw new ExamAlreadyExistsException(examId);
        }

        exam = new Exam();

        exam.setYear(op.getYear());
        exam.setCourse(op.getCourse());
        exam.setPhase(op.getPhase());
        exam.init();

        ofy().save().entity(exam);
    }

    @Override
    public List<ExamData> viewExams(ViewExamOp op) {
        ViewExamOpFilterMap viewExamOp = new ViewExamOpFilterMap(op);

        LoadType<Exam> loadType = ofy().load().type(Exam.class);
        Query<Exam> query = null;
        for(String filter: viewExamOp.filterKeys()) {
            query = loadType != null ?
                    loadType.filter(filter, viewExamOp.getValue(filter)) :
                    query.filter(filter, viewExamOp.getValue(filter));

            loadType = null;
        }

        List<Exam> exams = query.list();

        List<ExamData> r = new ArrayList<ExamData>();

        for(Exam e: exams) {
            r.add(new ExamData(e));
        }

        return r;
    }

    @Override
    public QuestionData viewQuestion(ViewQuestionOp op) {
        String examId = Exam.genId(op.getCourse(), op.getYear(), op.getPhase());
        Exam exam = ofy().load().type(Exam.class).id(examId).now();

        if(exam == null) {
            throw new ExamNotFoundException(examId);
        }

        Question q = exam.getQuestion(op.getNumber());

        if(q == null) {
            throw new QuestionNotFoundException(op.getNumber());
        }

        QuestionData result = new QuestionData(q);

        return result;
    }

    @Override
    public List<QuestionData> viewQuestions(ViewQuestionOp op) {
        String examId = Exam.genId(op.getCourse(), op.getYear(), op.getPhase());
        Exam exam = ofy().load().type(Exam.class).id(examId).now();

        if(exam == null) {
            throw new ExamNotFoundException(examId);
        }

        List<QuestionData> result = new ArrayList<QuestionData>();

        for(Question q: exam.getQuestionList()) {
            result.add(new QuestionData(q));
        }

        return result;
    }

}