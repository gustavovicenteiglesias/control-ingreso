package com.unsada.integradora.controller;

import com.unsada.integradora.model.entity.Usuario;
import com.unsada.integradora.service.impl.LoginServiceImpl;
import com.unsada.integradora.service.interfaces.LoginServiceInterface;
import com.unsada.integradora.service.interfaces.UsuarioServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    LoginServiceInterface loginService;
    @Autowired
    UsuarioServiceApi usuarioServiceApi;

    @PostMapping("/registrar")
    public ResponseEntity register(@RequestBody Usuario usuario){
        try{
            usuarioServiceApi.saveUsuario(usuario);
            return new ResponseEntity("Acceso permitido", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Acceso denegado", HttpStatus.BAD_REQUEST);

        }
    }
    @GetMapping("/ingreso")
    public Map<String, String> login(@RequestBody Usuario usuario){
        HashMap<String, String> response = new HashMap<>();
        try{
            Usuario user = usuarioServiceApi.findByUsername(usuario.getUsername()).get();
            if(loginService.decode(user.getPassword()).equals(usuario.getPassword())){
                response.put("Success", "Acceso permitido");
            }else{
                response.put("Failed", "Contraseña erronea");
            }

        }catch (NoSuchElementException e){
            response.put("Failed:", "Usuario no encontrado");
        }
        return response;
    }
}
