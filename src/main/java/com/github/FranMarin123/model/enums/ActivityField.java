package com.github.FranMarin123.model.enums;

public enum ActivityField {
    NAME("name"),
    DESCRIPTION("description"),
    PERCENT("percent"),
    MEDIA("media_file");

    private final String dbField;

    ActivityField(String dbField) {
        this.dbField = dbField;
    }

    public String getDbField() {
        return dbField;
    }
}
