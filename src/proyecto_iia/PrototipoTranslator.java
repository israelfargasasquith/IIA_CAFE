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
import proyecto_iia.tareas.Translator;

/**
 *
 * @author israe
 */
public class PrototipoTranslator {

    public static void main(String[] args) {
        System.out.println("Isra's translator main");
        ArrayList<Document> listDocumentsInput = new ArrayList<>();
        ArrayList<Document> listDocumentsOutput = new ArrayList<>();
        Slot sEntrada = new Slot();
        Slot sSalida = new Slot();
//        Slot sPuertoSalida = new Slot();
//        Slot sPuertoEntrada = new Slot();
        ArrayList<Slot> arraySlotEntrada = new ArrayList<>();
        ArrayList<Slot> arraySlotSalida = new ArrayList<>();
        arraySlotEntrada.add(sEntrada);
        arraySlotSalida.add(sSalida);

        ConectorFicheroGenerador cExEntrada = new ConectorFicheroGenerador(listDocumentsInput);
        ConectorReceptor cExSalida = new ConectorReceptor(listDocumentsOutput);

        PuertoEntrada puertoEntrada = new PuertoEntrada(sEntrada, listDocumentsInput);
        Translator tareaPrueba = new Translator(EnumTarea.TRANSLATOR, arraySlotEntrada, arraySlotSalida);
        PuertoSalida puertoSalida = new PuertoSalida(sSalida, listDocumentsOutput);
        tareaPrueba.setSQLCall(true);

        listDocumentsInput = cExEntrada.generarEntrada();
        puertoEntrada.generarEntrada();
        tareaPrueba.procesar();
        puertoSalida.generarSalida();
        cExSalida.generarSalida("ficheros");
    }
}
