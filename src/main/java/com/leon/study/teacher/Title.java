package com.leon.study.teacher;

public enum Title {

    MASTER("Ma"), DOCTOR("Dr"), PROFESSOR("Pr");

    private String shortName;

    Title(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public static Title fromShortName(String shortName) {
        switch (shortName) {
            case "Ma":
                return Title.MASTER;
            case "Dr":
                return Title.DOCTOR;
            case "Pr":
                return Title.PROFESSOR;
            default:
                throw new IllegalArgumentException("ShortName [" + shortName + "] not supported");
        }
    }

}
