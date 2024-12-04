/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package externo;

import comun.Mensaje;
import comun.Slot;
import org.w3c.dom.Document;

/**
 *
 * @author israe
 */
public class PuertoSolicitud {

    private Slot sEntrada;
    private Slot sSalida;
    private ConectorBD cBD;

    public PuertoSolicitud(ConectorBD cBD, Slot sEntrada, Slot sSalida) {
        this.sEntrada = sEntrada;
        this.sSalida = sSalida;
        this.cBD = cBD;
    }

    public void setsEntrada(Slot sEntrada) {
        this.sEntrada = sEntrada;
    }

    public void setsSalida(Slot sSalida) {
        this.sSalida = sSalida;
    }

    public void setcBD(ConectorBD cBD) {
        this.cBD = cBD;
    }
    
    

    public void leerSolicitud() {
        //llamara al conector de BD con el mensaje procesadon como "query"
        while (!sEntrada.isEmpty()) {
            Mensaje tmp = sEntrada.getMensaje();
            System.out.println("Query lanzada a la BD: " + tmp.toString());
            cBD.consulta(tmp.getDocument(), tmp.getIdDocument(), tmp.getIdSegment(), tmp.getnSegments());
        }
    }

    public void escribeSolicitud(Document doc,int idDocument,int idSegment,int nSegments) {
        //metodo llamado por el conector que escribe en el slot de salida un mensaje con la informacion del precio, es el que debe leer el contextEnricher
        Mensaje nuevo = new Mensaje(doc, idDocument, idSegment, nSegments);
        sSalida.addMensaje(nuevo);
    }

}
