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
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author israe
 */
public class PuertoEntrada {

    private Slot slotSalida;

    public PuertoEntrada(Slot slotSalida) {
        this.slotSalida = slotSalida;
    }

    public void generarEntrada() {

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            JFileChooser tmp = new JFileChooser();
            tmp.setCurrentDirectory(new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "orders"));

            int aproved = tmp.showDialog(null, JFileChooser.APPROVE_SELECTION);
            if (aproved == JFileChooser.APPROVE_OPTION) {
                File inputFile = tmp.getSelectedFile();
                Document doc = dBuilder.parse(inputFile);
                slotSalida.addMensaje(new Mensaje(doc));
            } else {
                //Introducir por teclado? Solicitar que elija alguno? Dialogo de salida?
            }
        } catch (ParserConfigurationException ex) {
            System.out.println("Error de parsing:" + ex.getMessage());
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("Error de IO: " + ex.getMessage());
            System.exit(2);
        } catch (SAXException ex) {
            System.out.println("Error de sax: " + ex.getMessage());
            System.exit(3);
        }
    }

}
