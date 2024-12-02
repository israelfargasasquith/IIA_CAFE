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
import proyecto_iia.tareas.Distributor;
import proyecto_iia.tareas.EnumTarea;

/**
 *
 * @author israe
 */
public class PrototipoDistributor {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        Slot sEntrada = new Slot();
        Slot sSalida2 = new Slot();
        Slot sSalida = new Slot();
        ArrayList<Slot> arraySlotEntrada = new ArrayList<>();
        ArrayList<Slot> arraySlotSalida = new ArrayList<>();
        
        
        
        arraySlotEntrada.add(sEntrada);
        arraySlotSalida.add(sSalida);
        arraySlotSalida.add(sSalida2);

        ConectorReceptor cReceptor = new ConectorReceptor();
        PuertoSalida puertoSalida = new PuertoSalida(sSalida, cReceptor);
        PuertoSalida puertoSalida2 = new PuertoSalida(sSalida2, cReceptor);
        

        String[] Condiciones = {"cold", "hot"};
        Distributor tareaPrueba = new Distributor(EnumTarea.DISTRIBUTOR, arraySlotEntrada, arraySlotSalida, Condiciones, 2, "//type");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        File prueba = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "orders" + File.separator + "MensajePruebaDistributor.xml");
        Document doc = dBuilder.parse(prueba);
        Mensaje tmp1 = new Mensaje(doc,1);
        
        sEntrada.addMensaje(tmp1);
        tareaPrueba.procesar();
        puertoSalida.generarSalida();
        puertoSalida2.generarSalida();
    }
}
