package org.jcr.entidades;

// Centralizamos las validaciones que comparten las entidades para no
// repetirlas en cada constructor. Es de paquete: no forma parte del
// modelo que ven las otras capas.
class Validations {

    private Validations() {
    }

    // Tratamos los textos con solo espacios igual que los vacíos
    static String requireText(String value, String errorMessage) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
        return value;
    }

    static void requireNonNull(Object value, String errorMessage) {
        if (value == null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    static boolean isDigits(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isDigit(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
