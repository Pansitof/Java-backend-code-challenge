package com.practices.resultpattern;

public class Something {

    public Something(){}

    public Result doSomething(int i) {
        return switch (i) {
            case 1 -> Result.successResult();
            case 2 -> Result.failureResult("Algo salió mal");
            case 3 -> Result.exceptionResult(new RuntimeException("Excepción Malvada"));
            default -> null;
        };
    }
}
