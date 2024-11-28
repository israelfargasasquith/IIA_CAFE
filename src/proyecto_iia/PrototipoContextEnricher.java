///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package proyecto_iia;
//
//import comun.Mensaje;
//import comun.Slot;
//import externo.PuertoSalida;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import org.w3c.dom.Document;
//import org.xml.sax.SAXException;
//import proyecto_iia.tareas.Context_Enricher;
//import proyecto_iia.tareas.EnumTarea;
//
///**
// *
// * @author israe
// */
//public class PrototipoContextEnricher {
//
//    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
//        
//        
//        System.out.println("Sebas' contextEnricher main");
//        Slot sEntrada = new Slot();
//        Slot sEntrada2 = new Slot();
//        Slot sSalida = new Slot();
//        ArrayList<Slot> arraySlotEntrada = new ArrayList<>();
//        ArrayList<Slot> arraySlotSalida = new ArrayList<>();
//        arraySlotEntrada.add(sEntrada);
//        arraySlotEntrada.add(sEntrada2);
//        arraySlotSalida.add(sSalida);
//
//        Context_Enricher tareaPrueba = new Context_Enricher(EnumTarea.TRANSLATOR, arraySlotEntrada, arraySlotSalida, "drinks");
//        PuertoSalida puertoSalida = new PuertoSalida(sSalida,null);
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//        Document doc = dBuilder.parse(new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "orders" + System.getProperty("file.separator") + "order1.xml"));
//        Mensaje tmp1 = new Mensaje(doc);
//        Mensaje tmp2 = new Mensaje(doc);
//
//        sEntrada.addMensaje(tmp1);
//        sEntrada2.addMensaje(tmp2);
//        tareaPrueba.procesar();
//        puertoSalida.generarSalida();
//
//        /*// Para ver la salida de la tarea sin generar fichero, hay que quitar puertoSalida.generarSalida()
//        while (!arraySlotSalida.get(0).isEmpty()) {
//            System.out.println(arraySlotSalida.get(0).getMensaje().toString());
//
//        }*/
//    }
//}
