/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package externo;

import comun.Mensaje;
import comun.Slot;

/**
 *
 * @author israe
 */
public class PuertoSalida {

    private Slot slotEntrada;
    private ConectorReceptor conectorReceptor;

    public PuertoSalida(Slot slotEntrada, ConectorReceptor conectorReceptor) {
        this.slotEntrada = slotEntrada;
        this.conectorReceptor = conectorReceptor;
    }

    public void generarSalida() {
        while (!slotEntrada.isEmpty()) {
            Mensaje tmp = slotEntrada.getMensaje();
            System.out.println("El mensaje: " + tmp.getIdMsg() + " sale por el puerto salida");
            conectorReceptor.generarSalida("ficheros", tmp.getDocument());
        }
    }
}
