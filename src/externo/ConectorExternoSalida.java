/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package externo;

import comun.Slot;
import java.io.File;
import java.util.ArrayList;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author israe
 */
public class ConectorExternoSalida {

    private Slot slotEntrada;

    public ConectorExternoSalida(Slot slotEntrada) {
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
                System.out.println("Generada nueva salida con el nombre output" + nOutput + ".xml");
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

