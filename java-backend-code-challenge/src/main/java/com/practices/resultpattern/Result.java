package com.practices.resultpattern;

public class Result {
    private boolean success;
    private Error error;
    private Exception exception;

    protected Result() {
        this.success = true;
    }

    protected Result(Error error) {
        this.success = false;
        this.error = error;
    }

    protected Result(Exception e) {
        this.success = false;
        exception = e;
    }

    public static Result successResult() {
        return new Result();
    }

    public static Result failureResult(Error error) {
        return new Result(error);
    }

    public static Result exceptionResult(Exception ex) {
        return new Result(ex);
    }

    public boolean isSuccess() {
        return success;
    }

    public Error getError() {
        return error;
    }

    public Exception getException() {
        return exception;
    }

    public boolean isException() {
        return this.exception != null;
    }
}
