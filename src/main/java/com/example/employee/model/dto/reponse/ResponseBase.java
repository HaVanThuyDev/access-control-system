package com.example.employee.model.dto.reponse;

import lombok.Data;

@Data
public class ResponseBase<T> {
    String message;
    private  T data;
    private  Long total;
}
