CREATE TABLE tb_billing_address (
        account_id BIGINT PRIMARY KEY,
        billing_address_street VARCHAR(255) NOT NULL,
        billing_address_number INT NOT NULL,
        billing_address_active TINYINT,

        CONSTRAINT FK_ID_Account FOREIGN KEY (account_id) REFERENCES tb_account(account_id)
);