package com.smartservice.nomina.repository;

import com.smartservice.nomina.model.Descuento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface DescuentoRepository extends JpaRepository<Descuento, Long> {

    List<Descuento> findByIdContratoAndEstadoAndFechaFinalizacionLessThan(long idContrato, String estado, Date fechaFinalizacion);

    @Query("select d from Descuento d where contrato.idEmpresa = ?1 and concepto.relacionadoCon = ?2")
    List<Descuento> findByIdEmpresaAndRelacionadoCon(long idCompany, String relacionadoCon);
}
