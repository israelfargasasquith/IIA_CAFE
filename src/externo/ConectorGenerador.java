/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package externo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
public class ConectorGenerador {

    public ArrayList<Document> generarEntrada() {
        try {
            ArrayList<Document> devolver = new ArrayList<>();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            JFileChooser tmp = new JFileChooser();
            tmp.setCurrentDirectory(new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "orders"));

            int aproved = tmp.showDialog(null, JFileChooser.APPROVE_SELECTION);
            if (aproved == JFileChooser.APPROVE_OPTION) {
                File inputFile = tmp.getSelectedFile();
                Document doc = dBuilder.parse(inputFile);
                devolver.add(doc);
                System.out.println("Salida del conector externo generada");
                return devolver;
            } else {
                //Introducir por teclado? Solicitar que elija alguno? Dialogo de salida?
                return devolver;
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
        return null;

    }
}
