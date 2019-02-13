package com.smartservice.nomina.repository;

import com.smartservice.nomina.model.CentroCostos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CentroCostosRepository extends JpaRepository<CentroCostos, Long> {

    List<CentroCostos> findByTipo(String tipo);
}
