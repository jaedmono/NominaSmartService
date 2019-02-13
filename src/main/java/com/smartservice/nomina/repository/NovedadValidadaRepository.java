package com.smartservice.nomina.repository;


import com.smartservice.nomina.model.NovedadValidada;
import com.smartservice.nomina.util.EstadosNovedadesCargadas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NovedadValidadaRepository extends JpaRepository<NovedadValidada, Long> {
    List<NovedadValidada> findByIdArchivoNovedad(long idArchivoNovedad);
    List<NovedadValidada> findByIdArchivoNovedadAndEstado(long idArchivoNovedad, String estado);
}
