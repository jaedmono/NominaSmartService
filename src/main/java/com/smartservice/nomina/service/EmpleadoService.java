package com.smartservice.nomina.service;

import com.smartservice.nomina.model.Empleado;

import java.util.List;

public interface EmpleadoService {

    List<Empleado> getEmpleados();

    Empleado saveEmpleado(Empleado empleado);

    void deleteEmpleado(long empleadoId);

    Empleado getEmpleadoByDocumento(long documento);

}
