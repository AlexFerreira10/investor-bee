package com.investor.bee.model.accountStock;

import com.investor.bee.model.account.Account;
import com.investor.bee.model.stock.Stock;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

@SQLDelete(sql = "UPDATE tb_account_stock SET active = false WHERE id=?")
@FilterDef(name = "deletedAccountStockFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedAccountStockFilter", condition = "active = :isDeleted")
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_account_stock")
public class AccountStock {

    @EmbeddedId
    private AccountStockId id;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(name = "account_stock_quantity")
    private Integer quantity;
}
