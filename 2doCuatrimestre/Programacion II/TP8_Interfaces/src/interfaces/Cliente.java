package interfaces;

public class Cliente implements Notificable {

    private String nombre;

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // El cliente recibe el aviso cada vez que su pedido cambia de estado
    @Override
    public void notificarCambioDeEstado(EstadoPedido estado) {
        System.out.println("[Notificación a " + nombre + "] Su pedido pasó a: " + estado);
    }
}
