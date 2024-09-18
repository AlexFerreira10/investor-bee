package com.investor.bee.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;
import java.time.LocalDate;

@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDate birthday;

    @Column
    private String cpf;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private boolean active;

    @CreationTimestamp
    private Instant creationTimestamp;

    @UpdateTimestamp
    private Instant updateTimestamp;

    public User(Long id, String name, LocalDate birthday, String cpf, String email, String password) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
    }
}