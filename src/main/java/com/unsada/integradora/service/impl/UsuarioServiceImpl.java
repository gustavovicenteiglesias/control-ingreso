package com.unsada.integradora.service.impl;

import com.unsada.integradora.dao.UsuarioDao;
import com.unsada.integradora.model.entity.Usuario;
import com.unsada.integradora.service.interfaces.LoginServiceInterface;
import com.unsada.integradora.service.interfaces.UsuarioServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UsuarioServiceImpl implements UsuarioServiceApi {
    @Autowired
    LoginServiceInterface loginService;
    @Autowired
    UsuarioDao usuarioDao;

    @Override
    public List<Usuario> findAllUsuarios() {
        return (List<Usuario>) usuarioDao.findAll();
    }

    @Override
    public Optional<Usuario> findByIdUsuario(int id) {
        return usuarioDao.findById(id);
    }

    @Override
    public int deleteUsuario(int id) {
        usuarioDao.deleteById(id);
        if(this.findByIdUsuario(id).isPresent()){
            return 1;
        }
        return 0;

    }

    @Override
    public int saveUsuario(Usuario u) {
        String encodedPass = loginService.encode(u.getPassword());
        u.setPassword(encodedPass);
        Usuario usuario = usuarioDao.save(u);
        if(usuario.equals(null)){
            return 1;
        }
        return 0;
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return usuarioDao.findByUsername(username);
    }
}
