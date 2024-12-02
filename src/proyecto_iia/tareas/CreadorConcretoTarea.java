/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia.tareas;

import comun.Slot;
import java.util.ArrayList;

/**
 *
 * @author JOSE
 */
public class CreadorConcretoTarea {

    private Object TareaConcreta;

    public Object Factory_Method(EnumTarea t, ArrayList<Slot> se, ArrayList<Slot> sl) {
        switch (t) {
            case AGREGATOR:
                TareaConcreta = new Agregator(t, se, sl);
                break;
            case CONTEXT_ENRICHER:
                TareaConcreta = new Context_Enricher(t, se, sl, "","");
                break;
            case CORRELATOR:
                TareaConcreta = new Correlator(t, se, sl,"");
                break;
            case DISTRIBUTOR:
                String[] Condiciones = null;
                TareaConcreta = new Distributor(t, se, sl, Condiciones, 0, "");
                break;
            case MERGER:
                TareaConcreta = new Merger(t, se, sl);
                break;
            case REPLICATOR:
                TareaConcreta = new Replicator(t, se, sl);
                break;
            case SPLITTER:
                TareaConcreta = new Splitter(t, se, sl);
                break;
            case TRANSLATOR:
                TareaConcreta = new Translator(t, se, sl);
                break;
            default:
                TareaConcreta = null;
        }
        return TareaConcreta;
    }

}
