package com.smartservice.nomina.service;


import com.smartservice.nomina.model.Descuento;
import com.smartservice.nomina.model.Nomina;
import com.smartservice.nomina.model.NominaContrato;

import java.util.Date;
import java.util.List;

public interface DescuentoService {

    List<Descuento> getAllDescuentos();

    List<Descuento> getDescuentosByCompany(long idCompany, String relacionadoCon);

    Descuento saveDescuento(Descuento descuento);

    void deleteDescuento(long descuentoId);

    void addDescuentos(NominaContrato nominaContrato, Date fechapago);
}
