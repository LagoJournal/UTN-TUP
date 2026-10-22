package ejercicio12;

public class Calculadora {
    private static final double ALICUOTA_IVA = 0.21;

    public void calcular(Impuesto impuesto) {
        double iva = impuesto.getMonto() * ALICUOTA_IVA;
        double total = impuesto.getMonto() + iva;
        System.out.println("Contribuyente: " + impuesto.getContribuyente().getNombre()
                + " | Base: $" + impuesto.getMonto()
                + " | IVA (21%): $" + iva
                + " | Total: $" + total);
    }
}
