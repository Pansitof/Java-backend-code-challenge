package com.practices.resultpattern;

public class Something {

    public Something() {
    }

    public Result doSomething(int i) {
        return switch (i) {
            case 1 -> Result.successResult();
            case 2 -> Result.failureResult(new Error("Not found", "There is not a potatoe in the DB"));
            case 3 -> Result.exceptionResult(new RuntimeException("Excepción Malvada"));
            default -> null;
        };
    }
}
