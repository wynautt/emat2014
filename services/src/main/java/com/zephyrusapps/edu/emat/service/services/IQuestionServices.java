package com.zephyrusapps.edu.emat.service.services;

import com.zephyrusapps.edu.emat.service.operations.CreateExamOp;
import com.zephyrusapps.edu.emat.service.operations.CreateQuestionOp;
import com.zephyrusapps.edu.emat.service.operations.ViewQuestionOp;
import com.zephyrusapps.edu.emat.service.rest.domain.QuestionData;

public interface IQuestionServices {

    public void createQuestion(CreateQuestionOp op);
    public void createExam(CreateExamOp op);

    public QuestionData viewQuestion(ViewQuestionOp op);
}
