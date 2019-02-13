package com.smartservice.nomina.repository;

import com.smartservice.nomina.model.EntidadParafiscal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntidadParafiscalRepository extends JpaRepository<EntidadParafiscal, Long> {

    List<EntidadParafiscal> findByTipoEntidad(String tipoEnticdad);
}
