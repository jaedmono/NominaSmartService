package com.smartservice.nomina.service.impl;


import com.smartservice.nomina.model.Banco;
import com.smartservice.nomina.repository.BancoRepository;
import com.smartservice.nomina.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancoServiceImpl implements BancoService {

    @Autowired
    private BancoRepository bancoRepository;

    @Override
    public List<Banco> getAllBancos() {
        return bancoRepository.findAll();
    }

    @Override
    public Banco saveBanco(Banco banco) {
        return bancoRepository.save(banco);
    }

    @Override
    public void deleteBanco(long bancoId) {
        bancoRepository.delete(bancoId);
    }
}
