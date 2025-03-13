package com.codechallenge.technologies.terminal;

import com.codechallenge.adapters.userrepository.filesystem.FileSystemUserRepository;
import com.codechallenge.adapters.userrepository.mysql.MySqlUserRepository;
import com.codechallenge.application.User;
import com.codechallenge.application.UsersFinder;
import com.codechallenge.application.ports.driven.UserRepository;

import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {

    private static StringBuilder stringBuilder;
    private static Scanner scanner;

    UserRepository userRepository = new FileSystemUserRepository();

    UsersFinder usersFinder = new UsersFinder(userRepository);

    public static void main(String[] args) {
        ConsoleApplication consoleApplication = new ConsoleApplication();
        consoleApplication.mainMenu();
    }

    void mainMenu() {
        List<User> users = usersFinder.execute();
        for (User user : users){
            String name = user.name();
            System.out.println(name);
            System.out.println(user.username());
        }
    }
}
