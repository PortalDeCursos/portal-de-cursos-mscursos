INSERT INTO cursos (
    campus_id, area_conhecimento_id, nome, acronimo, grau, duracao,
    turno, modalidade, carga_horaria,
    qtd_disciplinas_obrigatorias, qtd_disciplinas_optativas, descricao
)
VALUES

-- =====================================================
-- IFAP (Apodi)
-- =====================================================
((SELECT id FROM campus WHERE acronimo = 'IFAP'), (SELECT id FROM areas_conhecimento WHERE nome = 'Recursos Naturais e Ambiente'),
 'Técnico Subsequente em Agropecuária', 'SUBAGR', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em produção agrícola e animal.'),
((SELECT id FROM campus WHERE acronimo = 'IFAP'), (SELECT id FROM areas_conhecimento WHERE nome = 'Produção Industrial e Alimentícia'),
 'Técnico Subsequente em Biocombustíveis', 'SUBBIO', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica na produção de biocombustíveis.'),
((SELECT id FROM campus WHERE acronimo = 'IFAP'), (SELECT id FROM areas_conhecimento WHERE nome = 'Produção Industrial e Alimentícia'),
 'Técnico Subsequente em Química', 'SUBQUI', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em análises e processos químicos.'),
((SELECT id FROM campus WHERE acronimo = 'IFAP'), (SELECT id FROM areas_conhecimento WHERE nome = 'Produção Industrial e Alimentícia'),
 'Tecnologia em Agroindústria', 'TAGI', 'TECNOLOGO', '6 semestres', 'MATUTINO_VESPERTINO', 'PRESENCIAL', 2400, 30, 4, 'Formação para gerenciar a produção e o beneficiamento agroindustrial.'),
((SELECT id FROM campus WHERE acronimo = 'IFAP'), (SELECT id FROM areas_conhecimento WHERE nome = 'Ciências Exatas e da Natureza'),
 'Licenciatura em Química', 'LQUI', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'HIBRIDO', 3200, 40, 4, 'Formação de professores de Química para a educação básica.'),

-- =====================================================
-- IFCA (Caicó)
-- =====================================================
((SELECT id FROM campus WHERE acronimo = 'IFCA'), (SELECT id FROM areas_conhecimento WHERE nome = 'Controle e Processos Industriais'),
 'Técnico Subsequente em Eletrotécnica', 'SUBELT', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em instalações e sistemas elétricos.'),
((SELECT id FROM campus WHERE acronimo = 'IFCA'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Técnico Subsequente em Informática', 'SUBINF', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em programação, redes e manutenção de computadores.'),
((SELECT id FROM campus WHERE acronimo = 'IFCA'), (SELECT id FROM areas_conhecimento WHERE nome = 'Produção Industrial e Alimentícia'),
 'Técnico Subsequente em Têxtil', 'SUBTEX', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em processos da indústria têxtil.'),
((SELECT id FROM campus WHERE acronimo = 'IFCA'), (SELECT id FROM areas_conhecimento WHERE nome = 'Produção Industrial e Alimentícia'),
 'Técnico Subsequente em Vestuário', 'SUBVES', 'SUBSEQUENTE', '4 semestres', 'MATUTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em modelagem e confecção de vestuário.'),
((SELECT id FROM campus WHERE acronimo = 'IFCA'), (SELECT id FROM areas_conhecimento WHERE nome = 'Ciências Humanas e Linguagens'),
 'Tecnologia em Design de Moda', 'TDM', 'TECNOLOGO', '6 semestres', 'VESPERTINO', 'HIBRIDO', 2400, 30, 4, 'Formação para criar e desenvolver coleções de moda.'),
((SELECT id FROM campus WHERE acronimo = 'IFCA'), (SELECT id FROM areas_conhecimento WHERE nome = 'Ciências Exatas e da Natureza'),
 'Licenciatura em Física', 'LFIS', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'PRESENCIAL', 3200, 40, 4, 'Formação de professores de Física para a educação básica.'),

-- =====================================================
-- IFCANG (Canguaretama)
-- =====================================================
((SELECT id FROM campus WHERE acronimo = 'IFCANG'), (SELECT id FROM areas_conhecimento WHERE nome = 'Controle e Processos Industriais'),
 'Técnico Subsequente em Eletromecânica', 'SUBELM', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em sistemas elétricos e mecânicos.'),
((SELECT id FROM campus WHERE acronimo = 'IFCANG'), (SELECT id FROM areas_conhecimento WHERE nome = 'Turismo, Hospitalidade e Lazer'),
 'Técnico Subsequente em Eventos', 'SUBEVE', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em planejamento e organização de eventos.'),
((SELECT id FROM campus WHERE acronimo = 'IFCANG'), (SELECT id FROM areas_conhecimento WHERE nome = 'Turismo, Hospitalidade e Lazer'),
 'Tecnologia em Gestão de Turismo', 'TGTUR', 'TECNOLOGO', '6 semestres', 'MATUTINO_VESPERTINO', 'PRESENCIAL', 2400, 30, 4, 'Formação para gerenciar empreendimentos e destinos turísticos.'),
((SELECT id FROM campus WHERE acronimo = 'IFCANG'), (SELECT id FROM areas_conhecimento WHERE nome = 'Ciências Humanas e Linguagens'),
 'Licenciatura em Educação do Campo', 'LECAM', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'HIBRIDO', 3200, 40, 4, 'Formação de professores para escolas do campo.'),

-- =====================================================
-- IFCM (Ceará-Mirim)
-- =====================================================
((SELECT id FROM campus WHERE acronimo = 'IFCM'), (SELECT id FROM areas_conhecimento WHERE nome = 'Saúde e Bem-Estar'),
 'Técnico Subsequente em Equipamentos Biomédicos', 'SUBEQB', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em manutenção de equipamentos hospitalares.'),
((SELECT id FROM campus WHERE acronimo = 'IFCM'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Técnico Subsequente em Informática', 'SUBINF', 'SUBSEQUENTE', '4 semestres', 'MATUTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em programação, redes e manutenção de computadores.'),
((SELECT id FROM campus WHERE acronimo = 'IFCM'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Técnico Subsequente em Programação de Jogos Digitais', 'SUBPJD', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em criação e programação de jogos digitais.'),
((SELECT id FROM campus WHERE acronimo = 'IFCM'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Tecnologia em Análise e Desenvolvimento de Sistemas', 'TADS', 'TECNOLOGO', '6 semestres', 'NOTURNO', 'HIBRIDO', 2400, 30, 4, 'Formação para desenvolver e manter sistemas de software.'),
((SELECT id FROM campus WHERE acronimo = 'IFCM'), (SELECT id FROM areas_conhecimento WHERE nome = 'Ciências Exatas e da Natureza'),
 'Licenciatura em Matemática', 'LMAT', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'PRESENCIAL', 3200, 40, 4, 'Formação de professores de Matemática para a educação básica.'),

-- =====================================================
-- IFCN (Currais Novos)
-- =====================================================
((SELECT id FROM campus WHERE acronimo = 'IFCN'), (SELECT id FROM areas_conhecimento WHERE nome = 'Produção Industrial e Alimentícia'),
 'Técnico Subsequente em Alimentos', 'SUBALI', 'SUBSEQUENTE', '4 semestres', 'MATUTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em processamento e qualidade de alimentos.'),
((SELECT id FROM campus WHERE acronimo = 'IFCN'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Técnico Subsequente em Manutenção e Suporte em Informática', 'SUBMSI', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em manutenção e suporte de computadores.'),
((SELECT id FROM campus WHERE acronimo = 'IFCN'), (SELECT id FROM areas_conhecimento WHERE nome = 'Recursos Naturais e Ambiente'),
 'Técnico Subsequente em Mineração', 'SUBMIN', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em extração e beneficiamento de minérios.'),
((SELECT id FROM campus WHERE acronimo = 'IFCN'), (SELECT id FROM areas_conhecimento WHERE nome = 'Produção Industrial e Alimentícia'),
 'Tecnologia em Alimentos', 'TALI', 'TECNOLOGO', '6 semestres', 'MATUTINO_VESPERTINO', 'PRESENCIAL', 2400, 30, 4, 'Formação para atuar na produção e no controle de qualidade de alimentos.'),
((SELECT id FROM campus WHERE acronimo = 'IFCN'), (SELECT id FROM areas_conhecimento WHERE nome = 'Ciências Exatas e da Natureza'),
 'Licenciatura em Química', 'LQUI', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'HIBRIDO', 3200, 40, 4, 'Formação de professores de Química para a educação básica.'),

-- =====================================================
-- IFIP (Ipanguaçu)
-- =====================================================
((SELECT id FROM campus WHERE acronimo = 'IFIP'), (SELECT id FROM areas_conhecimento WHERE nome = 'Recursos Naturais e Ambiente'),
 'Técnico Subsequente em Agroecologia', 'SUBAGE', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em produção agrícola sustentável.'),
((SELECT id FROM campus WHERE acronimo = 'IFIP'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Técnico Subsequente em Informática', 'SUBINF', 'SUBSEQUENTE', '4 semestres', 'MATUTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em programação, redes e manutenção de computadores.'),
((SELECT id FROM campus WHERE acronimo = 'IFIP'), (SELECT id FROM areas_conhecimento WHERE nome = 'Recursos Naturais e Ambiente'),
 'Técnico Subsequente em Meio Ambiente', 'SUBMAM', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em controle e preservação ambiental.'),
((SELECT id FROM campus WHERE acronimo = 'IFIP'), (SELECT id FROM areas_conhecimento WHERE nome = 'Recursos Naturais e Ambiente'),
 'Tecnologia em Agroecologia', 'TAGE', 'TECNOLOGO', '6 semestres', 'MATUTINO_VESPERTINO', 'HIBRIDO', 2400, 30, 4, 'Formação para atuar em sistemas de produção agroecológica.'),
((SELECT id FROM campus WHERE acronimo = 'IFIP'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Licenciatura em Informática', 'LINF', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'PRESENCIAL', 3200, 40, 4, 'Formação de professores de Informática para a educação básica.'),

-- =====================================================
-- IFJC (João Câmara)
-- =====================================================
((SELECT id FROM campus WHERE acronimo = 'IFJC'), (SELECT id FROM areas_conhecimento WHERE nome = 'Gestão e Negócios'),
 'Técnico Subsequente em Administração', 'SUBADM', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em gestão de processos administrativos.'),
((SELECT id FROM campus WHERE acronimo = 'IFJC'), (SELECT id FROM areas_conhecimento WHERE nome = 'Controle e Processos Industriais'),
 'Técnico Subsequente em Eletrotécnica', 'SUBELT', 'SUBSEQUENTE', '4 semestres', 'MATUTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em instalações e sistemas elétricos.'),
((SELECT id FROM campus WHERE acronimo = 'IFJC'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Técnico Subsequente em Informática', 'SUBINF', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em programação, redes e manutenção de computadores.'),
((SELECT id FROM campus WHERE acronimo = 'IFJC'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Tecnologia em Sistemas para Internet', 'TSI', 'TECNOLOGO', '6 semestres', 'NOTURNO', 'HIBRIDO', 2400, 30, 4, 'Formação para desenvolver sites e aplicações web.'),
((SELECT id FROM campus WHERE acronimo = 'IFJC'), (SELECT id FROM areas_conhecimento WHERE nome = 'Ciências Exatas e da Natureza'),
 'Licenciatura em Física', 'LFIS', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'PRESENCIAL', 3200, 40, 4, 'Formação de professores de Física para a educação básica.'),

-- =====================================================
-- IFJUC (Jucurutu)
-- =====================================================
((SELECT id FROM campus WHERE acronimo = 'IFJUC'), (SELECT id FROM areas_conhecimento WHERE nome = 'Produção Industrial e Alimentícia'),
 'Técnico Subsequente em Alimentos', 'SUBALI', 'SUBSEQUENTE', '4 semestres', 'MATUTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em processamento e qualidade de alimentos.'),
((SELECT id FROM campus WHERE acronimo = 'IFJUC'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Técnico Subsequente em Informática', 'SUBINF', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em programação, redes e manutenção de computadores.'),

-- =====================================================
-- IFLAJ (Lajes)
-- =====================================================
((SELECT id FROM campus WHERE acronimo = 'IFLAJ'), (SELECT id FROM areas_conhecimento WHERE nome = 'Gestão e Negócios'),
 'Técnico Subsequente em Administração', 'SUBADM', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em gestão de processos administrativos.'),
((SELECT id FROM campus WHERE acronimo = 'IFLAJ'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Técnico Subsequente em Informática', 'SUBINF', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em programação, redes e manutenção de computadores.'),
((SELECT id FROM campus WHERE acronimo = 'IFLAJ'), (SELECT id FROM areas_conhecimento WHERE nome = 'Recursos Naturais e Ambiente'),
 'Tecnologia em Gestão Ambiental', 'TGA', 'TECNOLOGO', '6 semestres', 'NOTURNO', 'HIBRIDO', 2400, 30, 4, 'Formação para planejar e gerenciar ações de preservação ambiental.'),

-- =====================================================
-- IFMC (Macau)
-- =====================================================
((SELECT id FROM campus WHERE acronimo = 'IFMC'), (SELECT id FROM areas_conhecimento WHERE nome = 'Produção Industrial e Alimentícia'),
 'Técnico Subsequente em Química', 'SUBQUI', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em análises e processos químicos.'),
((SELECT id FROM campus WHERE acronimo = 'IFMC'), (SELECT id FROM areas_conhecimento WHERE nome = 'Recursos Naturais e Ambiente'),
 'Técnico Subsequente em Recursos Pesqueiros', 'SUBREP', 'SUBSEQUENTE', '4 semestres', 'MATUTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em pesca e aquicultura.'),
((SELECT id FROM campus WHERE acronimo = 'IFMC'), (SELECT id FROM areas_conhecimento WHERE nome = 'Produção Industrial e Alimentícia'),
 'Tecnologia em Processos Químicos', 'TPQ', 'TECNOLOGO', '6 semestres', 'VESPERTINO', 'PRESENCIAL', 2400, 30, 4, 'Formação para atuar em processos e controle da indústria química.'),
((SELECT id FROM campus WHERE acronimo = 'IFMC'), (SELECT id FROM areas_conhecimento WHERE nome = 'Ciências Exatas e da Natureza'),
 'Licenciatura em Biologia', 'LBIO', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'HIBRIDO', 3200, 40, 4, 'Formação de professores de Biologia para a educação básica.'),

-- =====================================================
-- IFMO (Mossoró)
-- =====================================================
((SELECT id FROM campus WHERE acronimo = 'IFMO'), (SELECT id FROM areas_conhecimento WHERE nome = 'Infraestrutura e Construção'),
 'Técnico Subsequente em Edificações', 'SUBEDI', 'SUBSEQUENTE', '4 semestres', 'MATUTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em projeto e execução de edificações.'),
((SELECT id FROM campus WHERE acronimo = 'IFMO'), (SELECT id FROM areas_conhecimento WHERE nome = 'Controle e Processos Industriais'),
 'Técnico Subsequente em Eletrotécnica', 'SUBELT', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em instalações e sistemas elétricos.'),
((SELECT id FROM campus WHERE acronimo = 'IFMO'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Técnico Subsequente em Informática', 'SUBINF', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em programação, redes e manutenção de computadores.'),
((SELECT id FROM campus WHERE acronimo = 'IFMO'), (SELECT id FROM areas_conhecimento WHERE nome = 'Controle e Processos Industriais'),
 'Técnico Subsequente em Mecânica', 'SUBMEC', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em projeto e manutenção de máquinas.'),
((SELECT id FROM campus WHERE acronimo = 'IFMO'), (SELECT id FROM areas_conhecimento WHERE nome = 'Produção Industrial e Alimentícia'),
 'Técnico Subsequente em Petróleo e Gás', 'SUBPEG', 'SUBSEQUENTE', '4 semestres', 'MATUTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em exploração e produção de petróleo e gás.'),
((SELECT id FROM campus WHERE acronimo = 'IFMO'), (SELECT id FROM areas_conhecimento WHERE nome = 'Infraestrutura e Construção'),
 'Técnico Subsequente em Saneamento', 'SUBSAN', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em sistemas de água e esgoto.'),
((SELECT id FROM campus WHERE acronimo = 'IFMO'), (SELECT id FROM areas_conhecimento WHERE nome = 'Controle e Processos Industriais'),
 'Técnico Subsequente em Sistemas de Energias Renováveis', 'SUBSER', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em energia solar e eólica.'),
((SELECT id FROM campus WHERE acronimo = 'IFMO'), (SELECT id FROM areas_conhecimento WHERE nome = 'Recursos Naturais e Ambiente'),
 'Tecnologia em Gestão Ambiental', 'TGA', 'TECNOLOGO', '6 semestres', 'MATUTINO_VESPERTINO', 'HIBRIDO', 2400, 30, 4, 'Formação para planejar e gerenciar ações de preservação ambiental.'),
((SELECT id FROM campus WHERE acronimo = 'IFMO'), (SELECT id FROM areas_conhecimento WHERE nome = 'Infraestrutura e Construção'),
 'Tecnologia em Saneamento Ambiental', 'TSAN', 'TECNOLOGO', '6 semestres', 'NOTURNO', 'PRESENCIAL', 2400, 30, 4, 'Formação para atuar em projetos e operação de saneamento ambiental.'),
((SELECT id FROM campus WHERE acronimo = 'IFMO'), (SELECT id FROM areas_conhecimento WHERE nome = 'Ciências Exatas e da Natureza'),
 'Licenciatura em Matemática', 'LMAT', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'HIBRIDO', 3200, 40, 4, 'Formação de professores de Matemática para a educação básica.'),

-- =====================================================
-- CNAT (Natal - Central)
-- =====================================================
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Gestão e Negócios'),
 'Técnico Subsequente em Administração', 'SUBADM', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em gestão de processos administrativos.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Recursos Naturais e Ambiente'),
 'Técnico Subsequente em Controle Ambiental', 'SUBCAM', 'SUBSEQUENTE', '4 semestres', 'MATUTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em monitoramento e controle ambiental.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Infraestrutura e Construção'),
 'Técnico Subsequente em Edificações', 'SUBEDI', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em projeto e execução de edificações.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Controle e Processos Industriais'),
 'Técnico Subsequente em Eletrotécnica', 'SUBELT', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em instalações e sistemas elétricos.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Infraestrutura e Construção'),
 'Técnico Subsequente em Estradas', 'SUBEST', 'SUBSEQUENTE', '4 semestres', 'MATUTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em projeto e construção de estradas.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Recursos Naturais e Ambiente'),
 'Técnico Subsequente em Geologia', 'SUBGEO', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em estudo e mapeamento geológico.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Técnico Subsequente em Manutenção e Suporte em Informática', 'SUBMSI', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em manutenção e suporte de computadores.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Controle e Processos Industriais'),
 'Técnico Subsequente em Mecânica', 'SUBMEC', 'SUBSEQUENTE', '4 semestres', 'MATUTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em projeto e manutenção de máquinas.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Recursos Naturais e Ambiente'),
 'Técnico Subsequente em Mineração', 'SUBMIN', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em extração e beneficiamento de minérios.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Produção Industrial e Alimentícia'),
 'Técnico Subsequente em Petróleo e Gás', 'SUBPEG', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em exploração e produção de petróleo e gás.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Técnico Subsequente em Redes de Computadores', 'SUBRDC', 'SUBSEQUENTE', '4 semestres', 'MATUTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em instalação e administração de redes.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Saúde e Bem-Estar'),
 'Técnico Subsequente em Segurança do Trabalho', 'SUBSET', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em prevenção de acidentes e segurança no trabalho.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Tecnologia em Análise e Desenvolvimento de Sistemas', 'TADS', 'TECNOLOGO', '6 semestres', 'NOTURNO', 'HIBRIDO', 2400, 30, 4, 'Formação para desenvolver e manter sistemas de software.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Infraestrutura e Construção'),
 'Tecnologia em Construção de Edifícios', 'TCE', 'TECNOLOGO', '6 semestres', 'MATUTINO_VESPERTINO', 'PRESENCIAL', 2400, 30, 4, 'Formação para planejar e executar obras de edificações.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Recursos Naturais e Ambiente'),
 'Tecnologia em Gestão Ambiental', 'TGA', 'TECNOLOGO', '6 semestres', 'NOTURNO', 'HIBRIDO', 2400, 30, 4, 'Formação para planejar e gerenciar ações de preservação ambiental.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Gestão e Negócios'),
 'Tecnologia em Gestão Pública', 'TGP', 'TECNOLOGO', '6 semestres', 'VESPERTINO', 'PRESENCIAL', 2400, 30, 4, 'Formação para atuar na gestão de órgãos e políticas públicas.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Gestão e Negócios'),
 'Tecnologia em Processos Gerenciais', 'TPG', 'TECNOLOGO', '6 semestres', 'NOTURNO', 'HIBRIDO', 2400, 30, 4, 'Formação para gerenciar processos e equipes nas organizações.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Tecnologia em Redes de Computadores', 'TRC', 'TECNOLOGO', '6 semestres', 'MATUTINO_VESPERTINO', 'PRESENCIAL', 2400, 30, 4, 'Formação para projetar, instalar e administrar redes de computadores.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Infraestrutura e Construção'),
 'Engenharia Civil', 'BECIV', 'BACHARELADO', '10 semestres', 'VESPERTINO', 'PRESENCIAL', 3600, 50, 6, 'Formação de engenheiros para projetar e executar obras de construção civil.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Controle e Processos Industriais'),
 'Engenharia de Energia', 'BEENE', 'BACHARELADO', '10 semestres', 'MATUTINO_VESPERTINO', 'PRESENCIAL', 3600, 50, 6, 'Formação de engenheiros para atuar com geração e eficiência energética.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Infraestrutura e Construção'),
 'Engenharia Sanitária e Ambiental', 'BESAM', 'BACHARELADO', '10 semestres', 'VESPERTINO', 'PRESENCIAL', 3600, 50, 6, 'Formação de engenheiros para projetos de saneamento e meio ambiente.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Ciências Exatas e da Natureza'),
 'Licenciatura em Física', 'LFIS', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'PRESENCIAL', 3200, 40, 4, 'Formação de professores de Física para a educação básica.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Ciências Humanas e Linguagens'),
 'Licenciatura em Geografia', 'LGEO', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'HIBRIDO', 3200, 40, 4, 'Formação de professores de Geografia para a educação básica.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Ciências Humanas e Linguagens'),
 'Licenciatura em Letras - Espanhol', 'LLESP', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'PRESENCIAL', 3200, 40, 4, 'Formação de professores de Língua Espanhola.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Ciências Humanas e Linguagens'),
 'Licenciatura em Letras - Língua Portuguesa', 'LLPOR', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'PRESENCIAL', 3200, 40, 4, 'Formação de professores de Língua Portuguesa.'),
((SELECT id FROM campus WHERE acronimo = 'CNAT'), (SELECT id FROM areas_conhecimento WHERE nome = 'Ciências Exatas e da Natureza'),
 'Licenciatura em Matemática', 'LMAT', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'HIBRIDO', 3200, 40, 4, 'Formação de professores de Matemática para a educação básica.'),

-- =====================================================
-- IFCAL (Natal - Centro Histórico)
-- =====================================================
((SELECT id FROM campus WHERE acronimo = 'IFCAL'), (SELECT id FROM areas_conhecimento WHERE nome = 'Turismo, Hospitalidade e Lazer'),
 'Técnico Subsequente em Eventos', 'SUBEVE', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em planejamento e organização de eventos.'),
((SELECT id FROM campus WHERE acronimo = 'IFCAL'), (SELECT id FROM areas_conhecimento WHERE nome = 'Turismo, Hospitalidade e Lazer'),
 'Técnico Subsequente em Guia de Turismo', 'SUBGUI', 'SUBSEQUENTE', '4 semestres', 'MATUTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica para conduzir e orientar turistas.'),
((SELECT id FROM campus WHERE acronimo = 'IFCAL'), (SELECT id FROM areas_conhecimento WHERE nome = 'Turismo, Hospitalidade e Lazer'),
 'Técnico Subsequente em Lazer', 'SUBLAZ', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em atividades de recreação e lazer.'),
((SELECT id FROM campus WHERE acronimo = 'IFCAL'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Técnico Subsequente em Multimídia', 'SUBMUL', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em criação de conteúdo multimídia.'),
((SELECT id FROM campus WHERE acronimo = 'IFCAL'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Técnico Subsequente em Produção de Áudio e Vídeo', 'SUBPAV', 'SUBSEQUENTE', '4 semestres', 'MATUTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em gravação, edição e produção audiovisual.'),
((SELECT id FROM campus WHERE acronimo = 'IFCAL'), (SELECT id FROM areas_conhecimento WHERE nome = 'Ciências Humanas e Linguagens'),
 'Tecnologia em Produção Cultural', 'TPC', 'TECNOLOGO', '6 semestres', 'NOTURNO', 'HIBRIDO', 2400, 30, 4, 'Formação para planejar e produzir projetos culturais.'),

-- =====================================================
-- IFZL (Natal - Zona Leste / EaD)
-- =====================================================
((SELECT id FROM campus WHERE acronimo = 'IFZL'), (SELECT id FROM areas_conhecimento WHERE nome = 'Gestão e Negócios'),
 'Técnico Subsequente em Administração', 'SUBADM', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'EAD', 1200, 20, 0, 'Formação técnica a distância em gestão de processos administrativos.'),
((SELECT id FROM campus WHERE acronimo = 'IFZL'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Técnico Subsequente em Manutenção e Suporte em Informática', 'SUBMSI', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'EAD', 1200, 20, 0, 'Formação técnica a distância em manutenção e suporte de computadores.'),
((SELECT id FROM campus WHERE acronimo = 'IFZL'), (SELECT id FROM areas_conhecimento WHERE nome = 'Saúde e Bem-Estar'),
 'Técnico Subsequente em Segurança do Trabalho', 'SUBSET', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'EAD', 1200, 20, 0, 'Formação técnica a distância em prevenção de acidentes e segurança no trabalho.'),
((SELECT id FROM campus WHERE acronimo = 'IFZL'), (SELECT id FROM areas_conhecimento WHERE nome = 'Recursos Naturais e Ambiente'),
 'Tecnologia em Gestão Ambiental', 'TGA', 'TECNOLOGO', '6 semestres', 'NOTURNO', 'EAD', 2400, 30, 4, 'Formação a distância para planejar e gerenciar ações de preservação ambiental.'),
((SELECT id FROM campus WHERE acronimo = 'IFZL'), (SELECT id FROM areas_conhecimento WHERE nome = 'Gestão e Negócios'),
 'Tecnologia em Gestão Pública', 'TGP', 'TECNOLOGO', '6 semestres', 'NOTURNO', 'EAD', 2400, 30, 4, 'Formação a distância para atuar na gestão de órgãos e políticas públicas.'),
((SELECT id FROM campus WHERE acronimo = 'IFZL'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Tecnologia em Sistemas para Internet', 'TSI', 'TECNOLOGO', '6 semestres', 'NOTURNO', 'EAD', 2400, 30, 4, 'Formação a distância para desenvolver sites e aplicações web.'),
((SELECT id FROM campus WHERE acronimo = 'IFZL'), (SELECT id FROM areas_conhecimento WHERE nome = 'Ciências Humanas e Linguagens'),
 'Licenciatura em Letras - Língua Portuguesa', 'LLPOR', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'EAD', 3200, 40, 4, 'Formação a distância de professores de Língua Portuguesa.'),

-- =====================================================
-- IFZN (Natal - Zona Norte)
-- =====================================================
((SELECT id FROM campus WHERE acronimo = 'IFZN'), (SELECT id FROM areas_conhecimento WHERE nome = 'Gestão e Negócios'),
 'Técnico Subsequente em Comércio', 'SUBCOM', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em vendas e gestão comercial.'),
((SELECT id FROM campus WHERE acronimo = 'IFZN'), (SELECT id FROM areas_conhecimento WHERE nome = 'Controle e Processos Industriais'),
 'Técnico Subsequente em Eletrônica', 'SUBELE', 'SUBSEQUENTE', '4 semestres', 'MATUTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em circuitos e sistemas eletrônicos.'),
((SELECT id FROM campus WHERE acronimo = 'IFZN'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Técnico Subsequente em Manutenção e Suporte em Informática', 'SUBMSI', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em manutenção e suporte de computadores.'),
((SELECT id FROM campus WHERE acronimo = 'IFZN'), (SELECT id FROM areas_conhecimento WHERE nome = 'Gestão e Negócios'),
 'Tecnologia em Marketing', 'TMKT', 'TECNOLOGO', '6 semestres', 'NOTURNO', 'HIBRIDO', 2400, 30, 4, 'Formação para planejar estratégias de marketing e vendas.'),
((SELECT id FROM campus WHERE acronimo = 'IFZN'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Tecnologia em Redes de Computadores', 'TRC', 'TECNOLOGO', '6 semestres', 'MATUTINO_VESPERTINO', 'PRESENCIAL', 2400, 30, 4, 'Formação para projetar, instalar e administrar redes de computadores.'),

-- =====================================================
-- IFNC (Nova Cruz)
-- =====================================================
((SELECT id FROM campus WHERE acronimo = 'IFNC'), (SELECT id FROM areas_conhecimento WHERE nome = 'Gestão e Negócios'),
 'Técnico Subsequente em Administração', 'SUBADM', 'SUBSEQUENTE', '4 semestres', 'MATUTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em gestão de processos administrativos.'),
((SELECT id FROM campus WHERE acronimo = 'IFNC'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Técnico Subsequente em Informática', 'SUBINF', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em programação, redes e manutenção de computadores.'),
((SELECT id FROM campus WHERE acronimo = 'IFNC'), (SELECT id FROM areas_conhecimento WHERE nome = 'Produção Industrial e Alimentícia'),
 'Técnico Subsequente em Química', 'SUBQUI', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em análises e processos químicos.'),
((SELECT id FROM campus WHERE acronimo = 'IFNC'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Tecnologia em Análise e Desenvolvimento de Sistemas', 'TADS', 'TECNOLOGO', '7 semestres', 'MATUTINO', 'PRESENCIAL', 2594, 36, 14, 'Formação para desenvolver e manter sistemas de software.'),
((SELECT id FROM campus WHERE acronimo = 'IFNC'), (SELECT id FROM areas_conhecimento WHERE nome = 'Produção Industrial e Alimentícia'),
 'Tecnologia em Processos Químicos', 'TPQ', 'TECNOLOGO', '6 semestres', 'VESPERTINO', 'PRESENCIAL', 2400, 30, 4, 'Formação para atuar em processos e controle da indústria química.'),
((SELECT id FROM campus WHERE acronimo = 'IFNC'), (SELECT id FROM areas_conhecimento WHERE nome = 'Ciências Exatas e da Natureza'),
 'Licenciatura em Química', 'LQUI', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'PRESENCIAL', 3200, 40, 4, 'Formação de professores de Química para a educação básica.'),

-- =====================================================
-- IFPAAS (Parelhas)
-- =====================================================
((SELECT id FROM campus WHERE acronimo = 'IFPAAS'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Técnico Subsequente em Informática', 'SUBINF', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em programação, redes e manutenção de computadores.'),
((SELECT id FROM campus WHERE acronimo = 'IFPAAS'), (SELECT id FROM areas_conhecimento WHERE nome = 'Recursos Naturais e Ambiente'),
 'Técnico Subsequente em Mineração', 'SUBMIN', 'SUBSEQUENTE', '4 semestres', 'MATUTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em extração e beneficiamento de minérios.'),

-- =====================================================
-- IFPAR (Parnamirim)
-- =====================================================
((SELECT id FROM campus WHERE acronimo = 'IFPAR'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Técnico Subsequente em Informática', 'SUBINF', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em programação, redes e manutenção de computadores.'),
((SELECT id FROM campus WHERE acronimo = 'IFPAR'), (SELECT id FROM areas_conhecimento WHERE nome = 'Controle e Processos Industriais'),
 'Técnico Subsequente em Mecatrônica', 'SUBMCT', 'SUBSEQUENTE', '4 semestres', 'NOTURNO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em automação e sistemas mecatrônicos.'),
((SELECT id FROM campus WHERE acronimo = 'IFPAR'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Tecnologia em Sistemas para Internet', 'TSI', 'TECNOLOGO', '6 semestres', 'MATUTINO_VESPERTINO', 'HIBRIDO', 2400, 30, 4, 'Formação para desenvolver sites e aplicações web.'),
((SELECT id FROM campus WHERE acronimo = 'IFPAR'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Licenciatura em Informática', 'LINF', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'PRESENCIAL', 3200, 40, 4, 'Formação de professores de Informática para a educação básica.'),

-- =====================================================
-- IFPF (Pau dos Ferros)
-- =====================================================
((SELECT id FROM campus WHERE acronimo = 'IFPF'), (SELECT id FROM areas_conhecimento WHERE nome = 'Produção Industrial e Alimentícia'),
 'Técnico Subsequente em Alimentos', 'SUBALI', 'SUBSEQUENTE', '4 semestres', 'MATUTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica em processamento e qualidade de alimentos.'),
((SELECT id FROM campus WHERE acronimo = 'IFPF'), (SELECT id FROM areas_conhecimento WHERE nome = 'Recursos Naturais e Ambiente'),
 'Técnico Subsequente em Apicultura', 'SUBAPI', 'SUBSEQUENTE', '4 semestres', 'VESPERTINO', 'PRESENCIAL', 1200, 20, 0, 'Formação técnica na criação de abelhas e produção de mel.'),
((SELECT id FROM campus WHERE acronimo = 'IFPF'), (SELECT id FROM areas_conhecimento WHERE nome = 'Informação e Comunicação'),
 'Tecnologia em Análise e Desenvolvimento de Sistemas', 'TADS', 'TECNOLOGO', '6 semestres', 'NOTURNO', 'HIBRIDO', 2400, 30, 4, 'Formação para desenvolver e manter sistemas de software.'),
((SELECT id FROM campus WHERE acronimo = 'IFPF'), (SELECT id FROM areas_conhecimento WHERE nome = 'Ciências Exatas e da Natureza'),
 'Licenciatura em Química', 'LQUI', 'LICENCIATURA', '8 semestres', 'NOTURNO', 'PRESENCIAL', 3200, 40, 4, 'Formação de professores de Química para a educação básica.');