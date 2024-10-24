package com.investor.bee.model.accountStock;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AccountStockId {

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "stock_id")
    private String stockId;
}
