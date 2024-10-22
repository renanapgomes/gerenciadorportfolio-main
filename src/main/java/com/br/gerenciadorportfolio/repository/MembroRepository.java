package com.br.gerenciadorportfolio.repository;

import com.br.gerenciadorportfolio.entity.Membro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembroRepository extends JpaRepository<Membro, Long> {
    List<Membro> findByProjetoId(Long projetoId);
}
