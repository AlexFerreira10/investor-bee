package com.investor.bee.model.stock;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

@SQLDelete(sql = "UPDATE tb_stock SET active = false WHERE id=?")
@FilterDef(name = "deletedStockFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedStockFilter", condition = "active = :isDeleted")
@EqualsAndHashCode(of = "stockId")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_stock")
public class Stock {

    @Id
    @Column(name = "stock_id")
    private String stockId; // Será o ticker

    @Column(name = "stock_description")
    private String description;

    @Column(name = "stock_active")
    private Boolean active;
}
