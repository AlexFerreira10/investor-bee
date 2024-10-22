package com.investor.bee.model.billingAddress;

import com.investor.bee.model.account.Account;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

@SQLDelete(sql = "UPDATE tb_billing_address SET active = false WHERE id=?")
@FilterDef(name = "deletedBillingAddressFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedBillingAddressFilter", condition = "active = :isDeleted")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_billing_address")
public class BillingAddress {

    @Id
    @Column(name = "account_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "billing_address_street")
    private String street;

    @Column(name = "billing_address_number")
    private Integer number;

    @Column(name = "billing_address_active")
    private Boolean active;
}
