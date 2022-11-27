package com.zero.ioshop.userservice.model;

public enum MessageStatus {
    SUCCESS(200,"OK"),
    NOT_FOUND(404, "NOT FOUND"),
    AUTHENTICATION_FAILED(403, "AUTHENTICATION FAILED"),
    RECORD_AlREADY_EXIST(409, "RECORD ALREADY EXIST"),
    COULD_NOT_DELETE_RECORD(409, "COULD NOT DELETE RECORD"),
    COULD_NOT_UPDATE_RECORD(409, "COULD NOT UPDATE RECORD"),
    COULD_NOT_SAVE_RECORD(409,"COULD NOT SAVE RECORD"),
    NOT_EXIST(404, "ID DOES NOT EXIST"),
    INTERNAL_ERROR(500, "INTERNAL ERROR");


    private final String  message;
    private final int  code;
    MessageStatus(int code, String message) {
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
