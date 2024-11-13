/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia;

import comun.Slot;
import externo.PuertoEntrada;
import externo.PuertoSalida;
import java.util.ArrayList;
import proyecto_iia.tareas.Distributor;
import proyecto_iia.tareas.EnumTarea;

/**
 *
 * @author israe
 */
public class PrototipoJose {

    public static void main(String[] args) {
        //BORRAR LOS GENERATED OUTPUT ANTES DE HACER ALGUNA EJECUCION, ASI VEMOS QUE SE GENERAN NUEVOS
        System.out.println("Jose's main");
        Slot sEntrada = new Slot();
        Slot sSalida = new Slot();
        ArrayList<Slot> arraySlotEntrada = new ArrayList<>();
        ArrayList<Slot> arraySlotSalida = new ArrayList<>();
        arraySlotEntrada.add(sEntrada);
        arraySlotSalida.add(sSalida);

        PuertoEntrada puertoEntrada = new PuertoEntrada(sEntrada);

        //Bucle para probar con varias salidas
        for (int i = 0; i < 1; i++) {
            arraySlotSalida.add(sSalida);
        }
//        Replicator tareaPrueba = new Replicator(EnumTarea.REPLICATOR, arraySlotEntrada, arraySlotSalida);

        String[] Condiciones = {"cold", "hot"};
        Distributor tareaPrueba = new Distributor(EnumTarea.DISTRIBUTOR, arraySlotEntrada, arraySlotSalida, Condiciones, 2);

        PuertoSalida puertoSalida = new PuertoSalida(sSalida);
        puertoEntrada.generarEntrada();
        tareaPrueba.procesar();
        puertoSalida.generarSalida();

    }
}
