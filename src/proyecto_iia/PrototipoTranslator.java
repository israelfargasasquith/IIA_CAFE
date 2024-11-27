/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia;

import comun.Slot;
import externo.ConectorFicheroGenerador;
import externo.ConectorReceptor;
import externo.PuertoEntrada;
import externo.PuertoSalida;
import java.util.ArrayList;
import org.w3c.dom.Document;
import proyecto_iia.tareas.EnumTarea;
import proyecto_iia.tareas.Splitter;
import proyecto_iia.tareas.Tarea;
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

        ConectorFicheroGenerador cGenerador = new ConectorFicheroGenerador(puertoEntrada);
        ConectorReceptor cReceptor = new ConectorReceptor();

        PuertoSalida puertoSalida = new PuertoSalida(sSalida, cReceptor);

        Tarea tareaPrueba = new Translator(EnumTarea.TRANSLATOR, arraySlotEntrada, arraySlotSalida);

        cGenerador.generarEntrada();
        tareaPrueba.procesar();
        puertoSalida.generarSalida();
    }
}
