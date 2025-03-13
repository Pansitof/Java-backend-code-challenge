package com.codechallenge.technologies.terminal;

import com.codechallenge.adapters.userrepository.filesystem.FileSystemUserRepository;
import com.codechallenge.application.User;
import com.codechallenge.application.UserFinder;
import com.codechallenge.application.UsersFinder;
import com.codechallenge.application.ports.driven.UserRepository;

import java.util.List;
import java.util.Scanner;

public class TerminalApplication {

    private static StringBuilder stringBuilder;
    private static Scanner scanner;

    UserRepository userRepository = new FileSystemUserRepository();

    UsersFinder usersFinder = new UsersFinder(userRepository);
    UserFinder userFinder = new UserFinder(userRepository);

    public static void main(String[] args) {
        TerminalApplication terminalApplication = new TerminalApplication();
        terminalApplication.mainMenu();
    }

    private void mainMenu() {
        int seleccion = 1;
        scanner = new Scanner(System.in);
        do {
            showMenu();
            seleccion = scanner.nextInt();
            switch (seleccion) {
                case 1:
                    showUsers();
                    break;
                case 2:
                    showUserByUsername();
                    break;
                default:
                    System.out.println("Seleccione una opción válida:");
                    break;
            }
        } while (seleccion != 0);
    }

    private void showUserByUsername() {
        scanner = new Scanner(System.in);
        System.out.println("Introduzca el nombre de usuario");
        User user = userFinder.execute(scanner.nextLine());

        if (user != null) {
            System.out.println(converterUserToString(user));
            return;
        }

        System.out.println("Usuario NO encotnrado");
    }

    void showMenu() {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Menu Principal \n");
        stringBuilder.append("\t1. Mostrar usuarios. \n");
        stringBuilder.append("\t2. Mostrar un usuario. \n");
        stringBuilder.append("\t0. Cerrar Terminal. \n");
        stringBuilder.append("Por favor. Seleccione Opción: \n");
        System.out.println(stringBuilder.toString());
    }

    void showUsers() {
        List<User> users = usersFinder.execute();
        stringBuilder = new StringBuilder();
        for (User user : users) {
            stringBuilder.append(converterUserToString(user));
            stringBuilder.append("---\n");
        }
        System.out.println(stringBuilder.toString());
    }

    String converterUserToString(User user){
        StringBuilder temporalstringBuilder = new StringBuilder();
        temporalstringBuilder.append("\t name = '" + user.name() + "'\n");
        temporalstringBuilder.append("\t username = '" + user.username() + "'\n");
        temporalstringBuilder.append("\t email = '" + user.email() + "'\n");
        temporalstringBuilder.append("\t gender = '" + user.gender() + "'\n");
        temporalstringBuilder.append("\t picture = '" + user.picture() + "'\n");
        return temporalstringBuilder.toString();
    };
}
