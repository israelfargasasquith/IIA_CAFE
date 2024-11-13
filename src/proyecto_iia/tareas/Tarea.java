/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyecto_iia.tareas;

import comun.Mensaje;
import java.util.ArrayList;
import org.w3c.dom.Document;
import comun.Slot;

/**
 *
 * @author israe
 */
abstract public class Tarea {

    private EnumTarea tipo;
    private ArrayList<Slot> slotsEntrada;
    private ArrayList<Slot> slotsSalida;

    abstract public void procesar();

    public Tarea(EnumTarea t, ArrayList<Slot> se, ArrayList<Slot> sl) {
        tipo = t;
        slotsEntrada = se;
        slotsSalida = sl;
    }

    public boolean isEmpty(int numEntrada) {
        return slotsEntrada.get(numEntrada).isEmpty();
    }

    public Mensaje getMensajeEntrada(int numE) {
        return slotsEntrada.get(numE).getMensaje();
    }

    public void setMensajeSalida(Mensaje msj, int numS) {
        slotsSalida.get(numS).addMensaje(msj);

    }

    public int getNSalidas() {
        return slotsSalida.size();
    }

    public ArrayList<Slot> getslotsSalida() {
        return slotsSalida;
    }
    //Ampliar el numero de slots de salida
//    public void setNSalidas(int n){
//        for (int i = 0; i < n; i++) {
//            slotsSalida.add(new Slot());
//        }
//    }

}
