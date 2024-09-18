package com.investor.bee.model.user;

public record DataUserDto (
        Long id,
        String name,
        String cpf,
        boolean active
) {
}
