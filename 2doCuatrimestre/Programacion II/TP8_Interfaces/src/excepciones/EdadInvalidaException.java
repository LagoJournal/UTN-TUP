package excepciones;

// Excepción checked: extiende Exception, obliga a capturarla o declararla
public class EdadInvalidaException extends Exception {

    public EdadInvalidaException(String mensaje) {
        super(mensaje);
    }
}
