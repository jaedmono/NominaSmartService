package com.smartservice.nomina.service.impl;

import com.smartservice.nomina.model.NovedadValidada;
import com.smartservice.nomina.processor.NovedadProcessor;
import com.smartservice.nomina.repository.NovedadValidadaRepository;
import com.smartservice.nomina.service.NovedadValidadaService;
import com.smartservice.nomina.util.EstadosNovedadesCargadas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NovedadValidadaServiceImpl implements NovedadValidadaService{

    private static final Logger LOGGER = LoggerFactory.getLogger(NovedadValidadaServiceImpl.class);

    private static final String IDARCHIVO = "IDARCHIVO";
    private NovedadValidadaRepository novedadValidadaRepository;
    private NovedadProcessor novedadProcessor;

    @Autowired
    public NovedadValidadaServiceImpl(final NovedadValidadaRepository novedadValidadaRepository, final NovedadProcessor novedadProcessor){

        this.novedadValidadaRepository = novedadValidadaRepository;
        this.novedadProcessor = novedadProcessor;

    }

    @Override
    public List<NovedadValidada> getAllNovedadesValidadas() {
        return novedadValidadaRepository.findAll();
    }

    @Override
    public List<NovedadValidada> getAllNovedadesValidadasById(long idArchivoNovedad, String type) {
        List<NovedadValidada> novedadValidadas = new ArrayList<>();
        if(type.equals(IDARCHIVO)){
            novedadValidadas = this.novedadValidadaRepository.findByIdArchivoNovedad(idArchivoNovedad);
        }
        return novedadValidadas;
    }


    @Override
    public NovedadValidada saveNovedadValidada(NovedadValidada novedadValidada) {
        return this.novedadValidadaRepository.save(novedadValidada);
    }

    @Override
    public List<NovedadValidada> saveNovedadValidada(List<NovedadValidada> novedadesValidadas) {
        return this.novedadValidadaRepository.save(novedadesValidadas);
    }

    @Override
    public void deleteNovedadValidada(long idNovedadValidada) {
        this.novedadValidadaRepository.delete(idNovedadValidada);
    }

    @Override
    public NovedadValidada saveNovedadValidada(long idArchivoNovedades, String type, long idEmpresa) {

        List<NovedadValidada> novedadValidadas =
                this.novedadValidadaRepository.findByIdArchivoNovedadAndEstado(idArchivoNovedades, EstadosNovedadesCargadas.VALIDADA.name());
        LOGGER.info("Novedades a procesar: "+novedadValidadas.size());
        this.novedadProcessor.loadNovedades(novedadValidadas, idEmpresa);
        return null;
    }
}
