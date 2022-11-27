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
    public ResponseModel(MessageStatus messageStatus, Object data){
        this.status=messageStatus.getCode();
        this.message=messageStatus.getMessage();
        this.data=data;
    }
    public ResponseModel(MessageStatus messageStatus){
        this.status=messageStatus.getCode();
        this.message=messageStatus.getMessage();
    }
    public ResponseModel(Integer status, String message){
        this.status=status;
        this.message=message;
    }
}
