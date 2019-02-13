package com.smartservice.nomina.service.impl;


import com.smartservice.nomina.model.Usuario;
import com.smartservice.nomina.repository.UsuarioRepository;
import com.smartservice.nomina.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findUsuarioByLoginAndPassword(String login, String password) {
        return usuarioRepository.findByLoginAndPassword(login,password);
    }

    @Override
    public void deleteUsuario(long id) {
        usuarioRepository.delete(id);
    }


}
