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
@CrossOrigin("*")
public class LoginController {

    @Autowired
    LoginServiceInterface loginService;
    @Autowired
    UsuarioServiceApi usuarioServiceApi;

    @PostMapping("/registrar")
    public ResponseEntity register(@RequestBody Usuario usuario){
        try{
            usuarioServiceApi.saveUsuario(usuario);
            return new ResponseEntity("Usuario registrado", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error usuario no guardado", HttpStatus.BAD_REQUEST);

        }
    }
    @PostMapping("/ingreso")
    public ResponseEntity login(@RequestBody Usuario usuario){
        try{
            Usuario user = usuarioServiceApi.findByUsername(usuario.getUsername()).get();
            if(loginService.decode(user.getPassword()).equals(usuario.getPassword())){
                return new ResponseEntity("Acceso permitido", HttpStatus.OK);
            }else{
                return new ResponseEntity("Acceso denegado", HttpStatus.BAD_REQUEST);
            }

        }catch (NoSuchElementException e){
            return new ResponseEntity("Error en los datos ingresados", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value ="/test-con")
    public Map<String, String> test(){
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "success");
        return response;
    }
}
