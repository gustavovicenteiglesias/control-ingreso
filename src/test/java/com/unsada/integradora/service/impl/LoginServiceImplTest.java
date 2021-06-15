package com.unsada.integradora.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginServiceImplTest {
    private String original = "hola mundo";
    private LoginServiceImpl loginService;

    @BeforeAll
    void setUp(){
         loginService = new LoginServiceImpl();
    }

    @Test
    void probarEncoding(){
        System.out.println("original: " + original);
        String encodedPass = loginService.encode(original);
        System.out.println("resultado codificado: " + encodedPass);
        String reDecoded = loginService.decode(encodedPass);
        System.out.println("resultado decodificado: " + reDecoded);
        assertEquals(reDecoded, original);

    }

}