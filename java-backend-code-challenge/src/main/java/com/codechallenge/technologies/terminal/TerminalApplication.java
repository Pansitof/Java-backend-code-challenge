package com.codechallenge.technologies.terminal;

import com.codechallenge.share.adapters.userrepository.FileSystemUserRepository;
import com.codechallenge.application.UserFinder;
import com.codechallenge.application.UsersFinder;
import com.codechallenge.application.ports.driven.UserRepository;

import java.util.Scanner;

public class TerminalApplication {

    private static Scanner scanner;

    TerminalVisualizer visualizer = new TerminalVisualizer();

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
            visualizer.showMenu();
            seleccion = scanner.nextInt();
            switch (seleccion) {
                case 1:
                    visualizer.showUsers(usersFinder.execute());
                    break;
                case 2:
                    System.out.println("Introduzca el nombre de usuario");
                    visualizer.showUserByUsername(userFinder.execute(scanner.nextLine()));
                    break;
                default:
                    System.out.println("Seleccione una opción válida:");
                    break;
            }
        } while (seleccion != 0);
    }






}
