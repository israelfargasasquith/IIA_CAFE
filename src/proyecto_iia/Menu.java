/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Menu implements IMenu{
    
    public Menu(){
        CrearBebida();
    }
    
    
    
    public Bebida CrearBebida(){
        int m1 = 0;
        Bebida beb = new Bebida();
        do{
        Scanner opc1 = new Scanner(System.in);
        System.out.println("Elige un tipo de bebida: ");
        System.out.println("1- Bebida Caliente");
        System.out.println("2- Bebida Fria");
        System.out.println("3- Salir");
        System.out.print("    Eleccion ---> ");
        m1 = opc1.nextInt();
        
        
            switch(m1){
                case 1:
                    String tipo = "Caliente";
                    CreadorConcreto c = new CreadorConcreto(tipo);
                    beb = c.Factory_Method();
                    System.out.println("\nBebida Caliente Creada\n");
                    break;
                case 2:
                    String tipo1 = "Fria";
                    CreadorConcreto c1 = new CreadorConcreto(tipo1);
                    beb = c1.Factory_Method();
                    System.out.println("\nBebida Fria Creada\n");
                    break;
                    
                case 3:
                    System.out.println("Saliendo...");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        System.out.println("");                    
                    }
                    break;                

            }
            
        }while(m1>0 && m1<3);
        
        
        
        
        return beb;
    }
}
