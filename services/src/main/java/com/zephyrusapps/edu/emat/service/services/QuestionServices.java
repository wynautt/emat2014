package com.zephyrusapps.edu.emat.service.services;

import com.zephyrusapps.edu.emat.service.domain.Question;
import com.zephyrusapps.edu.emat.service.operations.CreateQuestionOp;
import com.zephyrusapps.edu.emat.service.operations.results.Result;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

import static com.zephyrusapps.edu.emat.service.persistence.OfyService.ofy;

public class QuestionServices implements IQuestionServices {

    static List<Question.Theme> convertStr2Themes(List<String> strings) {
        List<Question.Theme> result = new ArrayList<Question.Theme>(strings.size());
        for(String s: strings) {
            result.add(Question.Theme.valueOf(s));
        }
        return result;
    }

    static Question.Phase convertStr2Phase(String str) {
        return Question.Phase.valueOf(str);
    }

    @Override
    public void createQuestion(CreateQuestionOp op) {
        Question q = new Question();
        q.setYear(op.getYear());
        q.setPhase(convertStr2Phase(op.getPhase()));
        q.setNumber(op.getNumber());
        q.setText(op.getText());
        q.setThemes(convertStr2Themes(op.getThemes()));
        q.setAnswers(op.getAnswers());
        q.setValidAnswerIndex(op.getValidAnswerIndex());

        //BeanUtils.copyProperties(op, q);

        ofy().save().entity(q);
    }
}
