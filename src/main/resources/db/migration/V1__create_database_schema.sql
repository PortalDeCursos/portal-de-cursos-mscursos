CREATE TABLE campus (
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome          VARCHAR(100) NOT NULL UNIQUE,
    acronimo      VARCHAR(10)  NOT NULL UNIQUE,
    cidade        VARCHAR(100) NOT NULL,
    endereco      VARCHAR(200) NOT NULL,
    criado_em     TIMESTAMP    NOT NULL DEFAULT now(),
    atualizado_em TIMESTAMP    NOT NULL DEFAULT now()
);

CREATE TABLE areas_conhecimento (
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome          VARCHAR(100) NOT NULL UNIQUE,
    descricao     VARCHAR(500) NOT NULL,
    criado_em     TIMESTAMP    NOT NULL DEFAULT now(),
    atualizado_em TIMESTAMP    NOT NULL DEFAULT now()
);

CREATE TABLE cursos (
    id                           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    campus_id                    BIGINT       NOT NULL,
    area_conhecimento_id         BIGINT       NOT NULL,
    coordenador_id               BIGINT,
    nome                         VARCHAR(100) NOT NULL,
    acronimo                     VARCHAR(10)  NOT NULL,
    grau                         VARCHAR(50)  NOT NULL,
    duracao                      VARCHAR(50)  NOT NULL,
    turno                        VARCHAR(50)  NOT NULL,
    modalidade                   VARCHAR(50)  NOT NULL,
    carga_horaria                INTEGER      NOT NULL,
    qtd_disciplinas_obrigatorias INTEGER      NOT NULL,
    qtd_disciplinas_optativas    INTEGER      NOT NULL,
    descricao                    VARCHAR(500) NOT NULL,
    criado_em                    TIMESTAMP    NOT NULL DEFAULT now(),
    atualizado_em                TIMESTAMP    NOT NULL DEFAULT now(),

    CONSTRAINT fk_curso_campus             FOREIGN KEY (campus_id) REFERENCES campus (id) ON DELETE RESTRICT,
    CONSTRAINT fk_curso_area_conhecimento  FOREIGN KEY (area_conhecimento_id) REFERENCES areas_conhecimento (id) ON DELETE RESTRICT,
    CONSTRAINT uk_curso_campus_acronimo    UNIQUE (campus_id, acronimo),
    CONSTRAINT uk_curso_campus_nome        UNIQUE (campus_id, nome),
    CONSTRAINT uk_curso_coordenador        UNIQUE (coordenador_id),
    CONSTRAINT ck_curso_carga_horaria      CHECK (carga_horaria > 0),
    CONSTRAINT ck_curso_qtd_obrigatorias   CHECK (qtd_disciplinas_obrigatorias >= 0),
    CONSTRAINT ck_curso_qtd_optativas      CHECK (qtd_disciplinas_optativas >= 0)
);

CREATE TABLE disciplinas (
     id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     curso_id      BIGINT       NOT NULL,
     nome          VARCHAR(100) NOT NULL,
     codigo        VARCHAR(20)  NOT NULL UNIQUE,
     carga_horaria INTEGER      NOT NULL,
     semestre      VARCHAR(20)  NOT NULL,
     descricao     VARCHAR(500) NOT NULL,
     obrigatoria   BOOLEAN      NOT NULL,
     criado_em     TIMESTAMP    NOT NULL DEFAULT now(),
     atualizado_em TIMESTAMP    NOT NULL DEFAULT now(),

     CONSTRAINT fk_disciplina_curso         FOREIGN KEY (curso_id) REFERENCES cursos (id) ON DELETE RESTRICT,
     CONSTRAINT uk_disciplina_curso_codigo  UNIQUE (curso_id, codigo),
     CONSTRAINT ck_disciplina_carga_horaria CHECK (carga_horaria > 0)
);
