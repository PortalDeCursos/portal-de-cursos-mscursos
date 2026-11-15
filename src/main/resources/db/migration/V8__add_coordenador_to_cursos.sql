ALTER TABLE cursos
    ADD COLUMN coordenador_id BIGINT;

ALTER TABLE cursos
    ADD CONSTRAINT uk_curso_coordenador UNIQUE (coordenador_id);