package com.smartservice.nomina.repository;

import com.smartservice.nomina.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByLoginAndPassword(String login, String password);


}
