/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package externo;

import comun.Mensaje;
import comun.Slot;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author israe
 */
public class PuertoSalida {

    private Slot slotEntrada;

    public PuertoSalida(Slot slotEntrada) {
        this.slotEntrada = slotEntrada;
    }

    public void generarSalida() {

        try {
            int nOutput = 0;
            while (!slotEntrada.isEmpty()) {

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(slotEntrada.getMensaje().getDocument());
                StreamResult result = new StreamResult(new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "generatedOutput" + System.getProperty("file.separator") + "output" + nOutput + ".xml"));
                transformer.transform(source, result);
                nOutput++;
            }

        } catch (TransformerException ex) {
            System.out.println("Error de Transformer puerto salida:" + ex.getMessage());
            System.exit(1);
        } catch (Exception ex) {
            System.out.println("Error inexperado : " + ex.getMessage());
            System.exit(2);
        }
    }
}
