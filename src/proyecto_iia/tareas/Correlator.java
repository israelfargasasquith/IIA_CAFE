/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia.tareas;

import comun.Mensaje;
import comun.Slot;
import java.util.ArrayList;
import proyecto_iia.tareas.Tarea;
import org.w3c.dom.Element;

/**
 *
 * @author israe
 */
public class Correlator extends Tarea {

    private Mensaje mensaje1;
    private Mensaje mensaje2;
    String nombreetiqueta;

    public Correlator(EnumTarea t, ArrayList<Slot> se, ArrayList<Slot> sl, String et) {
        super(t, se, sl);
        this.nombreetiqueta = et;
    }

    public void setEtiqueta(String e) {// aÃ±adir a diagrama 
        this.nombreetiqueta = e;
    }

    @Override
    public void procesar() {
        System.out.println("Correlator: Entrada 0 Vacia: " + this.isEmpty(0));
        System.out.println("Correlator: Entrada 1 Vacia: " + this.isEmpty(1));
        while (!this.isEmpty(0) && !this.isEmpty(1)) {

            mensaje1 = this.getMensajeEntrada(0);  // Usamos mensaje como auxiliar para el primer mensaje
            mensaje2 = this.getMensajeEntrada(1);  // Usamos mensaje como auxiliar para el segundo mensaje


            System.out.println("Correlator Mensaje1: IDMsg -> " + mensaje1.getIdMsg() + "   IDDocument: " + mensaje1.getIdDocument());
            System.out.println("mensaje1:" + mensaje1.toString());
            System.out.println("Correlator Mensaje2: IDMsg -> " + mensaje2.getIdMsg() + "   IDDocument: " + mensaje2.getIdDocument());
            System.out.println("mensaje2:" + mensaje2.toString());

            if (mensaje1.getDocument() == null) {
                System.out.println("Mensaje 1 es NULO");
            }
            if (mensaje2.getDocument() == null) {
                System.out.println("Mensaje 2 es NULO");
            }

            // Declaramos las variables para las etiquetas order_id
            String orderId1 = "" + mensaje1.getIdDocument();
            String orderId2 = "" + mensaje2.getIdDocument();

            // Comparamos los valores de order_id
            if (!orderId1.equalsIgnoreCase("") && !orderId2.equalsIgnoreCase("") && orderId1.equals(orderId2)) {
                // Si ambos order_id son iguales, enrutar los mensajes
                this.setMensajeSalida(mensaje1, 0);  // Mensaje 1 por la salida 0
                this.setMensajeSalida(mensaje2, 1);  // Mensaje 2 por la salida 1
            } else {
                // Si los order_id no son iguales, no hacer nada o manejar el error
                System.out.println("Los order_id no coinciden. No se enrutan los mensajes.");
            }
        }
    }

}
