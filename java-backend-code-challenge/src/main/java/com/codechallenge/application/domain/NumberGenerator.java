package com.codechallenge.application.domain;

public class NumberGenerator {

    public int generateFourRandomsDigits(){
        return  (int)((Math.random() * (10000 - 1111)) + 1111);
    }

}
