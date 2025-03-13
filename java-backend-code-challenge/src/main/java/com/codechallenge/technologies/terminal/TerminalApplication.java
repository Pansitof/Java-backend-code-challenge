package com.codechallenge.technologies.terminal;

import com.codechallenge.adapters.userrepository.filesystem.FileSystemUserRepository;
import com.codechallenge.application.User;
import com.codechallenge.application.UsersFinder;
import com.codechallenge.application.ports.driven.UserRepository;

import java.util.List;
import java.util.Scanner;

public class TerminalApplication {

    private static StringBuilder stringBuilder;
    private static Scanner scanner;

    UserRepository userRepository = new FileSystemUserRepository();

    UsersFinder usersFinder = new UsersFinder(userRepository);

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
            switch (seleccion){
                case 1:
                    showUsers();
                    break;
            }
        }while (seleccion !=0);
    }

    void showMenu() {
        stringBuilder = new StringBuilder();
        stringBuilder.append("1. Mostrar usuarios. \n");
        stringBuilder.append("0. Cerrar Terminal. \n");
        stringBuilder.append("Por favor. Seleccione Opción: \n");
        System.out.println(stringBuilder.toString());
    }

    void showUsers() {
        List<User> users = usersFinder.execute();
        stringBuilder = new StringBuilder();
        for (User user : users){
            stringBuilder.append("\t name = '"+user.name()+"'\n");
            stringBuilder.append("\t username = '"+user.username()+"'\n");
            stringBuilder.append("\t email = '"+user.email()+"'\n");
            stringBuilder.append("\t gender = '"+user.gender()+"'\n");
            stringBuilder.append("\t picture = '"+user.picture()+"'\n");
            stringBuilder.append("---\n");
        }
        System.out.println(stringBuilder.toString());
    }
}
