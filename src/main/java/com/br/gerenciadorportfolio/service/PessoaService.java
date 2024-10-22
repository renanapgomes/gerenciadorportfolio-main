package com.br.gerenciadorportfolio.service;

import com.br.gerenciadorportfolio.entity.Pessoa;
import com.br.gerenciadorportfolio.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional(readOnly = true)
    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Pessoa> buscarPessoa(Long id) {
        return pessoaRepository.findById(id);
    }

    @Transactional
    public Pessoa salvarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void excluirPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }
}
