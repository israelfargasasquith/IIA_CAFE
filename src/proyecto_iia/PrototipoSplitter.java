/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_iia;

import comun.Slot;
import externo.ConectorFicheroGenerador;
import externo.ConectorReceptor;
import externo.PuertoEntrada;
import externo.PuertoSalida;
import java.util.ArrayList;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import proyecto_iia.tareas.EnumTarea;
import proyecto_iia.tareas.Splitter;

/**
 *
 * @author Usuario
 */
public class PrototipoSplitter {

    public static void main(String[] args) throws TransformerConfigurationException, TransformerException {

        //BORRAR LOS GENERATED OUTPUT ANTES DE HACER ALGUNA EJECUCION, ASI VEMOS QUE SE GENERAN NUEVOS
        System.out.println("Isra's splitter main");
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
        Splitter tareaPrueba = new Splitter(EnumTarea.SPLITTER, arraySlotEntrada, arraySlotSalida);
        PuertoSalida puertoSalida = new PuertoSalida(sSalida,listDocumentsOutput);

        listDocumentsInput =cExEntrada.generarEntrada();
        puertoEntrada.generarEntrada();
        tareaPrueba.procesar();
        puertoSalida.generarSalida();
        cExSalida.generarSalida("ficheros");

    }
}
