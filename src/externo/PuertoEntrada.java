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
public class PuertoEntrada {

    private Slot slotSalida;
    private ArrayList<Document> slotEntrada;

    public PuertoEntrada(Slot slotSalida, ArrayList<Document> slotEntrada) {
        this.slotSalida = slotSalida;
        this.slotEntrada = slotEntrada;
    }
    
    public void setSlotEntradaDocumentos(ArrayList<Document> sEntradaDoc){
        this.slotEntrada = sEntradaDoc;
    }
    

    public void generarEntrada() {
        while(!slotEntrada.isEmpty()){
            System.out.println("Salida del puerto de entrada generada");
            Mensaje nuevo = new Mensaje(slotEntrada.removeFirst());
            slotSalida.addMensaje(nuevo);
        }
    }

}
