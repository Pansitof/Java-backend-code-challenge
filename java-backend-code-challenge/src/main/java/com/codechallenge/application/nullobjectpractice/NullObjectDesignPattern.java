package com.codechallenge.application.nullobjectpractice;

import java.util.List;

class NullObjectDesignPattern {

    public static void main(String[] args) {
        Iterable<Animal> animals = List.of(new Duck(), new Cat(), new NullAnimal(), new Cat());

        handle(animals);
    }

    static void handle(Iterable<Animal> animals) {
        for (Animal animal : animals) {
            Radio radio = new Radio();
            animal.sayName(radio);
            animal.makeSound(radio);
        }
    }
}
