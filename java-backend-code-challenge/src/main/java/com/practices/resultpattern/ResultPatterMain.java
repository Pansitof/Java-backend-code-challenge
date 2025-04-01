package com.practices.resultpattern;

public class ResultPatterMain {
    public static void main(String[] args) {
        Something something = new Something();
        CommandResult commandResult = something.doSomething(1);
        indicarResultados(commandResult);

        commandResult = something.doSomething(2);
        indicarResultados(commandResult);

        QueryResult<Coche> coche = new QueryResult<>(new Coche("123AAA",100.00,true));

        System.out.println(coche.getData().isFunctional());
    }

    private static void indicarResultados(CommandResult commandResult) {
        commandResult.notifyError();
    }
}
