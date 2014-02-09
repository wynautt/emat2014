package com.zephyrusapps.edu.emat.service.services;

import com.zephyrusapps.edu.emat.service.operations.CreateExamOp;
import com.zephyrusapps.edu.emat.service.operations.CreateQuestionOp;
import com.zephyrusapps.edu.emat.service.operations.ViewExamOp;
import com.zephyrusapps.edu.emat.service.operations.ViewQuestionOp;
import com.zephyrusapps.edu.emat.service.rest.domain.ExamData;
import com.zephyrusapps.edu.emat.service.rest.domain.QuestionData;

import java.util.List;

public interface IQuestionServices {

    public void createQuestion(CreateQuestionOp op);
    public void createExam(CreateExamOp op);

    public List<ExamData> viewExams(ViewExamOp op);

    public QuestionData viewQuestion(ViewQuestionOp op);
    public List<QuestionData> viewQuestions(ViewQuestionOp op);
}
