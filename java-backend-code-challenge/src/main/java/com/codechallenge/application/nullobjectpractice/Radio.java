package com.codechallenge.application.nullobjectpractice;

class Radio implements MessageReceptor, SoundReceptor {

    @Override
    public void emmit(String message) {
        System.out.println(message);
    }

    @Override
    public void play(String sound) {
        System.out.println(sound);
    }
}
