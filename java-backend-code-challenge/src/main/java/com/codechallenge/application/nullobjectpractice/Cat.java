package com.codechallenge.application.nullobjectpractice;

class Cat implements Animal {

    @Override
    public void sayName(MessageReceptor receptor) {
        receptor.emmit("My name is Cat");
    }

    @Override
    public void makeSound(SoundReceptor receptor) {
        receptor.play("Miau!");
    }
}
