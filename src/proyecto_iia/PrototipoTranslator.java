/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia;

import comun.Slot;
import externo.PuertoEntrada;
import externo.PuertoSalida;
import java.util.ArrayList;
import proyecto_iia.tareas.EnumTarea;
import proyecto_iia.tareas.Translator;

/**
 *
 * @author israe
 */
public class PrototipoTranslator {

    public static void main(String[] args) {
        System.out.println("Isra's translator main");
        Slot sEntrada = new Slot();
        Slot sSalida = new Slot();
        ArrayList<Slot> arraySlotEntrada = new ArrayList<>();
        ArrayList<Slot> arraySlotSalida = new ArrayList<>();
        arraySlotEntrada.add(sEntrada);
        arraySlotSalida.add(sSalida);

        PuertoEntrada puertoEntrada = new PuertoEntrada(sEntrada);
        Translator tareaPrueba = new Translator(EnumTarea.TRANSLATOR, arraySlotEntrada, arraySlotSalida);
        PuertoSalida puertoSalida = new PuertoSalida(sSalida);
        
        tareaPrueba.setSQLCall(true);
        puertoEntrada.generarEntrada();
        tareaPrueba.procesar();
        puertoSalida.generarSalida();

        /*// Para ver la salida de la tarea sin generar fichero, hay que quitar puertoSalida.generarSalida()
        while (!arraySlotSalida.get(0).isEmpty()) {
            System.out.println(arraySlotSalida.get(0).getMensaje().toString());

        }*/
    }
}
