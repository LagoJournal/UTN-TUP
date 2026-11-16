package interfaces;

// Contrato para quien deba enterarse de los cambios de estado de un pedido
public interface Notificable {

    void notificarCambioDeEstado(EstadoPedido estado);
}
