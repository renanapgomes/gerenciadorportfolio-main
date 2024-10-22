package com.br.gerenciadorportfolio.service;

import com.br.gerenciadorportfolio.entity.Membro;
import com.br.gerenciadorportfolio.entity.Projeto;
import com.br.gerenciadorportfolio.repository.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MembroService {
    @Autowired
    private MembroRepository membroRepository;

    @Transactional(readOnly = true)
    public List<Membro> listarMembros() {
        return membroRepository.findAll();
    }

    @Transactional
    public Membro cadastrarMembro(Membro membro) {
        return membroRepository.save(membro);
    }

    @Transactional
    public Membro atualizarMembro(Long membroId, Membro membro) {
        Optional<Membro> membroExistente = membroRepository.findById(membroId);
        if (membroExistente.isPresent()) {
            membro.setId(membroId);
            return membroRepository.save(membro);
        } else {
            throw new NoSuchElementException("Membro não encontrado");
        }
    }

    @Transactional
    public void excluirMembro(Long membroId) {
        Optional<Membro> membroExistente = membroRepository.findById(membroId);
        if (membroExistente.isPresent()) {
            membroRepository.delete(membroExistente.get());
        } else {
            throw new NoSuchElementException("Membro não encontrado");
        }
    }

    @Transactional(readOnly = true)
    public List<Membro> listarMembrosPorProjeto(Long projetoId) {
        return membroRepository.findByProjetoId(projetoId);
    }

    @Transactional(readOnly = true)
    public List<Membro> listarTodosMembros(){
        return this.membroRepository.findAll();
    }


}
