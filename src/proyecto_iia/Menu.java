/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia;

/**
 *
 * @author Usuario
 */
public class Menu implements IMenu{
    
    public Menu(){
        System.out.println("Creando Bebida Caliente...");
        CrearBebida();
        
        System.out.println("Bebida Caliente Creada.");
    }
    
    
    
    public Bebida CrearBebida(){
        Bebida beb = new Bebida();
        String tipo = "Caliente";
        
        //Pedir el tipo
        
        CreadorConcreto c = new CreadorConcreto(tipo);
        beb = c.Factory_Method();
        
        return beb;
    }
}
