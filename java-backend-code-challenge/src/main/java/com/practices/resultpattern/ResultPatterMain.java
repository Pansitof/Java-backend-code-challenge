package com.practices.resultpattern;

public class ResultPatterMain {
    public static void main(String[] args) {
        Something something = new Something();
        Result result = something.doSomething(1);
        indicarResultados(result);

        result = something.doSomething(2);
        indicarResultados(result);

        result = something.doSomething(3);
        indicarResultados(result);
    }

    private static void indicarResultados(Result result) {
        System.out.println("¿Es éxitoso? "+result.isSuccess());
        System.out.println("¿Hubo fallo? "+result.getError());
        System.out.println("¿Hay Excepción? "+result.isException() +" "+result.getException());
        System.out.println("---");
    }
}
