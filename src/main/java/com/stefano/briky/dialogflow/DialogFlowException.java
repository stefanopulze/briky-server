package com.stefano.briky.dialogflow;

public class DialogFlowException extends Exception {

    private final int status;
    private final String errorType;

    public DialogFlowException(int status, String errorType, String message) {
        super(message);
        this.status = status;
        this.errorType = errorType;
    }

    public int getStatus() {
        return status;
    }

    public String getErrorType() {
        return errorType;
    }
}
