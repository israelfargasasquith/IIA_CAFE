/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia;

import comun.Mensaje;
import comun.Slot;
import externo.ConectorFicheroGenerador;
import externo.ConectorReceptor;
import externo.PuertoEntrada;
import externo.PuertoSalida;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import proyecto_iia.tareas.Agregator;
import proyecto_iia.tareas.EnumTarea;

/**
 *
 * @author israe
 */
public class PrototipoAgregator {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        //BORRAR LOS GENERATED OUTPUT ANTES DE HACER ALGUNA EJECUCION, ASI VEMOS QUE SE GENERAN NUEVOS
        System.out.println("Isra's agregator main");
        ArrayList<Document> listDocumentsInput = new ArrayList<>();
        ArrayList<Document> listDocumentsOutput = new ArrayList<>();
        Slot sEntrada = new Slot();
        Slot sSalida = new Slot();
//        Slot sPuertoSalida = new Slot();
//        Slot sPuertoEntrada = new Slot();
        ArrayList<Slot> arraySlotEntrada = new ArrayList<>();
        ArrayList<Slot> arraySlotSalida = new ArrayList<>();

        ConectorFicheroGenerador cExEntrada = new ConectorFicheroGenerador(listDocumentsInput);
        ConectorReceptor cExSalida = new ConectorReceptor(listDocumentsOutput);

        PuertoEntrada puertoEntrada = new PuertoEntrada(sEntrada, listDocumentsInput);

        Agregator tareaPrueba = new Agregator(EnumTarea.AGREGATOR, arraySlotEntrada, arraySlotSalida);
        PuertoSalida puertoSalida = new PuertoSalida(sSalida, listDocumentsOutput);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc0 = dBuilder.parse(new File(System.getProperty("user.dir") + File.separator + "src"
                + File.separator + "generatedOutput" + File.separator + "output0.xml"));
        Document doc1 = dBuilder.parse(new File(System.getProperty("user.dir") + File.separator + "src"
                + File.separator + "generatedOutput" + File.separator + "output1.xml"));
        Mensaje parte0 = new Mensaje(doc0);
        Mensaje parte1 = new Mensaje(doc1);

        sEntrada.addMensaje(parte0);
        sEntrada.addMensaje(parte1);

        arraySlotEntrada.add(sEntrada);
        arraySlotSalida.add(sSalida);

        tareaPrueba.procesar();
        puertoSalida.generarSalida();
        cExSalida.generarSalida("ficheros");

    }
}
