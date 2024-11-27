/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia.tareas;

import comun.Mensaje;
import comun.Slot;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;

/**
 *
 * @author israe
 */
public class Translator extends Tarea {

    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private final StreamSource xsltSource;
    private final TransformerFactory transformerFactory;
    private Transformer transformer;
    private Mensaje mensajeAProcesar;

    public Translator(EnumTarea t, ArrayList<Slot> se, ArrayList<Slot> sl) {
        super(t, se, sl);
        dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.out.println("Error parsing trasnlator constructor: " + ex.getMessage());
            System.exit(-1);
        }
        xsltSource = new StreamSource(System.getProperty("user.dir") + File.separator + "src" + File.separator + "orders" + File.separator + "XSLT_Translator.xsl");
        transformerFactory = TransformerFactory.newInstance();
        try {
            transformer = transformerFactory.newTransformer(xsltSource);
        } catch (TransformerConfigurationException ex) {
            System.out.println("Error creating transformer, trasnlator constructor: " + ex.getMessage());
        }

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
                System.out.println("Error en procesarSQL translator: " + ex.getMessage());
            }

            Document transformedDoc = (Document) domResult.getNode();

            Mensaje nuevo = new Mensaje(transformedDoc);
            this.setMensajeSalida(nuevo, 0);
        }
    }

}
