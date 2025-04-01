package com.practices.resultpattern;

public class Result {
    protected Error error;

    protected boolean isSuccess() {
        return error == null;
    }

    protected void notifyError() {
        if (error != null) {
            System.out.println(error.Code() + " " + error.Description());
        }
    }
}