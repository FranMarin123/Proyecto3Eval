package com.github.FranMarin123.model.enums;

public enum UserField {
    NAME("name"),
    MAIL("mail"),
    DNI("dni"),
    PASS("pass"),
    PHOTO("image");

    private final String dbField;

    UserField(String dbField) {
        this.dbField = dbField;
    }

    public String getDbField() {
        return dbField;
    }
}
