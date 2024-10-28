/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia;

/**
 *
 * @author JOSE
 */
public class CreadorConcreto extends Creador{
    
    private String tipo;
    
    public CreadorConcreto(String tipo){
        this.tipo = tipo;
    }
    
    @Override
    public Bebida Factory_Method(){
        if(tipo.equalsIgnoreCase("Caliente"))
            return new Bebida_Caliente();
        else if(tipo.equalsIgnoreCase("Fria"))
            return new Bebida_Fria();
        else
           return null;
    }
}
