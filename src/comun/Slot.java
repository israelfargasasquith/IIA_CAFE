/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comun;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author israe
 */
public class Slot {

    private final Queue<Mensaje> colaMensajes;

    public Slot() {
        colaMensajes = new LinkedList<>();
    }

    public void addMensaje(Mensaje msj) {
        System.out.println("Añadido  mensaje con id = " + msj.getIdMsg() + " idDocument = " + msj.getIdDocument() + " idSegment = " + msj.getIdSegment() + " nSegments = " + msj.getnSegments());
        colaMensajes.add(msj);
    }

    public Mensaje getMensaje() {
        return colaMensajes.remove();
    }

    public boolean isEmpty() {
        return colaMensajes.isEmpty();
    }

    public int nMensajes() {
        return colaMensajes.size();
    }
}
