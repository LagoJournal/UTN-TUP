/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp8_interfaces.ejercicio1;

/**
 *
 * @author agustinlago
 */

import java.util.List;

public class PedidoConNotificacion extends Pedido {
    private Cliente cliente;

    public PedidoConNotificacion(List<Producto> productos, Cliente cliente) {
        super(productos);
        this.cliente = cliente;
    }

    public void cambiarEstado(String estado) {
               cliente.notificar("El pedido ha cambiado a: " + estado);
    }
}
