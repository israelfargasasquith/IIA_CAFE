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
import proyecto_iia.tareas.Tarea;

/**
 *
 * @author israe
 */
public class PrototipoAgregator {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        System.out.println("Isra's agregator main");

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

        Agregator tareaPrueba = new Agregator(EnumTarea.AGREGATOR, arraySlotEntrada, arraySlotSalida);
        
        tareaPrueba.setxPathQueryInfo1("//name");
        tareaPrueba.setxPathQueryInfo2("//type");
        tareaPrueba.setxPathQueryInfo3("//price");
        
        tareaPrueba.setRootTag("cafe_order");
        tareaPrueba.setIdTag("order_id");
        tareaPrueba.setGroupTag("drinks");
        
        tareaPrueba.setInfo1Tag("name");
        tareaPrueba.setInfo2Tag("type");
        tareaPrueba.setInfo3Tag("price");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc0 = dBuilder.parse(new File(System.getProperty("user.dir") + File.separator + "src"
                + File.separator + "orders" + File.separator + "MensajePruebaAgregator0.xml"));
        Document doc1 = dBuilder.parse(new File(System.getProperty("user.dir") + File.separator + "src"
                + File.separator + "orders" + File.separator + "MensajePruebaAgregator1.xml"));
        Mensaje parte0 = new Mensaje(doc0, 1, 0, 1);
        Mensaje parte1 = new Mensaje(doc1, 1, 1, 1);

        sEntrada.addMensaje(parte0);
        sEntrada.addMensaje(parte1);

        tareaPrueba.procesar();
        puertoSalida.generarSalida();
    }
}
