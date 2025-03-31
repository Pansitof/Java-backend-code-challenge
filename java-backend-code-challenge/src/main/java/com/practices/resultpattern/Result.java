package com.practices.resultpattern;

public class Result {
    private boolean success;
    private String failureMessage;
    private Exception exception;

    protected Result() {
        this.success = true;
    }

    protected Result(String message) {
        this.success = false;
        failureMessage = message;
    }

    protected Result(Exception e) {
        this.success = false;
        exception = e;
    }

    public static Result successResult() {
        return new Result();
    }

    public static Result failureResult(String message) {
        return new Result(message);
    }

    public static Result exceptionResult(Exception ex) {
        return new Result(ex);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public Exception getException() {
        return exception;
    }

    public boolean isException() {
        return this.exception != null;
    }
}
