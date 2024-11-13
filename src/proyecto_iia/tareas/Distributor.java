/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia.tareas;

import comun.Mensaje;
import comun.Slot;
import java.util.ArrayList;
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
    
    public Distributor(EnumTarea t, ArrayList<Slot> se, ArrayList<Slot> sl, String[] Condic, int NumCond) {
        super(t, se, sl);
        
        for(int i = 0; i<NumCond; i++){
          Condiciones[i] = Condic[i];
        }  
        NCondiciones = NumCond;
    }
    
    @Override
    public void procesar() {
        //Obtiene el mensaje
        mensaje = getMensajeEntrada(0);   //Solo tiene 1 entrada
        Element typeElement = (Element) mensaje.getDocument().getElementsByTagName("type").item(0);
        
        //Comprueba con la condición de cada salida
        for (int i = 0; i < getNSalidas(); i++) {
            String typeText = typeElement.getTextContent();
            
            //Comprueba si coincide en el mensaje alguna condición
            for (int j = 0; j < Condiciones.length; j++) {
                if(typeText.contains(Condiciones[i])){
                    super.setMensajeSalida(mensaje, i);                
                }
            }
        }
    }
}
