CREATE TABLE tb_stock (
          stock_id BIGINT AUTO_INCREMENT PRIMARY KEY,
          stock_description VARCHAR(255) NOT NULL,
          stock_ticker VARCHAR(30) NOT NULL,
          stock_active TINYINT
);
