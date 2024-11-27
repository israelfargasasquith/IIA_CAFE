/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_iia;

import comun.Slot;
import externo.ConectorFicheroGenerador;
import externo.ConectorReceptor;
import externo.PuertoEntrada;
import externo.PuertoSalida;
import java.util.ArrayList;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import proyecto_iia.tareas.EnumTarea;
import proyecto_iia.tareas.Splitter;
import proyecto_iia.tareas.Tarea;

/**
 *
 * @author Usuario
 */
public class PrototipoSplitter {

    public static void main(String[] args) throws TransformerConfigurationException, TransformerException {

        Slot sEntrada = new Slot();
        Slot sSalida = new Slot();
        ArrayList<Slot> arraySlotEntrada = new ArrayList<>();
        ArrayList<Slot> arraySlotSalida = new ArrayList<>();
        arraySlotEntrada.add(sEntrada);
        arraySlotSalida.add(sSalida);

        PuertoEntrada puertoEntrada = new PuertoEntrada(sEntrada);

        ConectorFicheroGenerador cGenerador = new ConectorFicheroGenerador(puertoEntrada);
        ConectorReceptor cReceptor = new ConectorReceptor();

        PuertoSalida puertoSalida = new PuertoSalida(sSalida, cReceptor);

        Splitter tareaPrueba = new Splitter(EnumTarea.SPLITTER, arraySlotEntrada, arraySlotSalida);
        tareaPrueba.setXPathQuery("//drinks/*");

        cGenerador.generarEntrada();
        tareaPrueba.procesar();
        puertoSalida.generarSalida();

    }
}
