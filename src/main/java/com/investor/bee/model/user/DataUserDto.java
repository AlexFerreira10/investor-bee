package com.investor.bee.model.user;

public record DataUserDto (
        Long id,
        String name,
        String email,
        boolean active
) {
}
