package com.br.gerenciadorportfolio.request;

public class MembroRequest {

    private String nome;
    private String atribuicao;
    private Long idProjetoSelecionado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAtribuicao() {
        return atribuicao;
    }

    public void setAtribuicao(String atribuicao) {
        this.atribuicao = atribuicao;
    }

    public Long getIdProjetoSelecionado() {
        return idProjetoSelecionado;
    }

    public void setIdProjetoSelecionado(Long idProjetoSelecionado) {
        this.idProjetoSelecionado = idProjetoSelecionado;
    }
}
