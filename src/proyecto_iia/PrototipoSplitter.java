/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_iia;

import comun.Slot;
import externo.ConectorGenerador;
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
        ArrayList<Document> listDocuments = new ArrayList<>();
        Slot sEntrada = new Slot();
        Slot sSalida = new Slot();
        Slot sSalidaPuertoSalida = new Slot();
        ArrayList<Slot> arraySlotEntrada = new ArrayList<>();
        ArrayList<Slot> arraySlotSalida = new ArrayList<>();
        arraySlotEntrada.add(sEntrada);
        arraySlotSalida.add(sSalida);

        PuertoEntrada puertoEntrada = new PuertoEntrada(sEntrada, listDocuments);
        Splitter tareaPrueba = new Splitter(EnumTarea.SPLITTER, arraySlotEntrada, arraySlotSalida);
        PuertoSalida puertoSalida = new PuertoSalida(sSalida, sSalidaPuertoSalida);

        ConectorGenerador cExEntrada = new ConectorGenerador();
        ConectorReceptor cExSalida = new ConectorReceptor(sSalidaPuertoSalida);
        
        listDocuments =cExEntrada.generarEntrada();
        puertoEntrada.setSlotEntradaDocumentos(listDocuments);
        puertoEntrada.generarEntrada();
        tareaPrueba.procesar();
        puertoSalida.generarSalida();
        cExSalida.generarSalida();

        /* Para ver la salida de la tarea sin generar fichero, hay que quitar puertoSalida.generarSalida()
        while (!arraySlotSalida.get(0).isEmpty()) {
            System.out.println(arraySlotSalida.get(0).getMensaje().toString());

        }*/
    }
}
