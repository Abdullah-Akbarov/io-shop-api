package com.zero.ioshop.productservice.model;

public enum MessageModel {
    SUCCESS(200,"OK"),
    NOT_FOUND(404, "NOT FOUND"),
    RECORD_AlREADY_EXIST(409, "RECORD ALREADY EXIST"),
    COULD_NOT_DELETE_RECORD(409, "COULD NOT DEACTIVATE RECORD"),
    COULD_NOT_UPDATE_RECORD(409, "COULD NOT UPDATE RECORD"),
    COULD_NOT_SAVE_RECORD(409,"COULD NOT SAVE RECORD"),
    NOT_EXIST(404, "ID DOES NOT EXIST");


    private final String  message;
    private final int  code;
    MessageModel(int code, String message) {
        this.code=code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
