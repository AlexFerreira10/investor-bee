CREATE TABLE tb_account_stock (
          account_id BIGINT,
          stock_id VARCHAR(30),
          account_stock_quantity INT NOT NULL,

          PRIMARY KEY (account_id, stock_id),

          CONSTRAINT FK_Account_ID FOREIGN KEY (account_id) REFERENCES tb_account(account_id),
          CONSTRAINT FK_Stock_ID FOREIGN KEY (stock_id) REFERENCES tb_stock(stock_id)
);