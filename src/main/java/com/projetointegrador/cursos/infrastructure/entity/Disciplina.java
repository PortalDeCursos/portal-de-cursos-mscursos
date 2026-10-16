package com.projetointegrador.cursos.infrastructure.entity;

import com.projetointegrador.cursos.infrastructure.enums.SemestreEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "disciplinas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;

    @Column(name = "carga_horaria", nullable = false)
    private Integer cargaHoraria;

    @Enumerated(EnumType.STRING)
    @Column(name = "semestre", nullable = false, length = 20)
    private SemestreEnum semestre;

    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    @Column(name = "obrigatoria", nullable = false)
    private Boolean obrigatoria;

    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "criado_em", nullable = false, insertable = false, updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp(source = SourceType.DB)
    @Column(name = "atualizado_em", insertable = false, nullable = false)
    private LocalDateTime atualizadoEm;
}
