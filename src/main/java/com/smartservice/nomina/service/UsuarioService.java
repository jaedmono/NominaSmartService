package com.smartservice.nomina.service;

import com.smartservice.nomina.model.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> getAllUsuarios();

    Usuario saveUsuario(Usuario usuario);

    Usuario findUsuarioByLoginAndPassword(String login, String password);

    void deleteUsuario(long id);
}
