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
public class Replicator extends Tarea {

    private Mensaje mensaje;

    public Replicator(EnumTarea t, ArrayList<Slot> se, ArrayList<Slot> sl) {
        super(t, se, sl);

        mensaje = getMensajeEntrada(0);   //Solo tiene 1 entrada
    }

    @Override
    public void procesar() {

        //Lo coloca en las salidas
        for (int i = 0; i < getNSalidas(); i++) {
            super.setMensajeSalida(mensaje, i);
        }
        sigMensaje();
    }

    public ArrayList<Slot> enviarReplicados() {
        return getslotsSalida();
    }

    private void sigMensaje() {
        mensaje = getMensajeEntrada(0);
    }

}
