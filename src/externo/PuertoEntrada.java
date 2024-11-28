/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package externo;

import comun.Mensaje;
import comun.Slot;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author israe
 */
public class PuertoEntrada {

    private Slot slotSalida;
    private XPath xPath;//nuevo
    private String query;//nuevo

    public PuertoEntrada(Slot slotSalida) {
        this.slotSalida = slotSalida;
        xPath = XPathFactory.newInstance().newXPath();
    }

    public String getXPathQuery() { //nuevo
        return query;
    }

    public void setQuery(String query) {//nuevo
        this.query = query;
    }

    public void generarEntrada(Document input) {
        System.out.println("Salida del puerto de entrada generada");
        NodeList id = null;
        try {
            id = (NodeList) xPath.compile(query).evaluate(input, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            System.out.println("Error Puerto entrada al extraer los nodos: " + ex.getMessage());
        }
        Mensaje nuevo;
        System.out.println("id PuertoEntrada: "+Integer.parseInt(id.item(0).getTextContent()));
        if (id != null) {
            nuevo = new Mensaje(input, Integer.parseInt(id.item(0).getTextContent()));
        } else {
            nuevo = new Mensaje(input, -1);
        }
        slotSalida.addMensaje(nuevo);
    }

}
