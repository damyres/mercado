CREATE TABLE produtos(
     id SERIAL PRIMARY KEY,
     nome VARCHAR(255) NOT NULL,
     preco DOUBLE PRECISION,
     descricao VARCHAR(255),
     quantidade INTEGER
);
