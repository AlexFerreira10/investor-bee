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
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

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
        // Problema no Teste
        @Test
        @DisplayName("Should get user by id with success")
        void shouldFindUserByIdWithSuccess() {
            // Arrange
            var input = new CreateUserDto("Alex Ferreira", LocalDate.parse("2004-02-16"),
                    "19922714701", "correaferreiraalex92@gmail.com",
                    "senha123");

            User user = new User(input);
            doReturn(Optional.of(user)).when(userRepository).findById(idArgumentCaptor.capture());

            // Action
            var output = userService.findById(user.getId());

            // Assert
            assertNotNull(output);
            assertEquals(user.getId(), idArgumentCaptor.getValue());

        }
    }
}