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
public class Agregator extends Tarea {

    private ArrayList<Mensaje> mensajesAUnir;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Source xsltSource;
    private TransformerFactory transformerFactory;
    private Transformer transformer;

    public Agregator(EnumTarea t, ArrayList<Slot> se, ArrayList<Slot> sl) {
        super(t, se, sl);
        mensajesAUnir = new ArrayList<>();
        dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            xsltSource = new StreamSource(System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "orders" + System.getProperty("file.separator") + "XSLT_Agregator.xsl");
        } catch (ParserConfigurationException ex) {
            System.out.println("Error parsing agregator constructor: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Error source not found, agregator constructor: " + ex.getMessage());
            ex.printStackTrace();
        }

        transformerFactory = TransformerFactory.newInstance();
        try {
            transformer = transformerFactory.newTransformer(xsltSource);
        } catch (TransformerConfigurationException ex) {
            System.out.println("Error creating transformer, agregator constructor: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(-1);
        }

    }

    @Override
    public void procesar() {
        while (!this.isEmpty(0)) {

            mensajesAUnir.add(this.getMensajeEntrada(0));

            try {
                DOMResult domResult = new DOMResult();
                for (Mensaje mensaje : mensajesAUnir) {
                    DOMSource input = new DOMSource(mensaje.getDocument());

                    transformer.transform(input, domResult);

                }
                Document mergedDocument = (Document) domResult.getNode();

                Mensaje nuevo = new Mensaje(mergedDocument);

                this.setMensajeSalida(nuevo, 0);

            } catch (TransformerException ex) {
                System.out.println("Error de Transformer" + ex.getMessage());
                System.exit(4);
            } catch (Exception ex) {
                System.out.println("Error inexperado en main: " + ex.getMessage());
                System.exit(6);
            }

        }
    }

}
