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
    //  private int nEntradaAprocesar;

    public Replicator(EnumTarea t, ArrayList<Slot> se, ArrayList<Slot> sl) {
        super(t, se, sl);
        //    nEntradaAprocesar = 0;
    }

    @Override
    public void procesar() {
        while (!this.isEmpty(0)) {
            mensaje = this.getMensajeEntrada(0);   //Solo tiene 1 entrada
            //Lo coloca en las salidas
            for (int i = 0; i < this.getNSalidas(); i++) {
                this.setMensajeSalida(mensaje, i);
                System.out.println("\nReplicator Mensaje: IDMsg -> "+mensaje.getIdMsg() +"   IDDocument: "+ mensaje.getIdDocument());
                System.out.println(mensaje.toString()+"\n");
            }
        }
    }
}

//    public void setEntradaAProcesar(int nEntrada) {
//        this.nEntradaAprocesar = nEntrada;
//    }
//    @Override
//    public void procesar() {
//
//        while (!this.isEmpty(nEntradaAprocesar)) {
//            mensaje = this.getMensajeEntrada(nEntradaAprocesar);   //Solo tiene 1 entrada
//            //Lo coloca en las salidas
//            for (int i = 0; i < this.getNSalidas(); i++) {
//                this.setMensajeSalida(mensaje, i);
//            }
//        }
//    }

