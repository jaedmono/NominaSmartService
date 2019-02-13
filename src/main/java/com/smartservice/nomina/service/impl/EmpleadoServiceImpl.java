package com.smartservice.nomina.service.impl;

import com.smartservice.nomina.model.Empleado;
import com.smartservice.nomina.repository.EmpleadoRepository;
import com.smartservice.nomina.service.EmpleadoService;
import jersey.repackaged.com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmpleadoServiceImpl.class);


    @Autowired
    EmpleadoRepository repository;

    @Override
    public List<Empleado> getEmpleados() {
        List<Empleado> result = Lists.newArrayList(repository.findAll());
        return result;
    }

    @Override
    public Empleado saveEmpleado(Empleado empleado) {
        return repository.saveAndFlush(empleado);
    }

    @Override
    public void deleteEmpleado(long empleadoId) {
        repository.delete(empleadoId);
    }

    @Override
    public Empleado getEmpleadoByDocumento(long numeroDocumento){
        return repository.findByNumeroDocumento(numeroDocumento);
    }


}
