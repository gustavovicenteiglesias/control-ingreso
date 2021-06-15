package com.unsada.integradora.service.impl;

import com.unsada.integradora.service.interfaces.LoginServiceInterface;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
@Service
public class LoginServiceImpl implements LoginServiceInterface {
    @Override
    public String encode(String x) {
        byte[] password = x.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(password);

    }

    @Override
    public String decode(String y) {
        byte[] decoded = Base64.getDecoder().decode(y);
        return new String(decoded, StandardCharsets.UTF_8);
    }

    @Override
    public int length() {
        return 0;
    }
}
