package com.zero.ioshop.userservice.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Component
public class ResponseModel {
    public Integer status;
    public String message;
    public Object data;

    public ResponseModel success(Object data) {
        return new ResponseModel(200, "success", data);
    }

    public ResponseModel success() {
        return new ResponseModel(200, "success", null);
    }

    public ResponseModel notFound() {
        return new ResponseModel(404, "not found", null);
    }
}
