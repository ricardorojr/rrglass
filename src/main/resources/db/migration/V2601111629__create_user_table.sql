CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY, -- Chave primária autoincremental
    nome VARCHAR(100) NOT NULL, -- Nome completo do usuário (obrigatório)
    email VARCHAR(255) UNIQUE NOT NULL, -- Email (único e obrigatório)
    senha_hash VARCHAR(255) NOT NULL, -- Armazena o hash da senha (nunca a senha em texto plano!)
    cpf VARCHAR(11) UNIQUE, -- CPF (opcional, mas único se preenchido)
    data_nascimento DATE, -- Data de nascimento (opcional)
    telefone VARCHAR(20), -- Telefone (opcional)
    ativo BOOLEAN DEFAULT TRUE, -- Status do usuário (ativo/inativo), padrão TRUE
    data_criacao TIMESTAMPTZ,
    data_atualizacao TIMESTAMPTZ
);