/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package externo;

import comun.Slot;

/**
 *
 * @author israe
 */
public class PuertoSalida {

    private Slot slotEntrada;
    private Slot slotSalida;

    public PuertoSalida(Slot slotEntrada,Slot slotSalida) {
        this.slotEntrada = slotEntrada;
        this.slotSalida = slotSalida;
    }

    public void generarSalida() {

        while (!slotEntrada.isEmpty()) {
            System.out.println("Salida generada por el puerto de salida");
            slotSalida.addMensaje(slotEntrada.getMensaje());
        }

    }
}
