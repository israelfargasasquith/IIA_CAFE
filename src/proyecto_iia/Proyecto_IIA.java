/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_iia;

import comun.Mensaje;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Usuario
 */
public class Proyecto_IIA {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            JFileChooser tmp = new JFileChooser();
            tmp.setCurrentDirectory(new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "orders"));

            int aproved = tmp.showDialog(null, JFileChooser.APPROVE_SELECTION);
            if (aproved == JFileChooser.APPROVE_OPTION) {

                File inputFile = tmp.getSelectedFile();
                Document doc = db.parse(inputFile);

                Source inputSource = new StreamSource(inputFile);
                Source xsltSource = new StreamSource(System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "orders" + System.getProperty("file.separator") + "XSLT_Splitter.xsl");
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer(xsltSource);
                DOMResult result = new DOMResult();

                transformer.setParameter("varSplitter", "drinks");
                transformer.setParameter("varOriginal_ID", "order_id");
                transformer.setParameter("varName", "name");
                transformer.setParameter("varType", "type");

                DOMResult domResult = new DOMResult();
                transformer.transform(inputSource, domResult);
                Document transformedDoc = (Document) domResult.getNode();

                NodeList mensajeNodes = transformedDoc.getElementsByTagName("mensaje");

                List<Mensaje> mensajesList = new ArrayList<>();
                for (int i = 0; i < mensajeNodes.getLength(); i++) {
                    // Extract each <mensaje> node as a new Document
                    Node mensajeNode = mensajeNodes.item(i);

                    // Create a new Document to hold just this <mensaje> element
                    Document mensajeDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

                    // Import mensajeNode into mensajeDoc, so it becomes part of this new Document
                    Node importedMensaje = mensajeDoc.importNode(mensajeNode, true);

                    // Append the imported <mensaje> as the root element of the new Document
                    mensajeDoc.appendChild(importedMensaje);

                    // Create a Mensaje object for each <mensaje> Document
                    Mensaje mensajeObj = new Mensaje(mensajeDoc);
                    mensajesList.add(mensajeObj);
                }/*

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
            }
        } catch (ParserConfigurationException ex) {
            System.out.println("Error:" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
