/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia;

import comun.Mensaje;
import comun.Slot;
import externo.ConectorReceptor;
import externo.PuertoSalida;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import proyecto_iia.tareas.Context_Enricher;
import proyecto_iia.tareas.EnumTarea;

/**
 *
 * @author israe
 */
public class PrototipoContextEnricher {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        System.out.println("Sebas' contextEnricher main");
        Slot sEntrada = new Slot();
        Slot sEntrada2 = new Slot();
        Slot sSalida = new Slot();
        ArrayList<Slot> arraySlotEntrada = new ArrayList<>();
        ArrayList<Slot> arraySlotSalida = new ArrayList<>();
        arraySlotEntrada.add(sEntrada);
        arraySlotEntrada.add(sEntrada2);
        arraySlotSalida.add(sSalida);

        ConectorReceptor cExSalida = new ConectorReceptor();

        Context_Enricher tareaPrueba = new Context_Enricher(EnumTarea.CONTEXT_ENRICHER, arraySlotEntrada, arraySlotSalida, "drink", "price");
        PuertoSalida puertoSalida = new PuertoSalida(sSalida, cExSalida); //quitar null

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        File prueba = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "orders" + File.separator + "Pruebaenrichercontexto.xml");
        Document doc = dBuilder.parse(prueba);

        Mensaje tmp1 = new Mensaje(doc, 1);
        System.out.println(tmp1.toString());

        File prueba2 = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "orders" + File.separator + "mensajebody.xml");
        Document doc2 = dBuilder.parse(prueba2);
//        doc = dBuilder.parse(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "orders" + File.separator + "mensajebody.xml"));
        Mensaje tmp2 = new Mensaje(doc2, 3);

        System.out.println(tmp2.toString());

        sEntrada.addMensaje(tmp1);
        sEntrada2.addMensaje(tmp2);
        tareaPrueba.procesar();
        puertoSalida.generarSalida();
    }
}
