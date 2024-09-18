package com.investor.bee.model.user;

import java.time.LocalDate;

public record UpdateUserDto(
        Long id,
        String name,
        LocalDate birthday,
        String cpf,
        String email,
        String password
) {

    public UpdateUserDto(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getBirthday(),
                user.getCpf(),
                user.getEmail(),
                user.getPassword()
        );
    }
}