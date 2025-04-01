package com.practices.resultpattern;

public class CommandResult extends Result{

    public CommandResult() {
    }

    public CommandResult(Error error) {
        this.error = error;
    }

    public static CommandResult successResult() {
        return new CommandResult();
    }

    public static CommandResult failureResult(Error error) {
        return new CommandResult(error);
    }

}
