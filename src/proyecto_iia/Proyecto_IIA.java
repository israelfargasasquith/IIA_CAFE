/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_iia;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            JFileChooser tmp = new JFileChooser();
            tmp.setCurrentDirectory(new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "orders"));
            int aproved = tmp.showDialog(null, JFileChooser.APPROVE_SELECTION);
            if (aproved == JFileChooser.APPROVE_OPTION) {

                File inputFile = tmp.getSelectedFile();
                Document doc = db.parse(inputFile);

                Source inputSource = new StreamSource(inputFile);
                Source xsltSource = new StreamSource(System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "orders" + System.getProperty("file.separator") + "XSLT_Temp.xsl");
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer(xsltSource);
                StreamResult result = new StreamResult("output.xml");
                transformer.transform(inputSource, result);
                
                XPath filter = XPathFactory.newInstance().newXPath();
                String expressionDrinks = "//drink";
                String expressionOrderId = "//order_id";

                NodeList order_id = (NodeList) filter.compile(expressionOrderId).evaluate(doc, XPathConstants.NODESET);
                // NodeList nodos = (NodeList) filter.compile(expressionDrinks).evaluate(doc, XPathConstants.NODESET);

                /*String id = order_id.item(0).getTextContent();
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

        } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException |  TransformerException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
