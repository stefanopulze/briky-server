package com.stefano.briky.json;

public class ErrorJson {

    private int status;
    private String errorType;
    private String errorDetails;

    public ErrorJson() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

    @Override
    public String toString() {
        return "ErrorJson{" +
                "status=" + status +
                ", errorType='" + errorType + '\'' +
                ", errorDetails='" + errorDetails + '\'' +
                '}';
    }
}
