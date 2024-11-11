/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_iia;

import comun.Mensaje;
import comun.Slot;
import externo.PuertoEntrada;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;
import proyecto_iia.tareas.EnumTarea;
import proyecto_iia.tareas.Splitter;

/**
 *
 * @author Usuario
 */
public class Proyecto_IIA {

    public static void main(String[] args) throws TransformerConfigurationException, TransformerException {

        Slot sEntrada = new Slot();
        Slot sSalida = new Slot();
        ArrayList<Slot> arraySlotEntrada = new ArrayList<>();
        ArrayList<Slot> arraySlotSalida = new ArrayList<>();
        arraySlotEntrada.add(sEntrada);
        arraySlotSalida.add(sSalida);

        PuertoEntrada puertoEntrada = new PuertoEntrada(sEntrada);
        Splitter tareaPrueba = new Splitter(EnumTarea.SPLITTER, arraySlotEntrada, arraySlotSalida);

        puertoEntrada.generarEntrada();
        tareaPrueba.procesar();

        while (!arraySlotSalida.get(0).isEmpty()) {
            System.out.println(arraySlotSalida.get(0).getMensaje().toString());

        }

    }
}

/* PRUEBAS PARA CREAR UN FICHERO EXTERNO COMO SALIDA DE DATOS DE LA APLICACION TAMBIEN EJEMPLOS DE COMO EXTRAER DATOS DEL ARBOL DE NODOS 
DEL XML

Esto para guardarlo en un fichero externo->
                    //At the end, we save the file XML on disk
                    //TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    //Transformer transformer = transformerFactory.newTransformer();
                    //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    //DOMSource source = new DOMSource(suppXml);
                    //StreamResult result = new StreamResult(new File("resources/" + supplier.trim() + ".xml"));
                    //transformer.transform(source, result);


                //with StreamResult we generate a new file
                //StreamResult result = new StreamResult("output.xml");
                
                NodeList order_id = transformedDoc.getElementsByTagName("id");
                NodeList nodos = doc.getElementsByTagName("drink");
                String id = "";

                if (order_id.getLength() > 0 && order_id.item(0) != null) {
                    id = order_id.item(0).getTextContent();
                    System.out.println("Order ID: " + id);
                } else {
                    System.out.println("Tag <order_id> not found in the document.");
                }

                System.out.println("Root Element :" + transformedDoc.getDocumentElement().getNodeName());
                System.out.println("Order id : " + id);
                System.out.println("------");
                for (int temp = 0; temp < nodos.getLength(); temp++) {

                    Node node = nodos.item(temp);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        Element element = (Element) node;

                        String name = element.getElementsByTagName("name").item(0).getTextContent();
                        String type = element.getElementsByTagName("type").item(0).getTextContent();
                        String fragmen = element.getElementsByTagName("nFrag").item(0).getTextContent();

                        System.out.println("Current Element :" + node.getNodeName());
                        System.out.println("First Name : " + name);
                        System.out.println("Last Name : " + type);
                        System.out.println("------------");

                    }
                }
                /*
                XPath filter = XPathFactory.newInstance().newXPath();
                String expressionDrinks = "//drink";
                String expressionOrderId = "//order_id";

                NodeList order_id = (NodeList) filter.compile(expressionOrderId).evaluate(doc, XPathConstants.NODESET);
                NodeList nodos = (NodeList) filter.compile(expressionDrinks).evaluate(doc, XPathConstants.NODESET);

                String id = order_id.item(0).getTextContent();
                System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
                System.out.println("Order id : " + id);
                System.out.println("------");
                for (int temp = 0; temp < nodos.getLength(); temp++) {

                    Node node = nodos.item(temp);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        Element element = (Element) node;

                        String name = element.getElementsByTagName("name").item(0).getTextContent();
                        String type = element.getElementsByTagName("type").item(0).getTextContent();

                        System.out.println("Current Element :" + node.getNodeName());
                        System.out.println("First Name : " + name);
                        System.out.println("Last Name : " + type);
                        System.out.println("------------");

                    }
                }

                //Retrival information without using XPath, just doing a search into the node tree document
                /* doc.getDocumentElement().normalize();

                NodeList order_id = doc.getElementsByTagName("order_id");
                String id = order_id.item(0).getTextContent();

                System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
                System.out.println("Order id : " + id);
                System.out.println("------");

                NodeList list = doc.getElementsByTagName("drink");
                for (int temp = 0; temp < list.getLength(); temp++) {

                    Node node = list.item(temp);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        Element element = (Element) node;

                        String name = element.getElementsByTagName("name").item(0).getTextContent();
                        String type = element.getElementsByTagName("type").item(0).getTextContent();

                        System.out.println("Current Element :" + node.getNodeName());
                        System.out.println("First Name : " + name);
                        System.out.println("Last Name : " + type);
                        System.out.println("------------");

                    }
                }*/
