package com.br.gerenciadorportfolio.entity;


import com.br.gerenciadorportfolio.entity.enums.ClassificacaoRisco;
import com.br.gerenciadorportfolio.entity.enums.StatusProjeto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projeto")
@Data
public class Projeto {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJETO_ID_SEQ")
    @SequenceGenerator(name = "PROJETO_ID_SEQ", sequenceName = "PROJETO_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_previsao_fim")
    private LocalDate dataPrevisaoFim;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(length = 5000)
    private String descricao;

    private BigDecimal orcamento;

    @ManyToOne
    private Pessoa gerente;

    @Column(name = "status", length = 45)
    @Enumerated(EnumType.STRING)
    private StatusProjeto status;

    @Column(name = "risco", length = 45)
    @Enumerated(EnumType.STRING)
    private ClassificacaoRisco classificacaoRisco;

}
