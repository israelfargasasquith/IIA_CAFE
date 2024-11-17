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

    }

    @Override
    public void procesar() {
        while (!this.isEmpty(0)) {
            mensajeAProcesar = this.getMensajeEntrada(0);

            try {

                DOMResult domResult = new DOMResult();
                DOMSource input = new DOMSource(mensajeAProcesar.getDocument());

                transformer.transform(input, domResult);
                Document transformedDoc = (Document) domResult.getNode();

                XPathFactory xfactory = XPathFactory.newInstance();
                XPath xpath = xfactory.newXPath();
                XPathExpression mensajesExpression = xpath.compile("count(cafe_order/cafe_subOrder)");
                Double nMensajesd = (Double) mensajesExpression.evaluate(transformedDoc, XPathConstants.NUMBER);
                int nMensajes = nMensajesd.intValue();
                String nuevoID = UUID.randomUUID().toString();

                for (int i = 0; i < nMensajes; i++) {

                    Document suppXml = dBuilder.newDocument();
                    Element root = suppXml.createElement("cafe_subOrder");
                    suppXml.appendChild(root);

                    String xpathQuery = "/cafe_order/cafe_subOrder";
                    XPathExpression query = xpath.compile(xpathQuery);
                    NodeList productNodesFiltered = (NodeList) query.evaluate(transformedDoc, XPathConstants.NODESET);

                    if (i < productNodesFiltered.getLength()) {
                        Node productNode = productNodesFiltered.item(i);
                        Node clonedNode = suppXml.importNode(productNode, true);

                        root.appendChild(clonedNode);
                    }
                    Mensaje nuevo = new Mensaje(suppXml, i, nMensajes);
                    nuevo.setIdMsg(nuevoID);

                    this.setMensajeSalida(nuevo, 0);
                }

            } catch (TransformerException ex) {
                System.out.println("Error de Transformer" + ex.getMessage());
                System.exit(4);
            } catch (XPathExpressionException ex) {
                System.out.println("Error de XPath" + ex.getMessage());
                System.exit(5);
            } catch (Exception ex) {
                System.out.println("Error inexperado en main: " + ex.getMessage());
                System.exit(6);
            }

        }
    }
}
