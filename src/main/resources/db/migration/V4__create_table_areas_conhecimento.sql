CREATE TABLE areas_conhecimento (
    id                  BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome                VARCHAR(100) NOT NULL UNIQUE,
    descricao           VARCHAR(500) NOT NULL,
    criado_em           TIMESTAMP    NOT NULL DEFAULT now(),
    atualizado_em       TIMESTAMP    NOT NULL DEFAULT now()
);