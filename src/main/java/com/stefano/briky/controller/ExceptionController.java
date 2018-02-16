package com.stefano.briky.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stefano.briky.dialogflow.DialogFlowException;
import com.stefano.briky.json.ErrorJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ExceptionController {

    @Autowired
    ObjectMapper mapper;

    @ExceptionHandler(value = DialogFlowException.class)
    public void handleDialogFlowException(DialogFlowException e, HttpServletResponse response) {
        ErrorJson e1 = new ErrorJson();
        e1.setStatus(e.getStatus());
        e1.setErrorType(e.getErrorType());
        e1.setErrorDetails(e.getMessage());

        handleError(e1, response);
    }

    private void handleError(ErrorJson e, HttpServletResponse response) {
        try {
            response.setStatus(e.getStatus());
            response.setContentType("application/json");
            mapper.writeValue(response.getOutputStream(), e);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
