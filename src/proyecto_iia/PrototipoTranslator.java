/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia;

import comun.Slot;
import externo.ConectorGenerador;
import externo.ConectorReceptor;
import externo.PuertoEntrada;
import externo.PuertoSalida;
import java.util.ArrayList;
import org.w3c.dom.Document;
import proyecto_iia.tareas.EnumTarea;
import proyecto_iia.tareas.Translator;

/**
 *
 * @author israe
 */
public class PrototipoTranslator {

    public static void main(String[] args) {
        System.out.println("Isra's translator main");
        ArrayList<Document> listDocuments = new ArrayList<>();
        Slot sEntrada = new Slot();
        Slot sSalida = new Slot();
        Slot sSalidaPuertoSalida = new Slot();
        ArrayList<Slot> arraySlotEntrada = new ArrayList<>();
        ArrayList<Slot> arraySlotSalida = new ArrayList<>();
        arraySlotEntrada.add(sEntrada);
        arraySlotSalida.add(sSalida);

        PuertoEntrada puertoEntrada = new PuertoEntrada(sEntrada, listDocuments);
        Translator tareaPrueba = new Translator(EnumTarea.TRANSLATOR, arraySlotEntrada, arraySlotSalida);
        PuertoSalida puertoSalida = new PuertoSalida(sSalida, sSalidaPuertoSalida);

        ConectorGenerador cExEntrada = new ConectorGenerador();
        ConectorReceptor cExSalida = new ConectorReceptor(sSalidaPuertoSalida);

        tareaPrueba.setSQLCall(true);
        listDocuments = cExEntrada.generarEntrada();
        System.out.println("size: "+listDocuments.size());
        puertoEntrada.generarEntrada();
        tareaPrueba.procesar();
        puertoSalida.generarSalida();
        cExSalida.generarSalida();

        /*// Para ver la salida de la tarea sin generar fichero, hay que quitar puertoSalida.generarSalida()
        while (!arraySlotSalida.get(0).isEmpty()) {
            System.out.println(arraySlotSalida.get(0).getMensaje().toString());

        }*/
    }
}
