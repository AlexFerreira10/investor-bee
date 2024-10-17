CREATE TABLE tb_account (
        account_id BIGINT AUTO_INCREMENT PRIMARY KEY,
        account_description VARCHAR(255) NOT NULL,
        account_active TINYINT,
        user_id BIGINT NOT NULL,

        CONSTRAINT FK_ID_USER FOREIGN KEY (user_id) REFERENCES tb_user(user_id)
);