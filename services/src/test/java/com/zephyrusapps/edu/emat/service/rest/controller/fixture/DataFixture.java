package com.zephyrusapps.edu.emat.service.rest.controller.fixture;

import com.zephyrusapps.edu.emat.service.rest.domain.QuestionData;
import scala.actors.threadpool.Arrays;

public class DataFixture {

    public static QuestionData exMath2012Phase1Question1() {
        QuestionData result = new QuestionData();
        result.setCourse("MatematicaA12");
        result.setYear(2012);
        result.setPhase("1f");
        result.setNumber("1");
        result.setText("dummy question 1");
        result.setAnswers(Arrays.asList(new String[]{"10", "20", "30", "40"}));
        result.setGroup("I");
        result.setThemes(Arrays.asList(new String[]{"Prob", "Succ"}));
        return result;
    }
}
