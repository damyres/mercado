CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE produtos
(
    id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome       VARCHAR(255) NOT NULL,
    preco      DOUBLE PRECISION,
    descricao  VARCHAR(255),
    quantidade INTEGER
);


