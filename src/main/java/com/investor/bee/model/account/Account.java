package com.investor.bee.model.account;

import com.investor.bee.model.billingAddress.BillingAddress;
import com.investor.bee.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

@SQLDelete(sql = "UPDATE tb_account SET active = false WHERE id=?")
@FilterDef(name = "deletedAccountFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedAccountFilter", condition = "active = :isDeleted")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "account_description")
    private String description;

    @Column(name = "account_active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "account")
    @PrimaryKeyJoinColumn
    private BillingAddress billingAddress;
}
