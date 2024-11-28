///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package proyecto_iia.tareas;
//
//import comun.Mensaje;
//import comun.Slot;
//import java.util.ArrayList;
//import org.w3c.dom.Document;
//
///**
// *
// * @author israe
// */
//public class Replicator extends Tarea {
//
//    private Mensaje mensaje;
//
//    public Replicator(EnumTarea t, ArrayList<Slot> se, ArrayList<Slot> sl /*, int n*/) {
//        super(t, se, sl);
//    }
//
//    @Override
//    public void procesar() {
//
//        mensaje = this.getMensajeEntrada(0);   //Solo tiene 1 entrada
//        //Lo coloca en las salidas
//        for (int i = 0; i < this.getNSalidas(); i++) {
//            this.setMensajeSalida(new Mensaje(mensaje.getDocument()), i);
//        }
//    }
//
//}
