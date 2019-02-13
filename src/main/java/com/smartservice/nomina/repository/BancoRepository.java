package com.smartservice.nomina.repository;


import com.smartservice.nomina.model.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoRepository extends JpaRepository<Banco, Long> {
}
