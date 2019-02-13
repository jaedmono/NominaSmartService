package com.smartservice.nomina.service.impl;

import com.smartservice.nomina.model.Empresa;
import com.smartservice.nomina.repository.EmpresaRepository;
import com.smartservice.nomina.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository repository;


    @Override
    public List<Empresa> getAllEmpresas() {
        return this.repository.findAll();
    }

    @Override
    public Empresa saveEmpresa(Empresa empresa) {
        return this.repository.save(empresa);
    }

    @Override
    public void deleteEmpresa(Long idEmpresa) {
        this.repository.delete(idEmpresa);
    }

    @Override
    public List<Empresa>  getAllEmpresasByIdUsuario(long idUsuario) {
        return this.repository.findByIdUsuario(idUsuario);
    }
}
