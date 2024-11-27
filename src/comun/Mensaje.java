/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comun;

import java.io.StringWriter;
import java.util.UUID;
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
public class Mensaje {

    
    private String idMsg;
    private int idDocument;
    private int nSegments;
    private Document document;

    public Mensaje(Document msj,int idDocument) {
        document = msj;
        idMsg = UUID.randomUUID().toString();
        idDocument = idDocument;
        nSegments = 0;
    }

    public Mensaje(Document msj, int idDocument, int nSegments) {
        document = msj;
        idMsg = UUID.randomUUID().toString();
        this.idDocument = idDocument;
        this.nSegments = nSegments;
    }

    public String getIdMsg() {
        return idMsg;
    }

    public void setIdMsg(String idMsg) {
        this.idMsg = idMsg;
    }

    public int getIdSegment() {
        return idDocument;
    }

    public void setIdSegment(int idDocument) {
        this.idDocument = idDocument;
    }

    public int getnSegments() {
        return nSegments;
    }

    public void setnSegments(int nSegments) {
        this.nSegments = nSegments;
    }

    public Document getDocument() {
        return document;
    }

    public void setMensaje(Document document) {
        this.document = document;
    }

    @Override
    public String toString() {

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StringWriter stringWriter = new StringWriter();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(this.document), new StreamResult(stringWriter));
            String result = stringWriter.toString();
            return result;
        } catch (TransformerException ex) {
            System.out.println("Error tranformacion en toString: " + ex.getMessage());
            System.exit(1);
            return "";
        }
    }

}
