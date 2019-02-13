package com.smartservice.nomina.service;

import com.smartservice.nomina.model.Empresa;

import java.util.List;

public interface EmpresaService {

    List<Empresa> getAllEmpresas();

    Empresa saveEmpresa(Empresa empresa);

    void deleteEmpresa(Long ifEmpresa);

    List<Empresa>  getAllEmpresasByIdUsuario(long idUsuario);
}
