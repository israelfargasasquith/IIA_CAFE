/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package externo;

import comun.Slot;

/**
 *
 * @author israe
 */
public class PuertoSolicitud {

    private Slot sEntrada;
    private Slot sSalida;

    public PuertoSolicitud(Slot sEntrada, Slot sSalida) {
        this.sEntrada = sEntrada;
        this.sSalida = sSalida;
    }

    public void leerSolicitud() {
        //llamara al conector de BD con el mensaje procesadon como "query"
    }

    public void escribeSolicitud(String respuesta) {
        //metodo llamado por el conector que escribe en el slot de salida un mensaje con la informacion del precio, es el que debe leer el contextEnricher
    }

}
