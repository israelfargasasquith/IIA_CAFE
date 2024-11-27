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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author israe
 */
public class Splitter extends Tarea {

    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private XPathFactory xfactory;
    private XPath xpath;
    private String expresionXPathContarMensajes;
    private String xPathQuery;
    private Slot input;
    private Slot output;

    public Splitter(EnumTarea t, ArrayList<Slot> se, ArrayList<Slot> sl) {
        super(t, se, sl);
        input = se.get(0);
        output = sl.get(0);

        dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.out.println("Error parsing splitter constructor: " + ex.getMessage());
            System.exit(-1);
        }

        xfactory = XPathFactory.newInstance();
        xpath = xfactory.newXPath();

        //especificas de Cafe
        expresionXPathContarMensajes = "count(cafe_order/cafe_subOrder)";
        xPathQuery = "//drinks/*";
    }

    public void setExpresionXPath(String expresion) {
        this.expresionXPathContarMensajes = expresion;
    }

    public void setXPathQuery(String nuevo) {
        this.xPathQuery = nuevo;
    }

    @Override
    public void procesar() {

        Mensaje mensajeAProcesar = this.input.getMensaje();
        Document doc = mensajeAProcesar.getDocument(); //Obtiene el documento del cuerpo del mensaje
        NodeList drinks = null;
        try {
            drinks = (NodeList) xpath.compile(xPathQuery).evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            System.out.println("Error splitter al extraer la lista de nodos: " + ex.getMessage());
        }

        NodeList order = doc.getElementsByTagName("order_id");
        Node ord = order.item(0);

        int j = 0;
        if (drinks != null) {
            for (int i = 0; i < drinks.getLength(); i++) //Para cada nodo
            {
                Document doc2 = dBuilder.newDocument();
                Node b = drinks.item(i);

                if (b.getNodeType() == Node.ELEMENT_NODE) {
                    Node drink = drinks.item(i);

                    if (drink.getNodeType() == Node.ELEMENT_NODE) {
                        Node copyNode = doc2.importNode(drink, true);
                        doc2.appendChild(copyNode);
                        Node TagOrder = doc2.importNode(ord, true);
                        doc2.getDocumentElement().appendChild(TagOrder);
                    }
                }

                Mensaje nuevoMensaje = new Mensaje(doc2, mensajeAProcesar.getIdSegment(), i); //Crea un nuevo mensaje con el nuevo documento
                output.addMensaje(nuevoMensaje); //Escribe en el slot de salida
                System.out.println("Nuevo mensaje" + i + " creado en el splitter y escrito en el slot correspondiente");
            }
        }

    }
}
