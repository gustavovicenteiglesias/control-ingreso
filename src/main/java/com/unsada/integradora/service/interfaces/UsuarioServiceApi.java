package com.unsada.integradora.service.interfaces;

import com.unsada.integradora.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UsuarioServiceApi  {
    public List<Usuario> findAllUsuarios();
    public Optional<Usuario> findByIdUsuario(int id);
    public int deleteUsuario(int id);
    public int saveUsuario(Usuario u);
    Optional<Usuario> findByUsername(String username);
}
