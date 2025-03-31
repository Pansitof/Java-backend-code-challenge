package com.practices.resultpattern;

public class ResultPatterMain {
    public static void main(String[] args) {
        Something something = new Something();
        System.out.println("Resultado Éxitoso");
        Result result = something.doSomething(1);
        indicarResultados(result);
        System.out.println("---");

        System.out.println("Resultado Fallido");
        result = something.doSomething(2);
        indicarResultados(result);
        System.out.println("---");

        System.out.println("Exception");
        result = something.doSomething(3);
        indicarResultados(result);
        System.out.println("---");
    }

    private static void indicarResultados(Result result) {
        System.out.println("¿Es éxitoso? "+result.isSuccess());
        System.out.println("¿Hay Excepción? "+result.isException() +" "+result.getException().getMessage());
        System.out.println("¿Hubo fallo? "+result.getFailureMessage());
    }
}
