ALTER TABLE cursos
    ADD COLUMN area_conhecimento_id BIGINT NOT NULL REFERENCES areas_conhecimento (id) ON DELETE RESTRICT;