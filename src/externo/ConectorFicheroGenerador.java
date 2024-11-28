/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package externo;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author israe
 */
public class ConectorFicheroGenerador {

    private PuertoEntrada puertoEntrada;

    public ConectorFicheroGenerador(PuertoEntrada puertoEntrada) {
        this.puertoEntrada = puertoEntrada;
    }

    public void generarEntrada() {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.out.println("Error al generar la entrada dbFactory: " + ex.getMessage());
            System.exit(1);
        }
        Document doc = null;
        Random rand = new Random(System.nanoTime());
        try {
            int randomInt = rand.nextInt(1, 9);
            //doc = dBuilder.parse(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "orders"+File.separator+"order"+randomInt+".xml")); //El bueno para cafe
            doc = dBuilder.parse(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "orders" + File.separator + "MensajePruebaTranslator.xml")); //Fichero prueba Translator
            //doc = dBuilder.parse(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "orders" + File.separator + "order1.xml")); //prueba Splitter
            
        } catch (SAXException ex) {
            System.out.println("Error al generar la entrada Parse: " + ex.getMessage());
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("Error al generar la entrada IO: " + ex.getMessage());
            System.exit(1);
        } catch (Exception ex) {
            System.out.println("Error al generar la entrada error no experado: " + ex.getMessage());
            System.exit(1);
        }
        puertoEntrada.generarEntrada(doc);

        System.out.println("Salida del conector externo generada");
    }
}
