/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package externo;

import comun.Mensaje;
import comun.Slot;
import java.util.ArrayList;
import org.w3c.dom.Document;

/**
 *
 * @author israe
 */
public class PuertoSalida {

    private Slot slotEntrada;
    private ArrayList<Document> documentosSalida;

    public PuertoSalida(Slot slotEntrada) {
        this.slotEntrada = slotEntrada;
    }

    public void generarSalida() {

        while (!slotEntrada.isEmpty()) {
            Mensaje tmp = slotEntrada.getMensaje();
            System.out.println("El mensaje: " + tmp.getIdMsg()+ " sale por el puerto salida");
            
        }

    }
}
