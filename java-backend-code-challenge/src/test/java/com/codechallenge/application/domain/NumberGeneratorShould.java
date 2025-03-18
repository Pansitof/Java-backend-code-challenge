package com.codechallenge.application.domain;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberGeneratorShould {
    private NumberGenerator numberGenerator;

    @Test
    public void generateFourDigitsNumbers(){
        numberGenerator = new NumberGenerator();
        Pattern pattern = Pattern.compile("^\\d{4}$");
        String numberGenerated = String.valueOf(numberGenerator.generateFourRandomsDigits());

        assertEquals(4,numberGenerated.length());
        assertTrue(pattern.matcher(numberGenerated).find());
    }
}
