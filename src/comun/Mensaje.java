/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comun;

import java.io.StringWriter;
import java.util.UUID;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

/**
 *
 * @author israe
 */
public class Mensaje {

    /**
     * Posible necesidad de añadir un atributo ¿Cabecera? Vamos a intentarlo
     * hacer con la propia cabecera que modificaremos de los XML
     */
    private String id;
    private Document mensaje;

    public Mensaje(Document msj) {
        mensaje = msj;
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Document getDocument() {
        return mensaje;
    }

    public void setMensaje(Document mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StringWriter stringWriter = new StringWriter();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(this.mensaje), new StreamResult(stringWriter));
            String result = stringWriter.toString();
            return result;
        } catch (TransformerException ex) {
            System.out.println("Error tranformacion en toString: " + ex.getMessage());
            System.exit(6);
            return "";
        }
    }

}
