package com.smartservice.nomina.service;


import com.smartservice.nomina.model.Banco;

import java.util.List;

public interface BancoService {

    List<Banco> getAllBancos();

    Banco saveBanco(Banco banco);

    void deleteBanco(long bancoId);
}
