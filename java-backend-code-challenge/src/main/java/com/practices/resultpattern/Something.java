package com.practices.resultpattern;

public class Something {

    public Something() {
    }

    public CommandResult doSomething(int i) {
        return switch (i) {
            case 1 -> CommandResult.failureResult(new Error("Not found", "There is not a potatoe in the DB"));
            default -> CommandResult.successResult();
        };
    }
}
