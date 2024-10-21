package com.teste.foursys.Utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CepValidatorTest {

    @Test
    void testValidCepWithHyphen() {
        String validCep = "12345-678";
        assertTrue(CepValidator.isValidCep(validCep), "CEP should be valid");
    }

    @Test
    void testValidCepWithoutHyphen() {
        String validCep = "12345678";
        assertTrue(CepValidator.isValidCep(validCep), "CEP should be valid");
    }

    @Test
    void testInvalidCepWithLetters() {
        String invalidCep = "12345-67A";
        assertFalse(CepValidator.isValidCep(invalidCep), "CEP should be invalid");
    }

    @Test
    void testInvalidCepTooShort() {
        String invalidCep = "12345";
        assertFalse(CepValidator.isValidCep(invalidCep), "CEP should be invalid");
    }

    @Test
    void testInvalidCepTooLong() {
        String invalidCep = "123456789";
        assertFalse(CepValidator.isValidCep(invalidCep), "CEP should be invalid");
    }

    @Test
    void testNullCep() {
        String nullCep = null;
        assertFalse(CepValidator.isValidCep(nullCep), "CEP should be invalid");
    }
}