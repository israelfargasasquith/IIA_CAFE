///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package proyecto_iia;
//
//import comun.Slot;
//import externo.PuertoEntrada;
//import externo.PuertoSalida;
//import java.util.ArrayList;
//import proyecto_iia.tareas.EnumTarea;
//import proyecto_iia.tareas.Replicator;
//
///**
// *
// * @author israe
// */
//public class PrototipoReplicator {
//
//    public static void main(String[] args) {
//        //BORRAR LOS GENERATED OUTPUT ANTES DE HACER ALGUNA EJECUCION, ASI VEMOS QUE SE GENERAN NUEVOS
//        System.out.println("Jose's main");
//        Slot sEntrada = new Slot();
//        Slot sSalida = new Slot();
//        ArrayList<Slot> arraySlotEntrada = new ArrayList<>();
//        ArrayList<Slot> arraySlotSalida = new ArrayList<>();
//        arraySlotEntrada.add(sEntrada);
//
//        PuertoEntrada puertoEntrada = new PuertoEntrada(sEntrada,null);
//
//        //Bucle para probar con varias salidas
//        for (int i = 0; i < 2; i++) {
//            arraySlotSalida.add(sSalida);
//        }
//        Replicator tareaPrueba = new Replicator(EnumTarea.REPLICATOR, arraySlotEntrada, arraySlotSalida);
//
//
//        PuertoSalida puertoSalida = new PuertoSalida(sSalida,null);
//        puertoEntrada.generarEntrada();
//        tareaPrueba.procesar();
//        puertoSalida.generarSalida();
//
//    }
//}
