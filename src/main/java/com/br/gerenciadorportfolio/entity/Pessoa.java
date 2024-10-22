package com.br.gerenciadorportfolio.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pessoa")
@Data
public class Pessoa {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PESSOA_ID_SEQ")
    @SequenceGenerator(name = "PESSOA_ID_SEQ", sequenceName = "PESSOA_ID_SEQ", allocationSize = 1)
    private Long idPessoa;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(name = "datanascimento")
    private LocalDate dataNascimento;

    @Column(name = "cpf", length = 14)
    private String cpf;

    private Boolean funcionario;

    private Boolean gerente;
}