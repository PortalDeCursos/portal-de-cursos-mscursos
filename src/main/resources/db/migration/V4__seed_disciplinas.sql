INSERT INTO disciplinas (
    curso_id, nome, codigo, carga_horaria,
    semestre, descricao, obrigatoria
)
VALUES
-- =====================================================
-- PRIMEIRO SEMESTRE
-- =====================================================
((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Empreendedorismo', 'TADS-EMP-01', 30, 'PRIMEIRO',
 'Estudo dos conceitos de empreendedorismo e desenvolvimento de iniciativas de negócio.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Língua Inglesa', 'TADS-LIN-01', 60, 'PRIMEIRO',
 'Desenvolvimento de habilidades de leitura, compreensão e comunicação em língua inglesa.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Matemática Discreta', 'TADS-MDI-01', 60, 'PRIMEIRO',
 'Estudo de fundamentos matemáticos aplicados à computação.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Programação de Computadores', 'TADS-PCO-01', 90, 'PRIMEIRO',
 'Introdução à lógica de programação, algoritmos e desenvolvimento de programas.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Seminário de Integração Acadêmica', 'TADS-SIA-01', 4, 'PRIMEIRO',
 'Apresentação do curso, da instituição e da integração à vida acadêmica.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Web Design', 'TADS-WDE-01', 60, 'PRIMEIRO',
 'Estudo dos fundamentos de design e desenvolvimento de interfaces para a web.', TRUE),

-- =====================================================
-- SEGUNDO SEMESTRE
-- =====================================================
((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Algoritmos', 'TADS-ALG-02', 60, 'SEGUNDO',
 'Estudo de algoritmos, estruturas de controle e técnicas de resolução de problemas.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Epistemologia da Ciência', 'TADS-EPC-02', 30, 'SEGUNDO',
 'Estudo dos fundamentos do conhecimento científico e da produção da ciência.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Inglês para Informática', 'TADS-IFI-02', 60, 'SEGUNDO',
 'Estudo da língua inglesa aplicada à leitura e compreensão de conteúdos da área de informática.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Língua Portuguesa', 'TADS-LPO-02', 60, 'SEGUNDO',
 'Desenvolvimento da comunicação oral e escrita, com foco na produção textual.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Programação Orientada a Objetos', 'TADS-POO-02', 90, 'SEGUNDO',
 'Estudo dos conceitos e práticas de programação orientada a objetos.', TRUE),

-- =====================================================
-- TERCEIRO SEMESTRE
-- =====================================================
((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Arquitetura de Computadores', 'TADS-ACO-03', 60, 'TERCEIRO',
 'Estudo da organização, dos componentes e do funcionamento dos computadores.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Computador e Sociedade', 'TADS-CES-03', 30, 'TERCEIRO',
 'Discussão dos impactos sociais, culturais e éticos da tecnologia da informação.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Interface Humano-Computador', 'TADS-IHC-03', 60, 'TERCEIRO',
 'Estudo dos princípios de interação, usabilidade e experiência do usuário.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Metodologia do Trabalho Científico', 'TADS-MTC-03', 30, 'TERCEIRO',
 'Estudo dos métodos e normas para elaboração de trabalhos científicos.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Redes de Computadores', 'TADS-RDC-03', 60, 'TERCEIRO',
 'Estudo dos conceitos, protocolos e funcionamento das redes de computadores.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Seminário de Iniciação à Pesquisa e à Extensão', 'TADS-SPE-03', 30, 'TERCEIRO',
 'Apresentação de práticas de pesquisa e extensão no contexto acadêmico.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Sistemas Digitais', 'TADS-SDI-03', 60, 'TERCEIRO',
 'Estudo de sistemas digitais, circuitos lógicos e representação de informações.', TRUE),

-- =====================================================
-- QUARTO SEMESTRE
-- =====================================================
((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Análise e Projeto Orientado a Objetos', 'TADS-APO-04', 60, 'QUARTO',
 'Estudo de técnicas de análise e projeto de sistemas utilizando orientação a objetos.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Banco de Dados', 'TADS-BDD-04', 60, 'QUARTO',
 'Estudo de modelagem, criação e manipulação de bancos de dados.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Desenvolvimento de Sistemas Web', 'TADS-DSW-04', 90, 'QUARTO',
 'Estudo de tecnologias e práticas para desenvolvimento de aplicações web.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Estrutura de Dados Lineares', 'TADS-EDL-04', 60, 'QUARTO',
 'Estudo de estruturas de dados lineares e seus algoritmos de manipulação.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Seminário de Orientação ao Projeto de Desenvolvimento de Sistema Web', 'TADS-SOW-04', 30, 'QUARTO',
 'Orientação e acompanhamento do projeto de desenvolvimento de um sistema web.', TRUE),

-- =====================================================
-- QUINTO SEMESTRE
-- =====================================================
((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Arquitetura de Software', 'TADS-ADS-05', 60, 'QUINTO',
 'Estudo de estilos, padrões e decisões arquiteturais no desenvolvimento de software.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Desenvolvimento de Sistemas Distribuídos', 'TADS-DSD-05', 90, 'QUINTO',
 'Estudo de conceitos, arquiteturas e tecnologias para sistemas distribuídos.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Processo de Software', 'TADS-PDS-05', 60, 'QUINTO',
 'Estudo dos processos, modelos e práticas utilizados no desenvolvimento de software.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Programação e Administração de Banco de Dados', 'TADS-PBD-05', 60, 'QUINTO',
 'Estudo de programação, administração e gerenciamento de bancos de dados.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Seminário de Orientação ao Projeto de Desenvolvimento de Sistemas Distribuídos', 'TADS-SOD-05', 30, 'QUINTO',
 'Orientação e acompanhamento do projeto de desenvolvimento de sistemas distribuídos.', TRUE),

-- =====================================================
-- SEXTO SEMESTRE
-- =====================================================
((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Desenvolvimento de Sistemas Coorporativos', 'TADS-DSC-06', 60, 'SEXTO',
 'Estudo de tecnologias e práticas para desenvolvimento de sistemas corporativos.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Estrutura de Dados Não Lineares', 'TADS-EDN-06', 60, 'SEXTO',
 'Estudo de estruturas de dados não lineares e seus algoritmos.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Gerência de Projetos', 'TADS-GDP-06', 60, 'SEXTO',
 'Estudo de planejamento, execução, monitoramento e controle de projetos.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Seminário de Orientação ao Projeto de Desenvolvimento de Sistema Coorporativo', 'TADS-SOC-06', 30, 'SEXTO',
 'Orientação e acompanhamento do projeto de desenvolvimento de um sistema corporativo.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Sistemas Operacionais', 'TADS-SOP-06', 60, 'SEXTO',
 'Estudo dos sistemas operacionais, seus componentes e mecanismos de gerenciamento.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Teste de Software', 'TADS-TDS-06', 60, 'SEXTO',
 'Estudo de técnicas, níveis e estratégias de teste e qualidade de software.', TRUE),

-- =====================================================
-- SÉTIMO SEMESTRE
-- =====================================================
((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Gestão de Tecnologia da Informação', 'TADS-GTI-07', 60, 'SETIMO',
 'Estudo da gestão de recursos, serviços e processos de tecnologia da informação.', TRUE),

((SELECT c.id FROM cursos c JOIN campus ca ON ca.id = c.campus_id
  WHERE c.acronimo = 'TADS' AND ca.acronimo = 'IFNC'),
 'Seminário de Orientação para Trabalho de Conclusão de Curso', 'TADS-STC-07', 30, 'SETIMO',
 'Orientação para planejamento e desenvolvimento do trabalho de conclusão de curso.', TRUE);