package com.projetointegrador.cursos.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "areas_conhecimento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaConhecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, unique = true, length = 100)
    private String nome;

    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "criado_em", nullable = false, insertable = false, updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp(source = SourceType.DB)
    @Column(name = "atualizado_em", insertable = false, nullable = false)
    private LocalDateTime atualizadoEm;
}
