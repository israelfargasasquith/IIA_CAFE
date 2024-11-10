/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia.tareas;

import comun.Mensaje;
import comun.Slot;
import java.util.ArrayList;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import proyecto_iia.tareas.Tarea;
import org.w3c.dom.Document;

/**
 *
 * @author israe
 */
public class Splitter extends Tarea {

    String expresionSeparar;
    String expresionID;
    String expresionName;
    String expresionType;
    Mensaje mensajeAProcesar;
    Source xsltSource;

    public Splitter(EnumTarea t, ArrayList<Slot> se, ArrayList<Slot> sl) {
        super(t, se, sl);
        this.expresionSeparar = "";
        this.expresionID = "";
        this.expresionName = "";
        this.expresionType = "";
    }

    public void setExpresionSeparar(String expresionSeparar) {
        this.expresionSeparar = expresionSeparar;
    }

    public void setExpresionID(String expresionID) {
        this.expresionID = expresionID;
    }

    public void setExpresionName(String expresionName) {
        this.expresionName = expresionName;
    }

    public void setExpresionType(String expresionType) {
        this.expresionType = expresionType;
    }

    @Override
    public void procesar() {
        mensajeAProcesar = this.getslotsSalida().get(0).getMensaje();
        if (mensajeAProcesar != null) {
    
               // Source inputSource = new StreamSource(mensajeAProcesar.getMensaje());
                Source xsltSource = new StreamSource(System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "orders" + System.getProperty("file.separator") + "XSLT_Splitter.xsl");
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                //Transformer transformer = transformerFactory.newTransformer(xsltSource);
        }
    }

}
