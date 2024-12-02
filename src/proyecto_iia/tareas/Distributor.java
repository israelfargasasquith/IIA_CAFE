/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia.tareas;

import comun.Mensaje;
import comun.Slot;
import java.util.ArrayList;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author israe
 */
public class Distributor extends Tarea {

    private Mensaje mensaje;

    private String[] Condiciones;
    private int NCondiciones;
    private String tagALeer;

    public Distributor(EnumTarea t, ArrayList<Slot> se, ArrayList<Slot> sl, String[] Condic, int NumCond, String tagALeer) {
        super(t, se, sl);
        Condiciones = new String[NumCond];
        for (int i = 0; i < NumCond; i++) {
            Condiciones[i] = Condic[i];
        }
        NCondiciones = NumCond;
        this.tagALeer = tagALeer;
    }

    public void setAtributos(String[] Con, int num, String tag) {
        this.Condiciones = Con;
        this.NCondiciones = num;
        this.tagALeer = tag;
    }

    @Override
    public void procesar() {
        //Obtiene el mensaje
        mensaje = getMensajeEntrada(0);   //Solo tiene 1 entrada
        Element typeElement = (Element) mensaje.getDocument().getElementsByTagName(tagALeer).item(0);

        XPathFactory xfactory = XPathFactory.newInstance();
        XPath xpath = xfactory.newXPath();

        for (int i = 0; i < getNSalidas(); i++) {
            try {
                String expression = "boolean(" + tagALeer + "[text()='" + Condiciones[i] + "'])";

                boolean result = (boolean) xpath.evaluate(expression, mensaje.getDocument(), XPathConstants.BOOLEAN);
                if (result) {
                    System.out.println("Sacando mensaje por salida " + i + " ||| Condicion: " + Condiciones[i]);
                    setMensajeSalida(mensaje, i);
                }
            } catch (XPathExpressionException ex) {
                System.out.println("Fallo al evaluar la condicion de la salida " + i);
            }
        }
    }
}
