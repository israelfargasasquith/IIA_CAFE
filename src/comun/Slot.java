/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comun;

import java.util.LinkedList;
import java.util.Queue;
import org.w3c.dom.Document;

/**
 *
 * @author israe
 */
public class Slot {
    private Queue<Mensaje> colaMensajes;
    
    public Slot(){
        colaMensajes = new LinkedList<Mensaje>();
    }
    
    public void addMensaje(Mensaje msj){
        colaMensajes.add(msj);
    }
    
    public Mensaje getMensaje(){
        return colaMensajes.remove();
    }
    
    public boolean isEmpty(){
        return colaMensajes.isEmpty();
    }
    
    public int nMensajes(){
        return colaMensajes.size();
    }
}
