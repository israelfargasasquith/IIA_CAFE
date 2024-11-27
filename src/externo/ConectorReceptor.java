/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package externo;

import java.io.File;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

/**
 *
 * @author israe
 */
public class ConectorReceptor {

    private int nOutput;

    public ConectorReceptor() {
        nOutput = 0;
    }

    public void generarSalida(String tipoSalida, Document doc) {

        if (tipoSalida.equalsIgnoreCase("ficheros")) {
            try {

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "generatedOutput" + File.separator + "output" + nOutput + ".xml"));
                transformer.transform(source, result);
                System.out.println("Generada nueva salida con el nombre output" + nOutput + ".xml");
                nOutput++;

            } catch (TransformerException ex) {
                System.out.println("Error de Transformer puerto salida:" + ex.getMessage());
                System.exit(1);
            } catch (Exception ex) {
                System.out.println("Error inexperado : " + ex.getMessage());
                System.exit(1);
            }
        } else {
                System.out.println(doc);
        }
    }

}
