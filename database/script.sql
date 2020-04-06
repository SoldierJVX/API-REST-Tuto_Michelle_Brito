CREATE DATABASE apirest-springboot;

CREATE TABLE tb_product (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(150) DEFAULT NULL,
    value DECIMAL(12,2),
    PRIMARY KEY(id)
);