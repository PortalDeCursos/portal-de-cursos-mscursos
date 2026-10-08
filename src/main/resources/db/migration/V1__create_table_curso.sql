CREATE TABLE cursos (
   id                           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
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

   CONSTRAINT uk_curso_acronimo               UNIQUE (acronimo),
   CONSTRAINT ck_curso_carga_horaria          CHECK (carga_horaria > 0),
   CONSTRAINT ck_curso_qtd_obrigatorias       CHECK (qtd_disciplinas_obrigatorias >= 0),
   CONSTRAINT ck_curso_qtd_optativas          CHECK (qtd_disciplinas_optativas >= 0)
);