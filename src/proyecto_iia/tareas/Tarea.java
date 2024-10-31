/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyecto_iia.tareas;

import java.util.ArrayList;
import org.w3c.dom.Document;
import comun.Slot;

/**
 *
 * @author israe
 */
abstract public class Tarea {
    private EnumTarea tipo;
    private ArrayList<Slot> slotsEntrada;
    private ArrayList<Slot> slotsSalida;
    
    abstract public Document procesar();
}
