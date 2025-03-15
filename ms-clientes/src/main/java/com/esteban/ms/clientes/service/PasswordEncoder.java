package com.esteban.ms.clientes.service;

public interface PasswordEncoder {
    String hashPassword(String plainPassword);
    boolean matches(String plainPassword, String hashedPassword);
}
