CREATE TABLE tb_account_stock (
          account_id BIGINT NOT NULL,
          stock_id BIGINT NOT NULL,
          account_stock_quantify INT,

          PRIMARY KEY (account_id, stock_id),

          CONSTRAINT FK_Account_ID FOREIGN KEY (account_id) REFERENCES tb_account(account_id),
          CONSTRAINT FK_Stock_ID FOREIGN KEY (stock_id) REFERENCES tb_stock(stock_id)
);
