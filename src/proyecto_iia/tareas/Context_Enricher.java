/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia.tareas;

import comun.Mensaje;
import comun.Slot;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author sebas
 */
public class Context_Enricher extends Tarea {

    private String etiqueta;
    private Mensaje mensaje;

    public Context_Enricher(EnumTarea t, ArrayList<Slot> se, ArrayList<Slot> sl, String etiqueta) {
        super(t, se, sl);
        this.etiqueta = etiqueta;

    }

    @Override
    public void procesar() {
        mensaje = this.getMensajeEntrada(1);  //usando mensaje como auxiliar
        Element typeElement = null;
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.out.println("Error en tarea context enricher: " + ex.getMessage());
        }
//        Document suppXml = dBuilder.newDocument();
//        Element root = suppXml.createElement("daIgual");
//        suppXml.appendChild(root);
        try {
            typeElement = (Element) mensaje.getDocument().getElementsByTagName(etiqueta).item(0); //Obtenos la etiqueta deseada
        } catch (Exception ex) {
            System.out.println("Error en tarea context enricher: " + ex.getMessage());
        }
        mensaje = this.getMensajeEntrada(0);//obtenemos el mensaje original
        Element rootMensaje = mensaje.getDocument().getDocumentElement();
//      Document pepe = mensaje.getDocument();
//      pepe.appendChild(root);
        if (typeElement != null) {
            try {
                rootMensaje.appendChild(typeElement);  //añadimos la etiqueta al mensaje original
            } catch (Exception ex) {
                System.out.println("Error en tarea context enricher: " + ex.getMessage());
            }
        } else {
            System.out.println("Error al inizializar el typeElement");
        }
        this.setMensajeSalida(mensaje, 0);

    }

}
