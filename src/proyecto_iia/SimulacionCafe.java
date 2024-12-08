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
        Slot slotSalidaDistributor1 = new Slot();  //Los slots 1 y 2 son los bifurcan en caminos *ver diagrama* en este caso uno va arriba y otro abajo
        Slot slotSalidaDistributor2 = new Slot();
        ArrayList<Slot> sSalidaDistributor = new ArrayList<>();
        sSalidaDistributor.add(slotSalidaDistributor1);
        sSalidaDistributor.add(slotSalidaDistributor2);

        ArrayList<Slot> sEntradaReplicator1 = new ArrayList<>();
        ArrayList<Slot> sEntradaReplicator2 = new ArrayList<>();
        sEntradaReplicator1.add(slotSalidaDistributor1);
        sEntradaReplicator2.add(slotSalidaDistributor2);

//       Slot slotEntradaReplicator1 = new Slot(); Las de arriba
//       Slot slotEntradaReplicator2 = new Slot();
        Slot slotSalidaReplicator1A = new Slot(); //Slot del camino "superior" que vuelve a bifurcar en dos caminos A para el traductor y B para el correlator
        Slot slotSalidaReplicator1B = new Slot();
        Slot slotSalidaReplicator2A = new Slot(); //Slot del camino "inferior" que vuelve a bifurcar en dos caminos A para el traductor y B para el correlator
        Slot slotSalidaReplicator2B = new Slot();
        ArrayList<Slot> sSalidaReplicator1 = new ArrayList<>(); //Este arrayList son las salidas del replicator "superior"
        sSalidaReplicator1.add(slotSalidaReplicator1A);
        sSalidaReplicator1.add(slotSalidaReplicator1B);
        ArrayList<Slot> sSalidaReplicator2 = new ArrayList<>(); //Este arrayList son las salidas del replicator "inferior"
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
        sEntradaCorrelator1.add(slotSalidaReplicator1B);

        ArrayList<Slot> sSalidaCorrelator1 = new ArrayList<>();
        sSalidaCorrelator1.add(slotSalidaCorrelator1A);
        sSalidaCorrelator1.add(slotSalidaCorrelator1B);

//        Slot slotEntradaCorrelator2A = new Slot();
        Slot slotEntradaCorrelator2B = new Slot(); //respuesta puerto solicitud
        Slot slotSalidaCorrelator2A = new Slot();
        Slot slotSalidaCorrelator2B = new Slot();
        ArrayList<Slot> sSalidaCorrelator2 = new ArrayList<>();
        sSalidaCorrelator2.add(slotSalidaCorrelator2A);
        sSalidaCorrelator2.add(slotSalidaCorrelator2B);

        ArrayList<Slot> sEntradaCorrelator2 = new ArrayList<>();
        sEntradaCorrelator2.add(slotEntradaCorrelator2B);
        sEntradaCorrelator2.add(slotSalidaReplicator2B);

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

        ArrayList<Slot> sEntradaMerger = new ArrayList<>();
        sEntradaMerger.add(slotSalidaContentEnricher1);
        sEntradaMerger.add(slotSalidaContentEnricher2);

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

        Splitter splitter = (Splitter) tareaFactory.Factory_Method(EnumTarea.SPLITTER, sEntradaSplitter, sSalidaSplitter);
        Distributor distributor = (Distributor) tareaFactory.Factory_Method(EnumTarea.DISTRIBUTOR, sSalidaSplitter, sSalidaDistributor);
        Replicator replicator1 = (Replicator) tareaFactory.Factory_Method(EnumTarea.REPLICATOR, sEntradaReplicator1, sSalidaReplicator1);
        Replicator replicator2 = (Replicator) tareaFactory.Factory_Method(EnumTarea.REPLICATOR, sEntradaReplicator2, sSalidaReplicator2);
        Translator translator1 = (Translator) tareaFactory.Factory_Method(EnumTarea.TRANSLATOR, sSalidaReplicator1, sSalidaTraductor1);
        Translator translator2 = (Translator) tareaFactory.Factory_Method(EnumTarea.TRANSLATOR, sSalidaReplicator2, sSalidaTraductor2);
        Correlator correlator1 = (Correlator) tareaFactory.Factory_Method(EnumTarea.CORRELATOR, sEntradaCorrelator1, sSalidaCorrelator1);
        Correlator correlator2 = (Correlator) tareaFactory.Factory_Method(EnumTarea.CORRELATOR, sEntradaCorrelator2, sSalidaCorrelator2);
        Context_Enricher context_Enricher1 = (Context_Enricher) tareaFactory.Factory_Method(EnumTarea.CONTEXT_ENRICHER, sSalidaCorrelator1, sSalidaContentEnricher1);
        Context_Enricher context_Enricher2 = (Context_Enricher) tareaFactory.Factory_Method(EnumTarea.CONTEXT_ENRICHER, sSalidaCorrelator2, sSalidaContentEnricher2);
        Merger merger = (Merger) tareaFactory.Factory_Method(EnumTarea.MERGER,sEntradaMerger, sSalidaMerger);
        Agregator agregator = (Agregator) tareaFactory.Factory_Method(EnumTarea.AGREGATOR, sSalidaMerger, sSalidaAgregator);

        /*
        ***************************************************************
        ************ ASIGNACION DE CONECTORES**************
        ***************************************************************
         */
        cGenerador.setPuertoEntrada(pEntrada);
        cSolicitud1.setPuertoSolicitud(pSolicitud1);

        /*
        ***************************************************************
        ************ ASIGNACION DE PUERTOS Y PARAMETROS **************
        ***************************************************************
         */
        pEntrada.setSlotSalida(slotEntradaSplitter);
        pEntrada.setQuery("//order_id");

        pSolicitud1.setcBD(cSolicitud1);
        pSolicitud1.setsEntrada(slotSalidaTraductor1);
        pSolicitud1.setsSalida(slotEntradaCorrelator1B);

        pSolicitud2.setcBD(cSolicitud1);
        pSolicitud2.setsEntrada(slotSalidaTraductor2);
        pSolicitud2.setsSalida(slotEntradaCorrelator2B);

        pSalida.setConectorReceptor(cReceptor);
        pSalida.setSlotEntrada(slotSalidaAgregator);
        /*
        ***************************************************************
        ************ ASIGNACION DE PARAMETROS TAREAS**************
        ***************************************************************
         */

        splitter.setXPathQuerySeparar("//drinks/*");
        splitter.setXPathQueryContar("count(cafe_order/drinks/drink)");
        splitter.setxPathIdMensaje("//order_id");
        splitter.setRootTag("cafe_order");

        String[] condiciones = {"cold", "hot"};
        distributor.setAtributos(condiciones, 2, "//type");

//        replicator1.setEntradaAProcesar(0); //Esto es otra solucion que he pensado si no funcina la de la asignacion de los slots
//        replicator2.setEntradaAProcesar(1);
        correlator1.setEtiqueta("order_id");
        correlator2.setEtiqueta("order_id");

        context_Enricher1.setetiqueta("drink");
        context_Enricher1.setetiquetaContex("price");
        context_Enricher2.setetiqueta("drink");
        context_Enricher2.setetiquetaContex("price");

        agregator.setxPathQueryInfo1("//name");
        agregator.setxPathQueryInfo2("//type");
        agregator.setxPathQueryInfo3("//price");

        agregator.setRootTag("cafe_order");
        agregator.setIdTag("order_id");
        agregator.setGroupTag("drinks");
        agregator.setGroupItemTag("drink");

        agregator.setInfo1Tag("name");
        agregator.setInfo2Tag("type");
        agregator.setInfo3Tag("price");

        /*
        ***************************************************************
        ************ SIMULACION CAFE **************
        ***************************************************************
         */
        cGenerador.generarEntrada();
        //puertoEntrada es llamado por el generador
        splitter.procesar();
        distributor.procesar();
        replicator1.procesar();
        replicator2.procesar();
        translator1.procesar();
        translator2.procesar();
        pSolicitud1.leerSolicitud();
        pSolicitud2.leerSolicitud();
        //El conector de BD escribe en el puerto de forma automatica despues de la consulta
        correlator1.procesar();
        correlator2.procesar();
        context_Enricher1.procesar();
        context_Enricher2.procesar();
        merger.procesar();
        agregator.procesar();
        pSalida.generarSalida();
        //El mismo puerto de salida es el que llama al conector receptor
    }

}
