/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_iia;

import comun.Slot;
import externo.ConectorBD;
import externo.ConectorFicheroGenerador;
import externo.ConectorReceptor;
import externo.PuertoEntrada;
import externo.PuertoSalida;
import externo.PuertoSolicitud;
import java.util.ArrayList;
import proyecto_iia.tareas.Agregator;
import proyecto_iia.tareas.Context_Enricher;
import proyecto_iia.tareas.Correlator;
import proyecto_iia.tareas.CreadorConcretoTarea;
import proyecto_iia.tareas.Distributor;
import proyecto_iia.tareas.EnumTarea;
import proyecto_iia.tareas.Merger;
import proyecto_iia.tareas.Replicator;
import proyecto_iia.tareas.Splitter;
import proyecto_iia.tareas.Translator;

/**
 *
 * @author israe
 */
public class SimulacionCafe {

    public static void main(String[] args) {

        /*
        ***************************************************************
        ************ DELCARACION DE SLOTS PRE ASIGNACION **************
        ***************************************************************
         */
        Slot slotEntradaSplitter = new Slot();
        Slot slotSalidaSplitter = new Slot();
        ArrayList<Slot> sEntradaSplitter = new ArrayList<>();
        ArrayList<Slot> sSalidaSplitter = new ArrayList<>();
        sEntradaSplitter.add(slotEntradaSplitter);
        sSalidaSplitter.add(slotSalidaSplitter);

//       Slot slotEntradaDistributor = new Slot(); La de arriba
        Slot slotSalidaDistributor1 = new Slot();
        Slot slotSalidaDistributor2 = new Slot();
        ArrayList<Slot> sSalidaDistributor = new ArrayList<>();
        sSalidaDistributor.add(slotSalidaDistributor1);
        sSalidaDistributor.add(slotSalidaDistributor2);

//       Slot slotEntradaReplicator1 = new Slot(); Las de arriba
//       Slot slotEntradaReplicator2 = new Slot();
        Slot slotSalidaReplicator1A = new Slot();
        Slot slotSalidaReplicator1B = new Slot();
        Slot slotSalidaReplicator2A = new Slot();
        Slot slotSalidaReplicator2B = new Slot();
        ArrayList<Slot> sSalidaReplicator1 = new ArrayList<>();
        sSalidaReplicator1.add(slotSalidaReplicator1A);
        sSalidaReplicator1.add(slotSalidaReplicator1B);
        ArrayList<Slot> sSalidaReplicator2 = new ArrayList<>();
        sSalidaReplicator2.add(slotSalidaReplicator2B);
        sSalidaReplicator2.add(slotSalidaReplicator2A);

//        Slot slotEntradaTraductor1 = new Slot();
        Slot slotSalidaTraductor1 = new Slot();
        ArrayList<Slot> sSalidaTraductor1 = new ArrayList<>();
        sSalidaTraductor1.add(slotSalidaTraductor1);

//        Slot slotEntradaTraductor2 = new Slot();
        Slot slotSalidaTraductor2 = new Slot();
        ArrayList<Slot> sSalidaTraductor2 = new ArrayList<>();
        sSalidaTraductor2.add(slotSalidaTraductor2);

//        Slot slotEntradaCorrelator1A = new Slot();
        Slot slotEntradaCorrelator1B = new Slot(); //respuesta puerto solicitud
        Slot slotSalidaCorrelator1A = new Slot();
        Slot slotSalidaCorrelator1B = new Slot();
        ArrayList<Slot> sEntradaCorrelator1 = new ArrayList<>();
        sEntradaCorrelator1.add(slotEntradaCorrelator1B);
        ArrayList<Slot> sSalidaCorrelator1 = new ArrayList<>();
        sSalidaCorrelator1.add(slotSalidaCorrelator1A);
        sSalidaCorrelator1.add(slotSalidaCorrelator1B);

//        Slot slotEntradaCorrelator2A = new Slot();
        Slot slotEntradaCorrelator2B = new Slot(); //respuesta puerto solicitud
        Slot slotSalidaCorrelator2A = new Slot();
        Slot slotSalidaCorrelator2B = new Slot();
        ArrayList<Slot> sEntradaCorrelator2 = new ArrayList<>();
        sEntradaCorrelator2.add(slotEntradaCorrelator2B);
        ArrayList<Slot> sSalidaCorrelator2 = new ArrayList<>();
        sSalidaCorrelator2.add(slotSalidaCorrelator2A);
        sSalidaCorrelator2.add(slotSalidaCorrelator2B);

//        Slot slotEntradaContentEnricher1A = new Slot();
//        Slot slotEntradaContentEnricher1B = new Slot();
        Slot slotSalidaContentEnricher1 = new Slot();
        ArrayList<Slot> sSalidaContentEnricher1 = new ArrayList<>();
        sSalidaContentEnricher1.add(slotSalidaContentEnricher1);

//        Slot slotSalidaContentEnricher2A = new Slot();
//        Slot slotSalidaContentEnricher2B = new Slot();
        Slot slotSalidaContentEnricher2 = new Slot();
        ArrayList<Slot> sSalidaContentEnricher2 = new ArrayList<>();
        sSalidaContentEnricher2.add(slotSalidaContentEnricher2);

//        Slot slotEntradaMerger = new Slot();
        Slot slotSalidaMerger = new Slot();
        ArrayList<Slot> sSalidaMerger = new ArrayList<>();
        sSalidaMerger.add(slotSalidaMerger);

//        Slot slotEntradaAgregator = new Slot();
        Slot slotSalidaAgregator = new Slot();
        ArrayList<Slot> sSalidaAgregator = new ArrayList<>();
        sSalidaAgregator.add(slotSalidaAgregator);

        /*
        ***************************************************************
        ************ DELCARACION DE CONECTORES PRE ASIGNACION **************
        ***************************************************************
         */
        ConectorFicheroGenerador cGenerador = new ConectorFicheroGenerador(null);
        ConectorBD cSolicitud1 = new ConectorBD();
        ConectorReceptor cReceptor = new ConectorReceptor();

        /*
        ***************************************************************
        ************ DELCARACION DE PUERTOS PRE ASIGNACION **************
        ***************************************************************
         */
        PuertoEntrada pEntrada = new PuertoEntrada(null);
        PuertoSolicitud pSolicitud1 = new PuertoSolicitud(null, null, null);
        PuertoSolicitud pSolicitud2 = new PuertoSolicitud(null, null, null);
        PuertoSalida pSalida = new PuertoSalida(null, null);

        /*
        ***************************************************************
        ************ DELCARACION DE PUERTOS PRE ASIGNACION **************
        ***************************************************************
         */
        CreadorConcretoTarea tareaFactory = new CreadorConcretoTarea();

        Splitter splitter = (Splitter) tareaFactory.Factory_Method(EnumTarea.SPLITTER, null, null);
        Distributor distributor = (Distributor) tareaFactory.Factory_Method(EnumTarea.DISTRIBUTOR, null, null);
        Replicator replicator1 = (Replicator) tareaFactory.Factory_Method(EnumTarea.REPLICATOR, null, null);
        Replicator replicator2 = (Replicator) tareaFactory.Factory_Method(EnumTarea.REPLICATOR, null, null);
        Translator translator1 = (Translator) tareaFactory.Factory_Method(EnumTarea.TRANSLATOR, null, null);
        Translator translator2 = (Translator) tareaFactory.Factory_Method(EnumTarea.TRANSLATOR, null, null);
        Correlator correlator1 = (Correlator) tareaFactory.Factory_Method(EnumTarea.CORRELATOR, null, null);
        Correlator correlator2 = (Correlator) tareaFactory.Factory_Method(EnumTarea.CORRELATOR, null, null);
        Context_Enricher context_Enricher1 = (Context_Enricher) tareaFactory.Factory_Method(EnumTarea.CONTEXT_ENRICHER, null, null);
        Context_Enricher context_Enricher2 = (Context_Enricher) tareaFactory.Factory_Method(EnumTarea.CONTEXT_ENRICHER, null, null);
        Merger merger = (Merger) tareaFactory.Factory_Method(EnumTarea.MERGER, null, null);
        Agregator agregator = (Agregator) tareaFactory.Factory_Method(EnumTarea.AGREGATOR, null, null);

    }

}
