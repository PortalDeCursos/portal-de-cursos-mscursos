-- Áreas do conhecimento
INSERT INTO areas_conhecimento (nome, descricao)
VALUES
('Exatas e Tecnologia', 'Computação, matemática, física e química.'),
('Engenharias e Produção', 'Engenharias, processos e produção industrial.'),
('Gestão e Negócios', 'Administração, gestão e negócios.'),
('Ciências Biológicas e da Saúde', 'Biologia, saúde e educação física.'),
('Ciências Agrárias e Ambientais', 'Agroindústria e gestão ambiental.'),
('Humanas e Linguagens', 'Ciências humanas, letras e linguagens.');

-- Campus
INSERT INTO campus (nome, acronimo, cidade, endereco)
VALUES
('Campus Nova Cruz', 'IFNC', 'Nova Cruz', 'Endereço a ser atualizado'),
('Campus Natal Central', 'CNAT', 'Natal', 'Endereço a ser atualizado'),
('Campus Parnamirim', 'IFPAR', 'Parnamirim', 'Endereço a ser atualizado');

-- Cursos do IFNC
INSERT INTO cursos (campus_id, area_conhecimento_id, nome, acronimo, grau, duracao, turno, modalidade, carga_horaria, qtd_disciplinas_obrigatorias, qtd_disciplinas_optativas, descricao)
VALUES
((SELECT id FROM campus WHERE acronimo = 'IFNC'), (SELECT id FROM areas_conhecimento WHERE nome = 'Exatas e Tecnologia'),
'Análise e Desenvolvimento de Sistemas', 'TADS', 'TECNOLOGO', '7 semestres', 'NOTURNO', 'PRESENCIAL', 2400, 36, 6,
'Formação para desenvolver e manter sistemas de software.'),

((SELECT id FROM campus WHERE acronimo = 'IFNC'), (SELECT id FROM areas_conhecimento WHERE nome = 'Engenharias e Produção'),
'Tecnologia em Processos Químicos', 'TPQ', 'TECNOLOGO', '6 semestres', 'VESPERTINO', 'PRESENCIAL', 2400, 12, 0,
'Formação para atuar em processos e controle da indústria química.'),

((SELECT id FROM campus WHERE acronimo = 'IFNC'), (SELECT id FROM areas_conhecimento WHERE nome = 'Gestão e Negócios'),
'Técnico Subsequente em Administração', 'TADM', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1000, 8, 0,
'Formação técnica em gestão de processos administrativos.');

-- Disciplinas do TADS (IFNC)
INSERT INTO disciplinas (curso_id, nome, codigo, carga_horaria, semestre, descricao, obrigatoria) VALUES
-- 1º semestre
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Empreendedorismo', 'IFNC-TADS-101', 60, 'PRIMEIRO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Língua Inglesa I', 'IFNC-TADS-102', 60, 'PRIMEIRO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Matemática Discreta', 'IFNC-TADS-103', 60, 'PRIMEIRO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Programação de Computadores', 'IFNC-TADS-104', 60, 'PRIMEIRO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Seminário de Integração Acadêmica', 'IFNC-TADS-105', 60, 'PRIMEIRO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Web Design', 'IFNC-TADS-106', 60, 'PRIMEIRO', 'Disciplina do curso de TADS', true),
-- 2º semestre
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Algoritmos', 'IFNC-TADS-201', 60, 'SEGUNDO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Epistemologia da Ciência', 'IFNC-TADS-202', 60, 'SEGUNDO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Inglês para Informática', 'IFNC-TADS-203', 60, 'SEGUNDO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Língua Inglesa II', 'IFNC-TADS-204', 60, 'SEGUNDO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Programação Orientada a Objetos', 'IFNC-TADS-205', 60, 'SEGUNDO', 'Disciplina do curso de TADS', true),
-- 3º semestre
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Arquitetura de Computadores', 'IFNC-TADS-301', 60, 'TERCEIRO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Computador e Sociedade', 'IFNC-TADS-302', 60, 'TERCEIRO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Interface Humano-Computador', 'IFNC-TADS-303', 60, 'TERCEIRO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Metodologia do Trabalho Científico', 'IFNC-TADS-304', 60, 'TERCEIRO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Redes de Computadores', 'IFNC-TADS-305', 60, 'TERCEIRO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Seminário de Iniciação à Pesquisa e à Extensão', 'IFNC-TADS-306', 60, 'TERCEIRO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Sistemas Digitais', 'IFNC-TADS-307', 60, 'TERCEIRO', 'Disciplina do curso de TADS', true),
-- 4º semestre
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Análise e Projeto Orientado a Objetos', 'IFNC-TADS-401', 60, 'QUARTO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Banco de Dados', 'IFNC-TADS-402', 60, 'QUARTO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Desenvolvimento de Sistemas Web', 'IFNC-TADS-403', 60, 'QUARTO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Estrutura de Dados Lineares', 'IFNC-TADS-404', 60, 'QUARTO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Projeto Integrador I', 'IFNC-TADS-405', 60, 'QUARTO', 'Disciplina do curso de TADS', true),
-- 5º semestre
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Arquitetura de Software', 'IFNC-TADS-501', 60, 'QUINTO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Desenvolvimento de Sistemas Distribuídos', 'IFNC-TADS-502', 60, 'QUINTO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Processo de Software', 'IFNC-TADS-503', 60, 'QUINTO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Programação e Administração de Banco de Dados', 'IFNC-TADS-504', 60, 'QUINTO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Projeto Integrador II', 'IFNC-TADS-505', 60, 'QUINTO', 'Disciplina do curso de TADS', true),
-- 6º semestre
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Desenvolvimento de Sistemas Corporativos', 'IFNC-TADS-601', 60, 'SEXTO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Estrutura de Dados Não Lineares', 'IFNC-TADS-602', 60, 'SEXTO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Gerência de Projetos', 'IFNC-TADS-603', 60, 'SEXTO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Sistemas Operacionais', 'IFNC-TADS-604', 60, 'SEXTO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Teste de Software', 'IFNC-TADS-605', 60, 'SEXTO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Projeto Integrador III', 'IFNC-TADS-606', 60, 'SEXTO', 'Disciplina do curso de TADS', true),
-- 7º semestre
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Gestão de Tecnologia da Informação', 'IFNC-TADS-701', 60, 'SETIMO', 'Disciplina do curso de TADS', true),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Seminário de Orientação para Trabalho de Conclusão de Curso', 'IFNC-TADS-702', 60, 'SETIMO', 'Disciplina do curso de TADS', true),
-- optativas
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Engenharia de Requisitos', 'IFNC-TADS-O01', 60, 'SETIMO', 'Disciplina optativa do curso de TADS', false),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Informática', 'IFNC-TADS-O02', 60, 'SETIMO', 'Disciplina optativa do curso de TADS', false),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Organização de Computadores', 'IFNC-TADS-O03', 60, 'SETIMO', 'Disciplina optativa do curso de TADS', false),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Paradigmas de Linguagens de Programação', 'IFNC-TADS-O04', 60, 'SETIMO', 'Disciplina optativa do curso de TADS', false),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Qualidade de Software', 'IFNC-TADS-O05', 60, 'SETIMO', 'Disciplina optativa do curso de TADS', false),
((SELECT id FROM cursos WHERE acronimo = 'TADS'), 'Qualidade de Vida e Trabalho', 'IFNC-TADS-O06', 60, 'SETIMO', 'Disciplina optativa do curso de TADS', false);