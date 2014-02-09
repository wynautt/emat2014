package com.zephyrusapps.edu.emat.service.rest.controller.fixture;

public class RestFixture {

    public static String exQuestion1() {
        return "{\n" +
                "    \"number\": \"1.1\",\n" +
                "    \"text\": \"dummy question\",\n" +
                "    \"themes\": [\n" +
                "        \"Probabilities\",\n" +
                "        \"Limits\"\n" +
                "    ],\n" +
                "    \"answers\": [\n" +
                "        \"1\",\n" +
                "        \"2\",\n" +
                "        \"3\",\n" +
                "        \"4\"\n" +
                "    ],\n" +
                "    \"validAnswerIndex\": 0\n" +
                "}";
    }

    public static String exQuestion2() {
        return "{\n" +
                "    \"number\": \"2\",\n" +
                "    \"text\": \"dummy question\",\n" +
                "    \"themes\": [\n" +
                "        \"Probabilities\",\n" +
                "        \"Limits\"\n" +
                "    ],\n" +
                "    \"answers\": [\n" +
                "        \"1\",\n" +
                "        \"2\",\n" +
                "        \"3\",\n" +
                "        \"4\"\n" +
                "    ],\n" +
                "    \"validAnswerIndex\": 0\n" +
                "}";
    }

    public static String exExamMath2012Phase1() {
        return "{\n" +
                "    \"course\": \"MatematicaA12\",\n" +
                "    \"year\": 2012,\n" +
                "    \"phase\": \"1f\"\n" +
                "}";
    }
}
