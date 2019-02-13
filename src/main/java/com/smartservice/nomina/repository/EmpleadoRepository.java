package com.smartservice.nomina.repository;

import com.smartservice.nomina.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {

    Empleado findByNumeroDocumento(long numeroDocumento);
}
