package com.example.crud.response;

import lombok.Data;

@Data
public class ApplicationResponse {

    private String status;
    private int statusCode;
    private Object data;
    private String message;
}
