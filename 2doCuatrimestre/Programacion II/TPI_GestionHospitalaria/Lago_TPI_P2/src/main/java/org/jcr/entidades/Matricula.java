package org.jcr.entidades;

public class Matricula {

    private static final String PREFIX     = "MP-";
    private static final int    MIN_DIGITS = 4;
    private static final int    MAX_DIGITS = 6;

    private final String number;

    public Matricula(String number) {
        this.number = validate(number);
    }

    public String getNumero() {
        return number;
    }

    private String validate(String number) {
        if (number == null || !number.startsWith(PREFIX)) {
            throw new IllegalArgumentException(
                    "La matrícula debe comenzar con " + PREFIX);
        }
        String digits = number.substring(PREFIX.length());
        if (digits.length() < MIN_DIGITS
                || digits.length() > MAX_DIGITS
                || !Validations.isDigits(digits)) {
            throw new IllegalArgumentException(
                    "La matrícula debe tener entre " + MIN_DIGITS
                    + " y " + MAX_DIGITS + " dígitos");
        }
        return number;
    }
}
