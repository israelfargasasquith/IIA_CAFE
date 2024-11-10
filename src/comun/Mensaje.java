/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comun;

import java.util.UUID;
import org.w3c.dom.Document;

/**
 *
 * @author israe
 */
public class Mensaje {
    /**
     * Posible necesidad de añadir un atributo ¿Cabecera? Vamos a intentarlo hacer con la propia cabecera que modificaremos de los XML
     */
    private String id;
    private Document mensaje;
    
    public Mensaje(Document msj){
        mensaje = msj;
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Document getMensaje() {
        return mensaje;
    }

    public void setMensaje(Document mensaje) {
        this.mensaje = mensaje;
    }
    
    
        
}
