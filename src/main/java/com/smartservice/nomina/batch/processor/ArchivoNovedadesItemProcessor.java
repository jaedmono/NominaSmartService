package com.smartservice.nomina.batch.processor;


import com.opencsv.bean.CsvToBeanBuilder;
import com.smartservice.nomina.batch.ArchivoNovedadContent;
import com.smartservice.nomina.model.*;
import com.smartservice.nomina.processor.NovedadProcessor;
import com.smartservice.nomina.processor.concept.BasicConcept;
import com.smartservice.nomina.processor.concept.ConceptFactory;
import com.smartservice.nomina.service.impl.ConceptoServiceImpl;
import com.smartservice.nomina.service.impl.ContratoServiceImpl;
import com.smartservice.nomina.service.impl.EmpleadoServiceImpl;
import com.smartservice.nomina.util.EstadoContrato;
import com.smartservice.nomina.util.EstadosNovedadesCargadas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArchivoNovedadesItemProcessor implements ItemProcessor<ArchivoNovedad,ArchivoNovedadContent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArchivoNovedadesItemProcessor.class);

    private static final String MENSAJE_EMPLEADO = "No se encontro un contrato activo para el empleado con este documento: %s";
    private static final String MENSAJE_CONTRATO = "No se encontro un empleado con este documento: %s";
    private static final String MENSAJE_TOPE_MAXIMO = "El concepto de la novedad excede el tope maximo permitido: %s";
    private static final String MENSAJE_VALOR_CERO = "No se ingresó un valor para el concepto de la novedad";
    private static final String MENSAJE_CORRECTO = "Novedad validada correctamente y sin problemas";
    private static final String PATH_FILE = "E:/SmartServiceProjects/NominaSmartService/fileUpload/";

    private Contrato contrato ;
    private Empleado empleado;
    private String currentDocument = "";


    @Autowired
    private ConceptoServiceImpl conceptoService;

    @Autowired
    private EmpleadoServiceImpl empleadoService;

    @Autowired
    private ContratoServiceImpl contratoService;

    @Autowired
    private NovedadProcessor novedadProcessor;

    @Override
    public ArchivoNovedadContent process(ArchivoNovedad item) throws Exception {
        LOGGER.info(String.format("Se va a procesar el item: %s",item) );
        List<ArchivoNovedadesRecords> archivoNovedadesRecords = getArchivoNovedadesRecords(item);
        ArchivoNovedadContent archivoNovedadContent = new ArchivoNovedadContent();
        archivoNovedadContent.setNovedadValidadas(validarNovedades(archivoNovedadesRecords,item));
        archivoNovedadContent.setArchivoNovedad(item);
        return archivoNovedadContent;
    }

    private List<ArchivoNovedadesRecords> getArchivoNovedadesRecords(ArchivoNovedad item) throws FileNotFoundException {

            return (List<ArchivoNovedadesRecords>) new CsvToBeanBuilder(new FileReader(PATH_FILE + item.getNombreArchivo()))
                    .withType(ArchivoNovedadesRecords.class).build().parse();

    }

    private List<NovedadValidada> validarNovedades(List<ArchivoNovedadesRecords> archivoNovedadesRecords, ArchivoNovedad archivoNovedad) {
        List<NovedadValidada> novedadesValidadas = new ArrayList<>();
        NovedadValidada novedad;
        for (ArchivoNovedadesRecords novedadesRecord: archivoNovedadesRecords ) {
            LOGGER.info("Novedad cargada: "+novedadesRecord);
            loadEmpleadoContrato(novedadesRecord.getDocumento());
            novedad = this.crearNovedad(novedadesRecord, archivoNovedad);
            if(!EstadosNovedadesCargadas.ERROR.equals(novedad.getEstado())) {
                this.validarEstadoNovedad(novedad);
            }
            novedadesValidadas.add(novedad);
        }
        return novedadesValidadas;
    }

    private void loadEmpleadoContrato(String document){
        if(!currentDocument.equals(document)) {
            currentDocument = document;
            LOGGER.info(String.format("Empleado Service: %s, Document: %s",empleadoService, document) );
            empleado = empleadoService.getEmpleadoByDocumento(Long.parseLong(document));
            contrato = contratoService.getContratoByTrabajadorAndEstado(empleado, EstadoContrato.ACTIVO.toString());
        }
    }

    private void validarEstadoNovedad(NovedadValidada novedad) {
        this.validarEmpleado(novedad);
        if(EstadosNovedadesCargadas.VALIDADA.equals(novedad.getEstado())) {
            this.validarTopeConcepto(novedad);
        }
    }

    private void validarEstadoContrato(NovedadValidada novedad ) {
        if(contrato == null){
            this.actualizarEstadoNovedad(novedad,EstadosNovedadesCargadas.ERROR,String.format(MENSAJE_CONTRATO,novedad.getIdContrato()));
        }else{
            this.actualizarEstadoNovedad(novedad,EstadosNovedadesCargadas.VALIDADA,MENSAJE_CORRECTO);
            novedad.setIdContrato(contrato.getIdContrato());
        }
    }

    private void validarEmpleado(NovedadValidada novedad) {
        LOGGER.info("Empleado Service: "+empleadoService);
        if(empleado == null){
            this.actualizarEstadoNovedad(novedad,EstadosNovedadesCargadas.ERROR,String.format(MENSAJE_EMPLEADO,novedad.getIdContrato()));
        }else{
            this.validarEstadoContrato(novedad);
        }
    }

    private void validarTopeConcepto(NovedadValidada novedad) {
        Concepto concepto = novedad.getConcepto();
        if(noEsUnaCantidadValida(novedad, concepto) || noEsUnValorValido(novedad, concepto)){
            this.actualizarEstadoNovedad(novedad, EstadosNovedadesCargadas.ERROR, String.format(MENSAJE_TOPE_MAXIMO, concepto.getTopeMaximo()));
        }else if(novedad.getValor() <= 0 && novedad.getCantidad() <= 0){
            this.actualizarEstadoNovedad(novedad,EstadosNovedadesCargadas.ERROR, MENSAJE_VALOR_CERO);
        }else {
            this.actualizarEstadoNovedad(novedad,EstadosNovedadesCargadas.VALIDADA,MENSAJE_CORRECTO);
        }
    }

    private void actualizarEstadoNovedad(NovedadValidada novedad, EstadosNovedadesCargadas estadosNovedadesCargadas, String mensajeObservacion) {
        novedad.setEstado(estadosNovedadesCargadas);
        novedad.setObservacion(mensajeObservacion);
    }

    private boolean noEsUnValorValido(NovedadValidada novedad, Concepto concepto) {
        return novedad.getValor() > 0 && novedad.getValor() > concepto.getTopeMaximo();
    }

    private boolean noEsUnaCantidadValida(NovedadValidada novedad, Concepto concepto) {
        return novedad.getCantidad() > 0 && novedad.getCantidad() > concepto.getTopeMaximo();
    }

    private NovedadValidada crearNovedad(ArchivoNovedadesRecords novedadesRecord, ArchivoNovedad archivoNovedad) {
        NovedadValidada novedad = new NovedadValidada();
        try {
            Concepto concepto = this.getConcepto(novedadesRecord.getIdConcepto());
            novedad.setCantidad(Long.parseLong(novedadesRecord.getCantidad()));
            novedad.setCentroCostos(Long.parseLong(novedadesRecord.getCentroCostos()));
            novedad.setIdContrato(contrato.getIdContrato());
            novedad.setConcepto(concepto);
            novedad.setSubCentroCostos(Long.parseLong(novedadesRecord.getSubCentroCostos()));
            novedad.setValor(calculateValue(concepto,novedadesRecord));
            novedad.setIdEmpresa(archivoNovedad.getEmpresa().getIdEmpresa());
            novedad.setIdArchivoNovedad(archivoNovedad.getIdArchivoNovedad());
            novedad.setEstado(EstadosNovedadesCargadas.VALIDADA);
        }catch (NumberFormatException formatException){
            this.actualizarEstadoNovedad(novedad,EstadosNovedadesCargadas.ERROR,formatException.getMessage());
        }
        return novedad;
    }

    private long calculateValue(Concepto concepto, ArchivoNovedadesRecords novedadesRecord) {
        NominaContrato nominaContrato = new NominaContrato();
        nominaContrato.setContrato(contrato);
        int time = Integer.parseInt(novedadesRecord.getCantidad());
        long value = novedadProcessor.processsConceptValue(concepto, time, nominaContrato );
        if(value == 0){
            value = Long.parseLong(novedadesRecord.getValor());
        }
        return value;
    }


    private Concepto getConcepto(String idConcepto) {
        return this.conceptoService.getConceptoById(Long.parseLong(idConcepto));
    }
}
