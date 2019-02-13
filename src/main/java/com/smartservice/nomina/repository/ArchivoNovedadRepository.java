package com.smartservice.nomina.repository;


import com.smartservice.nomina.model.ArchivoNovedad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivoNovedadRepository extends JpaRepository<ArchivoNovedad, Long> {

    Page<ArchivoNovedad> findByEstadoOrderByFechaCarga(String estado, Pageable pageable);
}
