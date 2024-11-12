/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia.tareas;

import comun.Mensaje;
import comun.Slot;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
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
import proyecto_iia.tareas.Tarea;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author israe
 */
public class Splitter extends Tarea {

    String expresionSeparar;
    String expresionID;
    String expresionName;
    String expresionType;
    Mensaje mensajeAProcesar;

    public Splitter(EnumTarea t, ArrayList<Slot> se, ArrayList<Slot> sl) {
        super(t, se, sl);
        this.expresionSeparar = "";
        this.expresionID = "";
        this.expresionName = "";
        this.expresionType = "";
    }

    @Override
    public void procesar() {
        while (!this.isEmpty(0)) {
            mensajeAProcesar = this.getMensajeEntrada(0);

            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

                DOMResult domResult = new DOMResult();
                DOMSource input = new DOMSource(mensajeAProcesar.getDocument());
                Source xsltSource = new StreamSource(System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "orders" + System.getProperty("file.separator") + "XSLT_Splitter.xsl");
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer(xsltSource);

                transformer.setParameter("varSplitter", "drinks");
                transformer.setParameter("varOriginal_ID", "order_id");
                transformer.setParameter("varName", "name");
                transformer.setParameter("varType", "type");

                transformer.transform(input, domResult);
                Document transformedDoc = (Document) domResult.getNode();

                XPathFactory xfactory = XPathFactory.newInstance();
                XPath xpath = xfactory.newXPath();
                XPathExpression mensajesExpression = xpath.compile("count(mensajes/mensaje)");
                Double nMensajesd = (Double) mensajesExpression.evaluate(transformedDoc, XPathConstants.NUMBER);
                int nMensajes = nMensajesd.intValue();
                //System.out.println("valor de numeroDeMensajes:" + nMensajes);

                //Now we create the split XMLs
                for (int i = 0; i < nMensajes; i++) {

                    Document suppXml = dBuilder.newDocument();
                    Element root = suppXml.createElement("mensajes");
                    suppXml.appendChild(root);

                    String xpathQuery = "/mensajes/mensaje";
                    XPathExpression query = xpath.compile(xpathQuery);
                    NodeList productNodesFiltered = (NodeList) query.evaluate(transformedDoc, XPathConstants.NODESET);

                    // Add each <mensaje> node to the new document
                    if (i < productNodesFiltered.getLength()) { // Ensure index is in bounds
                        Node productNode = productNodesFiltered.item(i);
                        Node clonedNode = suppXml.importNode(productNode, true);  // Clone node

                        root.appendChild(clonedNode);  // Append to the new document's root
                    }
                    //System.out.println("añadimos un mensaje al array salida");
                    this.setMensajeSalida(new Mensaje(suppXml), 0);
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

    public void setExpresionSeparar(String expresionSeparar) {
        this.expresionSeparar = expresionSeparar;
    }

    public void setExpresionID(String expresionID) {
        this.expresionID = expresionID;
    }

    public void setExpresionName(String expresionName) {
        this.expresionName = expresionName;
    }

    public void setExpresionType(String expresionType) {
        this.expresionType = expresionType;
    }

}
