/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia.tareas;

import comun.Mensaje;
import comun.Slot;
import java.util.ArrayList;
import org.w3c.dom.Document;

/**
 *
 * @author israe
 */
public class Distributor extends Tarea {

    private Slot SlotEntrada = new Slot();
    private Slot[] SlotSalida = new Slot[5];
    
    private Mensaje mensaje = new Mensaje();

    
    private String[] Condiciones;
    private int NCondiciones;
    
    public Distributor(EnumTarea t, ArrayList<Slot> se, ArrayList<Slot> sl, String[] Condic, int NumCond) {
        super(t, se, sl);
        
        for(int i = 0; i<NumCond; i++){
          Condiciones[i] = Condic[i];
        }  
        NCondiciones = NumCond;
        
        //Obtiene el mensaje
        mensaje = getMensajeEntrada(0);   //Solo tiene 1 entrada
    }
    
    @Override
    public void procesar() {
        // Lo coloca en las salidas
        for (int i = 0; i < getNSalidas(); i++) {
            //if(mensaje.getCond()[i].equals(Condiciones[i])){
            super.setMensajeSalida(mensaje, i);
            //}     Envía el mensaje completo, mirar posible fragmentación en el distributor
        }
        sigMensaje();
    }
    
    public ArrayList<Slot> enviarReplicados(){
        return getslotsSalida();
    }
    
    public void setCondiciones(){
        
    }
    
    private void sigMensaje(){
        mensaje = getMensajeEntrada(0);
    }
    
}
