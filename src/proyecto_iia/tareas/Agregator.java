/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia.tareas;

import comun.Mensaje;
import comun.Slot;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;

/**
 *
 * @author israe
 */
public class Agregator extends Tarea {

    private ArrayList<Mensaje> mensajesAUnir;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Source xsltSource;
    private TransformerFactory transformerFactory;
    private Transformer transformer;
    private Mensaje tmp;

    public Agregator(EnumTarea t, ArrayList<Slot> se, ArrayList<Slot> sl) {
        super(t, se, sl);
        mensajesAUnir = new ArrayList<>();
        dbFactory = DocumentBuilderFactory.newInstance();

        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.out.println("Error parsing agregator constructor dbBuilder: " + ex.getMessage());
            return;
        }
        transformerFactory = TransformerFactory.newInstance();
        try {
            transformer = transformerFactory.newTransformer(xsltSource);
        } catch (TransformerConfigurationException ex) {
            System.out.println("Error creating transformer, agregator constructor, transformer: " + ex.getMessage());
            return;
        }

    }

    @Override
    public void procesar() {
        int nSegmemtos = 0;
        while (!this.isEmpty(0)) {
            tmp = this.getMensajeEntrada(0);
            nSegmemtos = tmp.getnSegments();
            System.out.println("Hay " + nSegmemtos + " nSegmentos");
            mensajesAUnir.add(tmp);
            DOMResult domResult = new DOMResult();

            for (int i = 0; i < nSegmemtos; i++) {
                DOMSource input = new DOMSource(mensajesAUnir.get(i).getDocument());
                
                
                
            }

//            for (Mensaje mensaje : mensajesAUnir) {
//                DOMSource input = new DOMSource(mensaje.getDocument());
//
//                try {
//                    transformer.transform(input, domResult);
//                } catch (TransformerException ex) {
//                    System.out.println("Error de Transformer" + ex.getMessage());
//                    return;
//                }
//
//            }
//            Document mergedDocument = (Document) domResult.getNode();
//
//            Mensaje nuevo = new Mensaje(mergedDocument);
//
//            this.setMensajeSalida(nuevo, 0);
        }
    }

}
