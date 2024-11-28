/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia;

import comun.Slot;
import externo.ConectorBD;
import externo.ConectorFicheroGenerador;
import externo.ConectorReceptor;
import externo.PuertoEntrada;
import externo.PuertoSalida;
import externo.PuertoSolicitud;
import java.util.ArrayList;
import proyecto_iia.tareas.EnumTarea;
import proyecto_iia.tareas.Translator;

/**
 *
 * @author israe
 */
public class PrototipoPruebaBD {

    public static void main(String[] args) {
        System.out.println("Isra's translator main");
        Slot sEntrada = new Slot();
        Slot sSalida = new Slot();
        Slot sSalidaSolicitud = new Slot();
        ArrayList<Slot> arraySlotEntrada = new ArrayList<>();
        ArrayList<Slot> arraySlotSalida = new ArrayList<>();
        arraySlotEntrada.add(sEntrada);
        arraySlotSalida.add(sSalida);

        PuertoEntrada puertoEntrada = new PuertoEntrada(sEntrada);
        puertoEntrada.setQuery("//order_id");

        ConectorFicheroGenerador cGenerador = new ConectorFicheroGenerador(puertoEntrada);
        ConectorReceptor cReceptor = new ConectorReceptor();

        PuertoSalida puertoSalida = new PuertoSalida(sSalidaSolicitud, cReceptor);

        ConectorBD conectorBD = new ConectorBD();

        PuertoSolicitud puertoSolicitud = new PuertoSolicitud(conectorBD, sSalida, sSalidaSolicitud);
        conectorBD.setPuertoSolicitud(puertoSolicitud);

        Translator tareaPrueba = new Translator(EnumTarea.TRANSLATOR, arraySlotEntrada, arraySlotSalida);

        cGenerador.generarEntrada();
        tareaPrueba.procesar();
        puertoSolicitud.leerSolicitud();
        puertoSalida.generarSalida();
    }
}
