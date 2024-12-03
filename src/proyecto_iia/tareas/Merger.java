/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia.tareas;

import comun.Mensaje;
import comun.Slot;
import java.util.ArrayList;
import org.w3c.dom.Document;

/**
 *
 * @author israe
 */
public class Merger extends Tarea {

    private ArrayList<Slot> sEntrada;
    private Slot output;

    public Merger(EnumTarea t, ArrayList<Slot> se, ArrayList<Slot> sl) {
        super(t, se, sl);
        sEntrada = se;
        output = sl.getFirst();
    }

    @Override
    public void procesar() {
        for (Slot input : sEntrada) {
            while(!input.isEmpty()){
                Mensaje tmp = input.getMensaje();
                System.out.println("Mensaje: "+tmp.getIdDocument()+" pasa por el merger");
                output.addMensaje(tmp);
            }
        }
    }

}
