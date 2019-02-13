package com.smartservice.nomina.repository;

import com.smartservice.nomina.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    List<Empresa> findByIdUsuario(long idUsuario);
}
