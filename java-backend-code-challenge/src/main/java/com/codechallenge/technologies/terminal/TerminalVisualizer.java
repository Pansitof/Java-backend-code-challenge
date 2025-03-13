package com.codechallenge.technologies.terminal;

import com.codechallenge.application.User;

import java.util.List;
import java.util.Scanner;

public class TerminalVisualizer {

    private static StringBuilder stringBuilder;

    public void showMenu() {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Menu Principal \n");
        stringBuilder.append("\t1. Mostrar usuarios. \n");
        stringBuilder.append("\t2. Mostrar un usuario. \n");
        stringBuilder.append("\t0. Cerrar Terminal. \n");
        stringBuilder.append("Por favor. Seleccione Opción: \n");
        System.out.println(stringBuilder.toString());
    }

    void showUsers(List<User> users) {
        stringBuilder = new StringBuilder();
        for (User user : users) {
            stringBuilder.append(converterUserToString(user));
            stringBuilder.append("---\n");
        }
        System.out.println(stringBuilder.toString());
    }

    public void showUserByUsername(User user) {
        if (user != null) {
            System.out.println(converterUserToString(user));
            return;
        }
        System.out.println("Usuario NO encotnrado");
    }

    String converterUserToString(User user) {
        StringBuilder temporalstringBuilder = new StringBuilder();
        temporalstringBuilder.append("\t name = '" + user.name() + "'\n");
        temporalstringBuilder.append("\t username = '" + user.username() + "'\n");
        temporalstringBuilder.append("\t email = '" + user.email() + "'\n");
        temporalstringBuilder.append("\t gender = '" + user.gender() + "'\n");
        temporalstringBuilder.append("\t picture = '" + user.picture() + "'\n");
        return temporalstringBuilder.toString();
    }

    ;

}
