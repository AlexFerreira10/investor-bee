package com.investor.bee.model.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Captor
    private ArgumentCaptor<Long> idArgumentCaptor;

    @Nested
    class save {
        @Test
        @DisplayName("Should create use with success")
        void shouldSaveUserWithSuccess() {
            // Arrange
            var input = new CreateUserDto("Alex Ferreira", LocalDate.parse("2004-02-16"),
                    "19922714701","correaferreiraalex92@gmail.com",
                    "senha123");

            User user = new User(input);
            doReturn(user).when(userRepository).save(userArgumentCaptor.capture());

            // Act
            var output  = userService.save(input);

            // Assert
            assertNotNull(output);

            var userCaptured = userArgumentCaptor.getValue();
            assertEquals(input.name(), userCaptured.getName());
            assertEquals(input.email(), userCaptured.getEmail());
            assertEquals(input.password(), userCaptured.getPassword());
            assertEquals(input.birthday(), userCaptured.getBirthday());
            assertEquals(input.cpf(), userCaptured.getCpf());
        }

        @Test
        @DisplayName("Should throw exception when error occurs")
        void shouldThrowExceptionWhenErrorOccurs() {
            // Arrange
            doThrow(new RuntimeException()).when(userRepository).save(any());
            var input = new CreateUserDto("Alex Ferreira", LocalDate.parse("2004-02-16"),
                    "19922714701","correaferreiraalex92@gmail.com",
                    "senha123");

            // Act and Assert
            assertThrows(RuntimeException.class, () -> userService.save(input));
        }
    }

    @Nested
    class findById {
        @Test
        @DisplayName("Should get user by id with success")
        void shouldFindUserByIdWithSuccess() {
            // Arrange
            var input = new CreateUserDto("Alex Ferreira", LocalDate.parse("2004-02-16"),
                    "19922714701", "correaferreiraalex92@gmail.com",
                    "senha123");

            User user = new User(input);
            user.setId(1L); // Atribuindo um ID explícito, por exemplo 1L

            // Simula que o usuário existe no repositório
            doReturn(true).when(userRepository).existsById(1L); // Simula que o ID existe
            doReturn(user).when(userRepository).getReferenceById(1L); // Simula que o usuário pode ser recuperado

            // Action
            var output = userService.findById(user.getId());

            // Assert
            assertNotNull(output);

            // Captura do ID e verificação
            verify(userRepository).existsById(idArgumentCaptor.capture()); // Captura o ID aqui
            assertEquals(user.getId(), idArgumentCaptor.getValue()); // Verifica se o ID capturado é igual ao ID do usuário
        }
    }
}