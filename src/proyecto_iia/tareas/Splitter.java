/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia.tareas;

import comun.Mensaje;
import comun.Slot;
import java.util.ArrayList;
import java.util.UUID;
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

/**
 *
 * @author israe
 */
public class Splitter extends Tarea {

    private Mensaje mensajeAProcesar;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Source xsltSource;
    private TransformerFactory transformerFactory;
    private Transformer transformer;
    private XPathFactory xfactory;
    private XPath xpath;
    private String expresionXPathContarMensajes;
    private String rootName;
    private String xPathQuery;

    public Splitter(EnumTarea t, ArrayList<Slot> se, ArrayList<Slot> sl) {
        super(t, se, sl);
        dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.out.println("Error parsing splitter constructor: " + ex.getMessage());
            System.exit(-1);
        }
        xsltSource = new StreamSource(System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "orders" + System.getProperty("file.separator") + "XSLT_Splitter.xsl");
        transformerFactory = TransformerFactory.newInstance();
        try {
            transformer = transformerFactory.newTransformer(xsltSource);
        } catch (TransformerConfigurationException ex) {
            System.out.println("Error creating transformer, splitter constructor: " + ex.getMessage());
        }

        xfactory = XPathFactory.newInstance();
        xpath = xfactory.newXPath();

        //especificas de Cafe
        expresionXPathContarMensajes = "count(cafe_order/cafe_subOrder)";
        rootName = "cafe_subOrder";
        xPathQuery = "/cafe_order/cafe_subOrder";
    }

    public void setExpresionXPath(String expresion) {
        this.expresionXPathContarMensajes = expresion;
    }

    public void setXSLT(StreamSource nuevoXSLT) throws TransformerConfigurationException {
        this.xsltSource = nuevoXSLT;
        this.transformer = transformerFactory.newTransformer(this.xsltSource);

    }

    public void setRootName(String nuevo) {
        this.rootName = nuevo;
    }

    public void setXPathQuery(String nuevo) {
        this.xPathQuery = nuevo;
    }

    @Override
    public void procesar() {
        while (!this.isEmpty(0)) {
            mensajeAProcesar = this.getMensajeEntrada(0);

            DOMResult domResult = new DOMResult();
            DOMSource input = new DOMSource(mensajeAProcesar.getDocument());

            try {
                transformer.transform(input, domResult);
            } catch (TransformerException ex) {
                System.out.println("Error transformer splitter: " + ex.getMessage());
                return;
            }
            Document transformedDoc = (Document) domResult.getNode();

            XPathExpression mensajesExpression;
            try {
                mensajesExpression = xpath.compile(expresionXPathContarMensajes);
            } catch (XPathExpressionException ex) {
                System.out.println("Error xpath.comile splitter: " + ex.getMessage());
                return;
            }

            Double nMensajesd;
            try {
                nMensajesd = (Double) mensajesExpression.evaluate(transformedDoc, XPathConstants.NUMBER);
            } catch (XPathExpressionException ex) {
                System.out.println("Error xpathEvaluation splitter: " + ex.getMessage());
                return;

            }
            int nMensajes = nMensajesd.intValue();
            String nuevoID = UUID.randomUUID().toString();

            for (int i = 0; i < nMensajes; i++) {

                Document suppXml = dBuilder.newDocument();
                Element root = suppXml.createElement(rootName);
                suppXml.appendChild(root);

                XPathExpression query;
                try {
                    query = xpath.compile(xPathQuery);
                } catch (XPathExpressionException ex) {
                    System.out.println("Error xPath.compile 2 splitter: " + ex.getMessage());
                    return;
                }
                NodeList productNodesFiltered;
                try {
                    productNodesFiltered = (NodeList) query.evaluate(transformedDoc, XPathConstants.NODESET);
                } catch (XPathExpressionException ex) {
                    System.out.println("Error query evaluate 2 splitter: " + ex.getMessage());
                    return;
                }
                
                if (i < productNodesFiltered.getLength()) {
                    Node productNode = productNodesFiltered.item(i);
                    Node clonedNode = suppXml.importNode(productNode, true);

                    root.appendChild(clonedNode);
                }
                Mensaje nuevo = new Mensaje(suppXml, i, nMensajes);
                nuevo.setIdMsg(nuevoID);
                System.out.println("Added nuevo mensaje en la salida");
                this.setMensajeSalida(nuevo, 0);
            }

        }
    }
}
