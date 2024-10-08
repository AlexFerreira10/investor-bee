package com.investor.bee.model.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateUserDto(

        @NotBlank(message = "The name camp can't be empty!")
        String name,
        @NotNull(message = "The name birth can't be empty!")
        LocalDate birthday,
        @NotBlank(message = "The name cpf can't be empty!")
        String cpf,
        @NotBlank(message = "The email camp can't be empty!")
        String email,
        @NotBlank(message = "The password camp can't be empty!")
        String password
) {
}
