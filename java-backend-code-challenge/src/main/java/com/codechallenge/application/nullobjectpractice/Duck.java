package com.codechallenge.application.nullobjectpractice;

class Duck implements Animal {

    @Override
    public void sayName(MessageReceptor receptor) {
        receptor.emmit("My name is duck");
    }

    @Override
    public void makeSound(SoundReceptor receptor) {
        receptor.play("Quack!");
    }
}
