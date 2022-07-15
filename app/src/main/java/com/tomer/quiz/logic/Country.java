package com.tomer.quiz.logic;

public class Country {
    private String name;
    private int flagId;

    public Country(String name, int id) {
        this.name = name;
        this.flagId = id;
    }

    public String getName() {
        return name;
    }

    public int getFlagId() {
        return flagId;
    }
}
