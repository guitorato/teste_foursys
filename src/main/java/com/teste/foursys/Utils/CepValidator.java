package com.teste.foursys.Utils;

import java.util.regex.Pattern;

public class CepValidator {

    private static final String CEP_REGEX = "^[0-9]{5}-?[0-9]{3}$"; // Aceita tanto '12345-678' quanto '12345678'
    private static final Pattern pattern = Pattern.compile(CEP_REGEX);

    public static boolean isValidCep(String cep) {
        if (cep == null) {
            return false;
        }
        return pattern.matcher(cep.toString()).matches();
    }
}
