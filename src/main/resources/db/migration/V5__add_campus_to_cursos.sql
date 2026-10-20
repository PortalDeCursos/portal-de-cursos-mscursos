ALTER TABLE cursos
    ADD COLUMN campus_id BIGINT NOT NULL REFERENCES campus (id) ON DELETE RESTRICT;

ALTER TABLE cursos DROP CONSTRAINT uk_curso_acronimo;

ALTER TABLE cursos
    ADD CONSTRAINT uk_curso_campus_acronimo UNIQUE (campus_id, acronimo);

ALTER TABLE cursos
    ADD CONSTRAINT uk_curso_campus_nome UNIQUE (campus_id, nome);