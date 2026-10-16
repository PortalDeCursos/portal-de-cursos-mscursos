package com.projetointegrador.cursos.infrastructure.entity;

import com.projetointegrador.cursos.infrastructure.enums.GrauEnum;
import com.projetointegrador.cursos.infrastructure.enums.ModalidadeEnum;
import com.projetointegrador.cursos.infrastructure.enums.TurnoEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "acronimo", nullable = false, unique = true, length = 10)
    private String acronimo;

    @Enumerated(EnumType.STRING)
    @Column(name = "grau", nullable = false, length = 50)
    private GrauEnum grau;

    @Column(name = "duracao", nullable = false, length = 50)
    private String duracao;

    @Enumerated(EnumType.STRING)
    @Column(name = "turno", nullable = false, length = 50)
    private TurnoEnum turno;

    @Enumerated(EnumType.STRING)
    @Column(name = "modalidade", nullable = false, length = 50)
    private ModalidadeEnum modalidade;

    @Column(name = "carga_horaria", nullable = false)
    private Integer cargaHoraria;

    @Column(name = "qtd_disciplinas_obrigatorias", nullable = false)
    private Integer qtdDisciplinasObrigatorias;

    @Column(name = "qtd_disciplinas_optativas", nullable = false)
    private Integer qtdDisciplinasOptativas;

    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "criado_em", nullable = false, insertable = false, updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp(source = SourceType.DB)
    @Column(name = "atualizado_em", insertable = false, nullable = false)
    private LocalDateTime atualizadoEm;

    @OneToMany(mappedBy = "curso")
    @Builder.Default
    private List<Disciplina> disciplinas = new ArrayList<>();
}
