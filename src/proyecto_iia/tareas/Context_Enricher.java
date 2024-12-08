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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author sebas
 */
public class Context_Enricher extends Tarea {

    private String etiqueta;
    private String etiquetaContex;
    private Mensaje mensaje;

    public Context_Enricher(EnumTarea t, ArrayList<Slot> se, ArrayList<Slot> sl, String etiqueta, String etiquetaContex) {
        super(t, se, sl);
        this.etiqueta = etiqueta;
        this.etiquetaContex = etiquetaContex;

    }

    public void setetiqueta(String e) {
        this.etiqueta = e;
    }

    public void setetiquetaContex(String e) { // aÃ±adir al diagrama
        this.etiquetaContex = e;
    }

    @Override
    public void procesar() {
        System.out.println("ContextEnricher: Entrada 0 Vacia: " + this.isEmpty(0));
        System.out.println("ContextEnricher: Entrada 1 Vacia: " + this.isEmpty(1));
        while (!this.isEmpty(0) && !this.isEmpty(1)) {
            mensaje = this.getMensajeEntrada(0);  //usando mensaje como auxiliar, es este, comprobado
            System.out.println("dentro de procesar: \n" + mensaje.toString());
            Element typeElement = null;
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            try {
                dBuilder = dbFactory.newDocumentBuilder();
            } catch (ParserConfigurationException ex) {
                System.out.println("Error en dBuilder de la tarea context enricher : " + ex.getMessage());
            }

            String precio = "";
            try {
                typeElement = (Element) mensaje.getDocument().getElementsByTagName(etiquetaContex).item(0); //Obtenos la etiqueta deseada
            } catch (Exception ex) {
                System.out.println("Error en tarea context enricher: " + ex.getMessage());
            }
            mensaje = this.getMensajeEntrada(1);//obtenemos el mensaje original
            System.out.println(mensaje.getDocument().toString());
            Element rootMensaje = mensaje.getDocument().getDocumentElement();
            precio = typeElement.getTextContent();

            System.out.println("Precio: " + precio);
            typeElement.setTextContent(precio);
            if (typeElement != null) {
                try {
                    NodeList ListaNodos = mensaje.getDocument().getElementsByTagName(etiqueta);
                    Node Nodo1 = ListaNodos.item(0);
                    Node importado = mensaje.getDocument().importNode(typeElement, true);
                    Nodo1.appendChild(importado);

                } catch (Exception ex) {
                    System.out.println("Error en tarea context enricher: " + ex.getMessage());
                }
            } else {
                System.out.println("Error al inizializar el typeElement");
            }
            this.setMensajeSalida(mensaje, 0);
        }

    }

}
