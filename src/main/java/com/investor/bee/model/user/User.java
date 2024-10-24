package com.investor.bee.model.user;

import com.investor.bee.model.account.Account;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@SQLDelete(sql = "UPDATE tb_user SET active = false WHERE id=?")
@FilterDef(name = "deletedUserFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedUserFilter", condition = "active = :isDeleted")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_date_birth")
    private LocalDate birthday;

    @Column(name = "user_cpf")
    private String cpf;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_active")
    private boolean active;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

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