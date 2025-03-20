package com.codechallenge.application.domain;

import java.util.Iterator;
import java.util.List;

public record User(String username,
                   String name,
                   String email,
                   String gender,
                   String picture) {
}



class User2 {

    public static void main(String[] args) {
        Iterable<IAnimal> animals = List.of();
        handle(animals);
    }
    static void handle(Iterable<IAnimal> animals){
        for (IAnimal animal: animals){
            System.out.println(animal.GetName()+" _ "+animal.MakeSound());
        }
    }
}


interface IAnimal {
    String GetName();

    String MakeSound();
}

class Duck implements IAnimal {
    @Override
    public String GetName() {
        return "Pato";
    }

    @Override
    public String MakeSound() {
        return "Quack!";
    }
}

class Cat implements IAnimal {
    @Override
    public String GetName() {
        return "Gato";
    }

    @Override
    public String MakeSound() {
        return "Miau";
    }
}

class NullAnimal implements IAnimal {
    @Override
    public String GetName() {
        return "";
    }

    @Override
    public String MakeSound() {
        return "";
    }
}