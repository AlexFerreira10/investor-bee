CREATE TABLE tb_user (
         user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
         user_name VARCHAR(255) NOT NULL,
         user_date_birth DATE,
         user_cpf VARCHAR(11) UNIQUE NOT NULL,
         user_email VARCHAR(255) UNIQUE NOT NULL,
         user_password VARCHAR(255) NOT NULL,
         user_active TINYINT
);