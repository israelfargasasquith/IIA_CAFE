/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia;

import comun.Slot;
import externo.ConectorFicheroGenerador;
import externo.PuertoEntrada;

/**
 *
 * @author israe
 */
public class PruebasMensajesID {

    public static void main(String[] args) {
        Slot slotSalida = new Slot();
        PuertoEntrada puertoEntrada = new PuertoEntrada(slotSalida);
        ConectorFicheroGenerador cGenerador = new ConectorFicheroGenerador(puertoEntrada);
        puertoEntrada.setQuery("//order_id");
        cGenerador.generarEntrada();
    
    }

}
