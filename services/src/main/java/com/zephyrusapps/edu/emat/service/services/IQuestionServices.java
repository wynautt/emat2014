package com.zephyrusapps.edu.emat.service.services;

import com.zephyrusapps.edu.emat.service.operations.CreateQuestionOp;
import com.zephyrusapps.edu.emat.service.operations.results.Result;

public interface IQuestionServices {

    public void createQuestion(CreateQuestionOp op);
}
