/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package externo;

import comun.Mensaje;
import comun.Slot;
import org.w3c.dom.Document;

/**
 *
 * @author israe
 */
public class PuertoEntrada {

    private Slot slotSalida;

    public PuertoEntrada(Slot slotSalida) {
        this.slotSalida = slotSalida;
    }

    public void generarEntrada(Document input) {
        System.out.println("Salida del puerto de entrada generada");
        Mensaje nuevo = new Mensaje(input, input.getElementsByTagName(""));
        slotSalida.addMensaje(nuevo);
    }

}
