CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha_hash VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE,
    data_nascimento DATE,
    telefone VARCHAR(20),
    ativo BOOLEAN DEFAULT TRUE,
    data_criacao TIMESTAMPTZ,
    data_atualizacao TIMESTAMPTZ
);