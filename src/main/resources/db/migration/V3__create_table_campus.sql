CREATE TABLE campus (
    id                  BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome                VARCHAR(100) NOT NULL UNIQUE,
    acronimo            VARCHAR(10) NOT NULL UNIQUE,
    cidade              VARCHAR(100) NOT NULL,
    endereco            VARCHAR(200) NOT NULL,
    criado_em           TIMESTAMP    NOT NULL DEFAULT now(),
    atualizado_em       TIMESTAMP    NOT NULL DEFAULT now()
);