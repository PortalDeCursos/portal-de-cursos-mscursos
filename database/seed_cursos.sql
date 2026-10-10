-- Seed de desenvolvimento: tabela cursos
-- Valores de carga horária, duração, turno e quantidade de disciplinas são ILUSTRATIVOS.

INSERT INTO cursos (nome, acronimo, grau, duracao, turno, modalidade, carga_horaria,
                    qtd_disciplinas_obrigatorias, qtd_disciplinas_optativas, descricao)
VALUES
-- SUBSEQUENTE
('Técnico Subsequente em Informática', 'TINFO', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 24, 0,
 'Formação técnica para desenvolvimento, manutenção e suporte de sistemas e equipamentos de informática.'),
('Técnico Subsequente em Redes de Computadores', 'TREDES', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 24, 0,
 'Formação técnica para projetar, instalar, configurar e manter redes de computadores.'),
('Técnico Subsequente em Administração', 'TADM', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1000, 22, 0,
 'Formação técnica para atuar na gestão de processos administrativos, financeiros e de pessoas.'),
('Técnico Subsequente em Edificações', 'TEDIF', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 26, 0,
 'Formação técnica para atuar em projeto, execução e fiscalização de obras de edificações.'),

-- TECNOLOGO
('Tecnologia em Análise e Desenvolvimento de Sistemas', 'TADS', 'TECNOLOGO', '6 semestres', 'VESPERTINO', 'PRESENCIAL', 2400, 36, 2,
 'Curso superior voltado à formação de profissionais para analisar, projetar, desenvolver e manter sistemas de software.'),
('Tecnologia em Processos Químicos', 'TPQ', 'TECNOLOGO', '6 semestres', 'NOTURNO', 'PRESENCIAL', 2400, 40, 3,
 'Curso superior voltado à formação de profissionais para atuar no controle, na operação e na otimização de processos químicos industriais.'),
('Tecnologia em Gestão Ambiental', 'TGA', 'TECNOLOGO', '6 semestres', 'NOTURNO', 'PRESENCIAL', 2400, 38, 3,
 'Formação de profissionais para planejar, implantar e gerenciar sistemas de gestão e controle ambiental.'),
('Tecnologia em Energias Renováveis', 'TER', 'TECNOLOGO', '6 semestres', 'NOTURNO', 'PRESENCIAL', 2400, 38, 3,
 'Formação de profissionais para projetar, instalar e operar sistemas de geração de energia renovável.'),

-- BACHARELADO
('Bacharelado em Engenharia Civil', 'ECIV', 'BACHARELADO', '10 semestres', 'MATUTINO_VESPERTINO', 'PRESENCIAL', 3600, 55, 5,
 'Formação de engenheiros para projetar, construir e gerenciar obras de infraestrutura e edificações.'),
('Bacharelado em Engenharia de Energia', 'EENERGIA', 'BACHARELADO', '10 semestres', 'MATUTINO_VESPERTINO', 'PRESENCIAL', 3600, 55, 5,
 'Formação de engenheiros para atuar na geração, transmissão, distribuição e uso eficiente de energia.'),
('Bacharelado em Engenharia de Produção', 'EPROD', 'BACHARELADO', '10 semestres', 'MATUTINO_VESPERTINO', 'PRESENCIAL', 3600, 55, 5,
 'Formação de engenheiros para otimizar sistemas produtivos, logística, qualidade e gestão de operações.'),
('Bacharelado em Engenharia Sanitária e Ambiental', 'ESA', 'BACHARELADO', '10 semestres', 'MATUTINO_VESPERTINO', 'PRESENCIAL', 3600, 55, 5,
 'Formação de engenheiros para atuar em saneamento, recursos hídricos e controle da poluição.'),

-- LICENCIATURA
('Licenciatura em Matemática', 'LMAT', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'PRESENCIAL', 3200, 45, 4,
 'Formação de professores para o ensino de Matemática na educação básica.'),
('Licenciatura em Química', 'LQUIM', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'PRESENCIAL', 3200, 45, 4,
 'Formação de professores para o ensino de Química na educação básica.'),
('Licenciatura em Física', 'LFIS', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'PRESENCIAL', 3200, 45, 4,
 'Formação de professores para o ensino de Física na educação básica.'),
('Licenciatura em Informática', 'LINFO', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'PRESENCIAL', 3200, 45, 4,
 'Formação de professores para o ensino de Computação e Informática na educação básica.'),
('Licenciatura em Biologia', 'LBIO', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'PRESENCIAL', 3200, 45, 4,
 'Formação de professores para o ensino de Ciências e Biologia na educação básica.');