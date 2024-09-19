package com.investor.bee.model.user;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import java.time.Instant;
import java.time.LocalDate;

@SQLDelete(sql = "UPDATE tb_user SET active = false WHERE id=?")
@FilterDef(name = "deletedUserFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedUserFilter", condition = "active = :isDeleted")
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

    public User(CreateUserDto user) {
        this.name = user.name();
        this.birthday = user.birthday();
        this.cpf = user.cpf();
        this.email = user.email();
        this.password = user.password();
        this.active = true;
    }



    public void updateData(UpdateUserDto newData) {
        if(!this.name.equals(newData.name())) {
            this.name = newData.name();
        }
        if(!this.cpf.equals(newData.cpf())) {
            this.cpf = newData.cpf();
        }
        if(!this.birthday.equals(newData.birthday())) {
            this.birthday = newData.birthday();
        }
        if(!this.email.equals(newData.email())) {
            this.email = newData.email();
        }
        if (!this.password.equals(newData.password())) {
            this.password = newData.password();
        }
    }
}