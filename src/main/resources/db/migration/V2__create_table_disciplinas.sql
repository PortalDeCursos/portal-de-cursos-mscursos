CREATE TABLE disciplinas (
     id               BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     curso_id         BIGINT       NOT NULL,
     nome             VARCHAR(100) NOT NULL,
     codigo           VARCHAR(20)  NOT NULL UNIQUE,
     carga_horaria    INTEGER      NOT NULL,
     semestre         VARCHAR(20)  NOT NULL,
     descricao        VARCHAR(500) NOT NULL,
     obrigatoria      BOOLEAN      NOT NULL,
     criado_em        TIMESTAMP    NOT NULL DEFAULT now(),
     atualizado_em    TIMESTAMP    NOT NULL DEFAULT now(),

     CONSTRAINT fk_disciplina_curso          FOREIGN KEY (curso_id) REFERENCES cursos (id) ON DELETE RESTRICT,
     CONSTRAINT uk_disciplina_curso_codigo   UNIQUE (curso_id, codigo),
     CONSTRAINT ck_disciplina_carga_horaria  CHECK (carga_horaria > 0)
);