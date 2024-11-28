/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia.tareas;

import comun.Mensaje;
import comun.Slot;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private String xPathQueryContar;
    private String xPathQuerySeparador;
    private String xPathIdMensaje;
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
    }

    public void setXPathQuerySeparar(String nuevo) {
        this.xPathQuerySeparador = nuevo;
    }

    public void setXPathQueryContar(String nuevo) {
        this.xPathQueryContar = nuevo;
    }

    public void setxPathIdMensaje(String nuevo) {
        this.xPathIdMensaje = nuevo;
    }

    @Override
    public void procesar() {
        Mensaje mensajeAProcesar = this.input.getMensaje();
        Document docASeparar = mensajeAProcesar.getDocument(); //Obtiene el documento del cuerpo del mensaje
        NodeList listaTodosLosNodos = null;
        try {
            listaTodosLosNodos = (NodeList) xpath.compile(xPathQuerySeparador).evaluate(docASeparar, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            System.out.println("Error splitter al extraer la listaTodosLosNodos de nodos: " + ex.getMessage());
        }

        int idDocument = mensajeAProcesar.getIdDocument();
        int nSegmentos = -1;
        Double tmp = -1.0;
        try {
            tmp = (Double) xpath.compile(xPathQueryContar).evaluate(docASeparar, XPathConstants.NUMBER);
        } catch (XPathExpressionException ex) {
            System.out.println("Error splitter al extraer el numero de nodos: " + ex.getMessage());
        }
        nSegmentos = tmp.intValue();
        Node id=null;
        try {
            id = (Node) xpath.compile(xPathIdMensaje).evaluate(input, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            System.out.println("Error splitter al extraer el id order: " + ex.getMessage());
        }

        if (listaTodosLosNodos != null && nSegmentos != -1) {
            System.out.println("Splitter************************");

            for (int i = 0; i < listaTodosLosNodos.getLength(); i++) //Para cada nodo
            {
                Document docExtraido = dBuilder.newDocument();
                Node base = listaTodosLosNodos.item(i);
                System.out.println("Nodo base: " + base.getTextContent());
                if (base.getNodeType() == Node.ELEMENT_NODE) {

                    Node nodo = listaTodosLosNodos.item(i);
                    System.out.println("Nodo nodo: " + nodo.getTextContent());

                    if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                        Node TagOrder = docExtraido.importNode(id, true);
                        docExtraido.getDocumentElement().appendChild(TagOrder);
                        Node copyNode = docExtraido.importNode(nodo, true);
                        docExtraido.appendChild(copyNode);
                    }
                }

                Mensaje nuevoMensaje = new Mensaje(docExtraido, idDocument, i, nSegmentos);
                output.addMensaje(nuevoMensaje);
                System.out.println("Nuevo mensaje" + i + " creado en el splitter y escrito en el slot correspondiente");
            }
        }

    }
}
