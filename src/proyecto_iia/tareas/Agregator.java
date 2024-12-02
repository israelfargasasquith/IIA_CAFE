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
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 *
 * @author israe
 */
public class Agregator extends Tarea { //XD

    private Slot input;
    private Slot output;

    private String rootTag;
    private String idTag;
    private String groupTag;
    private String info1Tag;
    private String info2Tag;
    private String info3Tag;

    private XPathFactory xfactory;
    private XPath xpath;
    private String xPathQueryInfo1;
    private String xPathQueryInfo2;
    private String xPathQueryInfo3;

    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;

    public Agregator(EnumTarea t, ArrayList<Slot> se, ArrayList<Slot> sl) {
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

    public void setxPathQueryInfo1(String xPathQueryInfo1) {
        this.xPathQueryInfo1 = xPathQueryInfo1;
    }

    public void setxPathQueryInfo2(String xPathQueryInfo2) {
        this.xPathQueryInfo2 = xPathQueryInfo2;
    }

    public void setxPathQueryInfo3(String xPathQueryInfo3) {
        this.xPathQueryInfo3 = xPathQueryInfo3;
    }

    public void setInfo1Tag(String info1Tag) {
        this.info1Tag = info1Tag;
    }

    public void setInfo2Tag(String info2Tag) {
        this.info2Tag = info2Tag;
    }

    public void setInfo3Tag(String info3Tag) {
        this.info3Tag = info3Tag;
    }

    public void setRootTag(String rootTag) {
        this.rootTag = rootTag;
    }

    public void setIdTag(String idTag) {
        this.idTag = idTag;
    }

    public void setGroupTag(String groupTag) {
        this.groupTag = groupTag;
    }

    @Override
    public void procesar() {
        ArrayList<Mensaje> lMensajesEntrada = new ArrayList<>();
        while (!this.input.isEmpty()) {
            lMensajesEntrada.add(input.getMensaje());
        }

        Mensaje tmp = lMensajesEntrada.getFirst();
        System.out.println("El mensaje " + tmp.getIdDocument() + "espera " + tmp.getnSegments() + " nSegmentos");

        Document docUnido = dBuilder.newDocument();
        Element rootElement = docUnido.createElement(rootTag);
        docUnido.appendChild(rootElement);

        Element id = docUnido.createElement(idTag);
        Text nodeId = docUnido.createTextNode("" + tmp.getIdDocument());
        id.appendChild(nodeId);
        rootElement.appendChild(id);

        Element group = docUnido.createElement(groupTag);
        rootElement.appendChild(group);

        // int nSegmentos = tmp.getnSegments();
        for (Mensaje mensaje : lMensajesEntrada) {
            System.out.println("Se va a procesar el mensaje " + mensaje.getIdSegment() + " de los " + mensaje.getnSegments() + " segmentos");
            NodeList listaInfo1 = null;
            try {
                listaInfo1 = (NodeList) xpath.compile(xPathQueryInfo1).evaluate(mensaje.getDocument(), XPathConstants.NODESET);
            } catch (XPathExpressionException ex) {
                System.out.println("Error al extraer la info1 Agregator: " + ex.getMessage());
            }
            NodeList listaInfo2 = null;
            try {
                listaInfo2 = (NodeList) xpath.compile(xPathQueryInfo2).evaluate(mensaje.getDocument(), XPathConstants.NODESET);
            } catch (XPathExpressionException ex) {
                System.out.println("Error al extraer la info2 Agregator: " + ex.getMessage());
            }
            NodeList listaInfo3 = null;
            try {
                listaInfo3 = (NodeList) xpath.compile(xPathQueryInfo3).evaluate(mensaje.getDocument(), XPathConstants.NODESET);
            } catch (XPathExpressionException ex) {
                System.out.println("Error al extraer la info3 Agregator: " + ex.getMessage());
            }

            String info1 = listaInfo1.item(0).getTextContent();
            String info2 = listaInfo2.item(0).getTextContent();
            String info3 = listaInfo3.item(0).getTextContent();

            Element itemInfo1 = docUnido.createElement(info1Tag);
            Text nodeInfo1Value = docUnido.createTextNode(info1);
            itemInfo1.appendChild(nodeInfo1Value);
            group.appendChild(itemInfo1);

            Element itemInfo2 = docUnido.createElement(info2Tag);
            Text nodeInfo2Value = docUnido.createTextNode(info2);
            itemInfo2.appendChild(nodeInfo2Value);
            group.appendChild(itemInfo2);

            Element itemInfo3 = docUnido.createElement(info3Tag);
            Text nodeInfo3Value = docUnido.createTextNode(info3);
            itemInfo1.appendChild(nodeInfo3Value);
            group.appendChild(itemInfo3);
        }

        Mensaje unido = new Mensaje(docUnido, tmp.getIdDocument());

        output.addMensaje(unido);
    }

}



        /*//primera iteracion fuera del bucle porque ya la hemos sacado
        Document docTmp = tmp.getDocument();

        NodeList listaInfo1 = null;
        try {
            listaInfo1 = (NodeList) xpath.compile(xPathQueryInfo1).evaluate(docTmp, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            System.out.println("Error al extraer la info1 Agregator: " + ex.getMessage());
        }
        NodeList listaInfo2 = null;
        try {
            listaInfo2 = (NodeList) xpath.compile(xPathQueryInfo2).evaluate(docTmp, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            System.out.println("Error al extraer la info2 Agregator: " + ex.getMessage());
        }
        NodeList listaInfo3 = null;
        try {
            listaInfo3 = (NodeList) xpath.compile(xPathQueryInfo3).evaluate(docTmp, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            System.out.println("Error al extraer la info3 Agregator: " + ex.getMessage());
        }
        String info1 = listaInfo1.item(0).getTextContent();
        String info2 = listaInfo2.item(0).getTextContent();
        String info3 = listaInfo3.item(0).getTextContent();

        Element itemInfo1 = docUnido.createElement(info1Tag);
        Text nodeInfo1Value = docUnido.createTextNode(info1);
        itemInfo1.appendChild(nodeInfo1Value);
        rootElement.appendChild(itemInfo1);

        Element itemInfo2 = docUnido.createElement(info2Tag);
        Text nodeInfo2Value = docUnido.createTextNode(info2);
        itemInfo2.appendChild(nodeInfo2Value);
        rootElement.appendChild(itemInfo2);

        Element itemInfo3 = docUnido.createElement(info3Tag);
        Text nodeInfo3Value = docUnido.createTextNode(info3);
        itemInfo1.appendChild(nodeInfo3Value);
        rootElement.appendChild(itemInfo3);

        for (int i = 0; i < nSegmentos - 1; i++) {
            docTmp = tmp.getDocument();

            listaInfo1 = null;
            try {
                listaInfo1 = (NodeList) xpath.compile(xPathQueryInfo1).evaluate(docTmp, XPathConstants.NODESET);
            } catch (XPathExpressionException ex) {
                System.out.println("Error al extraer la info1 Agregator: " + ex.getMessage());
            }
            listaInfo2 = null;
            try {
                listaInfo2 = (NodeList) xpath.compile(xPathQueryInfo2).evaluate(docTmp, XPathConstants.NODESET);
            } catch (XPathExpressionException ex) {
                System.out.println("Error al extraer la info2 Agregator: " + ex.getMessage());
            }
            listaInfo3 = null;
            try {
                listaInfo3 = (NodeList) xpath.compile(xPathQueryInfo3).evaluate(docTmp, XPathConstants.NODESET);
            } catch (XPathExpressionException ex) {
                System.out.println("Error al extraer la info3 Agregator: " + ex.getMessage());
            }
            info1 = listaInfo1.item(0).getTextContent();
            info2 = listaInfo2.item(0).getTextContent();
            info3 = listaInfo3.item(0).getTextContent();

            itemInfo1 = docUnido.createElement(info1Tag);
            nodeInfo1Value = docUnido.createTextNode(info1);
            itemInfo1.appendChild(nodeInfo1Value);
            rootElement.appendChild(itemInfo1);

            itemInfo2 = docUnido.createElement(info2Tag);
            nodeInfo2Value = docUnido.createTextNode(info2);
            itemInfo2.appendChild(nodeInfo2Value);
            rootElement.appendChild(itemInfo2);

            itemInfo3 = docUnido.createElement(info3Tag);
            nodeInfo3Value = docUnido.createTextNode(info3);
            itemInfo1.appendChild(nodeInfo3Value);
            rootElement.appendChild(itemInfo3);

        }
        Mensaje unido = new Mensaje(docUnido, tmp.getIdDocument());

        output.addMensaje(unido);*/