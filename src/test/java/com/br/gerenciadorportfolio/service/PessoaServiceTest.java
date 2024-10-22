package com.br.gerenciadorportfolio.service;

import com.br.gerenciadorportfolio.entity.Pessoa;
import com.br.gerenciadorportfolio.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @BeforeEach
    void setUp() {
        // Inicializa os mocks e injeta o mock do repositório no serviço
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarPessoas() {
        // Arrange
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("Pessoa 1");

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Pessoa 2");

        List<Pessoa> pessoas = Arrays.asList(pessoa1, pessoa2);
        when(pessoaRepository.findAll()).thenReturn(pessoas);

        // Act
        List<Pessoa> result = pessoaService.listarPessoas();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Pessoa 1", result.get(0).getNome());
        assertEquals("Pessoa 2", result.get(1).getNome());

        // Verifica se o método findAll foi chamado uma vez
        verify(pessoaRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPessoa() {
        // Arrange
        Pessoa pessoa = new Pessoa();
        pessoa.setIdPessoa(1L);
        pessoa.setNome("Pessoa Teste");

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        // Act
        Optional<Pessoa> result = pessoaService.buscarPessoa(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getIdPessoa());
        assertEquals("Pessoa Teste", result.get().getNome());

        // Verifica se o método findById foi chamado uma vez
        verify(pessoaRepository, times(1)).findById(1L);
    }

    @Test
    void testSalvarPessoa() {
        // Arrange
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Nova Pessoa");

        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);

        // Act
        Pessoa result = pessoaService.salvarPessoa(pessoa);

        // Assert
        assertNotNull(result);
        assertEquals("Nova Pessoa", result.getNome());

        // Verifica se o método save foi chamado uma vez
        verify(pessoaRepository, times(1)).save(pessoa);
    }

    @Test
    void testExcluirPessoa() {
        // Act
        pessoaService.excluirPessoa(1L);

        // Assert
        verify(pessoaRepository, times(1)).deleteById(1L);
    }
}
