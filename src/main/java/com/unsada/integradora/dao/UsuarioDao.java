package com.unsada.integradora.dao;

import com.unsada.integradora.model.entity.Usuario;
import com.unsada.integradora.service.interfaces.UsuarioServiceApi;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioDao extends CrudRepository<Usuario, Integer> {
    public Optional<Usuario> findByUsernameAndPassword(String username, String password);
    Optional<Usuario> findByUsername(String username);
}
