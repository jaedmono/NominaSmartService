package com.smartservice.nomina.service.impl;

import com.smartservice.nomina.model.ArchivoNovedad;
import com.smartservice.nomina.model.Empresa;
import com.smartservice.nomina.repository.ArchivoNovedadRepository;
import com.smartservice.nomina.service.ArchivoNovedadesService;
import com.smartservice.nomina.util.EstadoArchivosNovedad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArchivoNovedadServiceImpl implements ArchivoNovedadesService{

    @Autowired
    private ArchivoNovedadRepository archivoNovedadRepository;


    @Override
    public List<ArchivoNovedad> getAllArchivoNovedad() {
        return  this.archivoNovedadRepository.findAll();
    }

    @Override
    public ArchivoNovedad saveArchivoNovedad(ArchivoNovedad archivoNovedad) {
        return this.archivoNovedadRepository.save(archivoNovedad);
    }

    @Override
    public void deleteArchivoNovedad(long archivoNovedadId) {
        this.archivoNovedadRepository.delete(archivoNovedadId);
    }

    @Override
    public void saveArchivoNovedad(int empresaId, String mombreArchivo) {
        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(empresaId);
        ArchivoNovedad archivoNovedad = new ArchivoNovedad();
        archivoNovedad.setNombreArchivo(mombreArchivo);
        archivoNovedad.setEmpresa(empresa);
        archivoNovedad.setFechaCarga(new Date());
        archivoNovedad.setEstado(EstadoArchivosNovedad.CARGADO.toString());
        this.saveArchivoNovedad(archivoNovedad);
    }

    @Override
    public ArchivoNovedad getArchivoNovedadById(long id) {
        return this.archivoNovedadRepository.findOne(id);
    }
}
