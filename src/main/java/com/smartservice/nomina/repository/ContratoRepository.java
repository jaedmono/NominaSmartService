package com.smartservice.nomina.repository;

import com.smartservice.nomina.model.Contrato;
import com.smartservice.nomina.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    List<Contrato> findByIdEmpresaAndEstadoAndPeriodoPago(long idEmpresa,String estado, String periodoPago);

    List<Contrato> findByIdEmpresaAndEstado(long idEmpresa,String estado);

    Contrato findByEmpleadoAndEstado(Empleado empleado, String estado);

    long countByPeriodoPagoAndEstado(String periodoPago, String estado);


}
