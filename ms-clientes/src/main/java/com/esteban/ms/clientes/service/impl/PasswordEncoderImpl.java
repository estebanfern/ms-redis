package com.esteban.ms.clientes.service.impl;

import com.esteban.ms.clientes.service.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PasswordEncoderImpl implements PasswordEncoder {

    @Override
    public String hashPassword(String plainPassword) {
        log.trace("hashing password");
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    @Override
    public boolean matches(String plainPassword, String hashedPassword) {
        log.trace("checking if {} matches {}", plainPassword, hashedPassword);
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

}
