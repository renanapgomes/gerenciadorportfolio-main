package com.br.gerenciadorportfolio.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.br.gerenciadorportfolio.entity.Pessoa;
import com.br.gerenciadorportfolio.entity.Projeto;
import com.br.gerenciadorportfolio.entity.enums.ClassificacaoRisco;
import com.br.gerenciadorportfolio.entity.enums.StatusProjeto;
import com.br.gerenciadorportfolio.repository.ProjetoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProjetoService.class})
@ExtendWith(SpringExtension.class)
class ProjetoServiceDiffblueTest {
    @MockBean
    private ProjetoRepository projetoRepository;

    @Autowired
    private ProjetoService projetoService;

    /**
     * Method under test: {@link ProjetoService#listarProjetos()}
     */
    @Test
    void testListarProjetos() {
        // Arrange
        ArrayList<Projeto> projetoList = new ArrayList<>();
        when(projetoRepository.findAll()).thenReturn(projetoList);

        // Act
        List<Projeto> actualListarProjetosResult = projetoService.listarProjetos();

        // Assert
        verify(projetoRepository).findAll();
        assertTrue(actualListarProjetosResult.isEmpty());
        assertSame(projetoList, actualListarProjetosResult);
    }

    /**
     * Method under test: {@link ProjetoService#listarProjetos()}
     */
    @Test
    void testListarProjetos2() {
        // Arrange
        when(projetoRepository.findAll()).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> projetoService.listarProjetos());
        verify(projetoRepository).findAll();
    }

    /**
     * Method under test: {@link ProjetoService#buscarProjeto(Long)}
     */
    @Test
    void testBuscarProjeto() {
        // Arrange
        Pessoa gerente = new Pessoa();
        gerente.setCpf("Cpf");
        gerente.setDataNascimento(LocalDate.of(1970, 1, 1));
        gerente.setFuncionario(true);
        gerente.setGerente(true);
        gerente.setIdPessoa(1L);
        gerente.setNome("Nome");

        Projeto projeto = new Projeto();
        projeto.setClassificacaoRisco(ClassificacaoRisco.BAIXO_RISCO);
        projeto.setDataFim(LocalDate.of(1970, 1, 1));
        projeto.setDataInicio(LocalDate.of(1970, 1, 1));
        projeto.setDataPrevisaoFim(LocalDate.of(1970, 1, 1));
        projeto.setDescricao("Descricao");
        projeto.setGerente(gerente);
        projeto.setId(1L);
        projeto.setNome("Nome");
        projeto.setOrcamento(new BigDecimal("2.3"));
        projeto.setStatus(StatusProjeto.EM_ANALISE);
        Optional<Projeto> ofResult = Optional.of(projeto);
        when(projetoRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Optional<Projeto> actualBuscarProjetoResult = projetoService.buscarProjeto(1L);

        // Assert
        verify(projetoRepository).findById(eq(1L));
        assertSame(ofResult, actualBuscarProjetoResult);
    }

    /**
     * Method under test: {@link ProjetoService#buscarProjeto(Long)}
     */
    @Test
    void testBuscarProjeto2() {
        // Arrange
        when(projetoRepository.findById(Mockito.<Long>any())).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> projetoService.buscarProjeto(1L));
        verify(projetoRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link ProjetoService#salvarProjeto(Projeto)}
     */
    @Test
    void testSalvarProjeto() {
        // Arrange
        Pessoa gerente = new Pessoa();
        gerente.setCpf("Cpf");
        gerente.setDataNascimento(LocalDate.of(1970, 1, 1));
        gerente.setFuncionario(true);
        gerente.setGerente(true);
        gerente.setIdPessoa(1L);
        gerente.setNome("Nome");

        Projeto projeto = new Projeto();
        projeto.setClassificacaoRisco(ClassificacaoRisco.BAIXO_RISCO);
        projeto.setDataFim(LocalDate.of(1970, 1, 1));
        projeto.setDataInicio(LocalDate.of(1970, 1, 1));
        projeto.setDataPrevisaoFim(LocalDate.of(1970, 1, 1));
        projeto.setDescricao("Descricao");
        projeto.setGerente(gerente);
        projeto.setId(1L);
        projeto.setNome("Nome");
        projeto.setOrcamento(new BigDecimal("2.3"));
        projeto.setStatus(StatusProjeto.EM_ANALISE);
        when(projetoRepository.save(Mockito.<Projeto>any())).thenReturn(projeto);

        Pessoa gerente2 = new Pessoa();
        gerente2.setCpf("Cpf");
        gerente2.setDataNascimento(LocalDate.of(1970, 1, 1));
        gerente2.setFuncionario(true);
        gerente2.setGerente(true);
        gerente2.setIdPessoa(1L);
        gerente2.setNome("Nome");

        Projeto projeto2 = new Projeto();
        projeto2.setClassificacaoRisco(ClassificacaoRisco.BAIXO_RISCO);
        projeto2.setDataFim(LocalDate.of(1970, 1, 1));
        projeto2.setDataInicio(LocalDate.of(1970, 1, 1));
        projeto2.setDataPrevisaoFim(LocalDate.of(1970, 1, 1));
        projeto2.setDescricao("Descricao");
        projeto2.setGerente(gerente2);
        projeto2.setId(1L);
        projeto2.setNome("Nome");
        projeto2.setOrcamento(new BigDecimal("2.3"));
        projeto2.setStatus(StatusProjeto.EM_ANALISE);

        // Act
        Projeto actualSalvarProjetoResult = projetoService.salvarProjeto(projeto2);

        // Assert
        verify(projetoRepository).save(isA(Projeto.class));
        assertSame(projeto, actualSalvarProjetoResult);
    }

    /**
     * Method under test: {@link ProjetoService#salvarProjeto(Projeto)}
     */
    @Test
    void testSalvarProjeto2() {
        // Arrange
        when(projetoRepository.save(Mockito.<Projeto>any())).thenThrow(new RuntimeException("foo"));

        Pessoa gerente = new Pessoa();
        gerente.setCpf("Cpf");
        gerente.setDataNascimento(LocalDate.of(1970, 1, 1));
        gerente.setFuncionario(true);
        gerente.setGerente(true);
        gerente.setIdPessoa(1L);
        gerente.setNome("Nome");

        Projeto projeto = new Projeto();
        projeto.setClassificacaoRisco(ClassificacaoRisco.BAIXO_RISCO);
        projeto.setDataFim(LocalDate.of(1970, 1, 1));
        projeto.setDataInicio(LocalDate.of(1970, 1, 1));
        projeto.setDataPrevisaoFim(LocalDate.of(1970, 1, 1));
        projeto.setDescricao("Descricao");
        projeto.setGerente(gerente);
        projeto.setId(1L);
        projeto.setNome("Nome");
        projeto.setOrcamento(new BigDecimal("2.3"));
        projeto.setStatus(StatusProjeto.EM_ANALISE);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> projetoService.salvarProjeto(projeto));
        verify(projetoRepository).save(isA(Projeto.class));
    }

    /**
     * Method under test: {@link ProjetoService#excluirProjeto(Long)}
     */
    @Test
    void testExcluirProjeto() {
        // Arrange
        Pessoa gerente = new Pessoa();
        gerente.setCpf("Cpf");
        gerente.setDataNascimento(LocalDate.of(1970, 1, 1));
        gerente.setFuncionario(true);
        gerente.setGerente(true);
        gerente.setIdPessoa(1L);
        gerente.setNome("Nome");

        Projeto projeto = new Projeto();
        projeto.setClassificacaoRisco(ClassificacaoRisco.BAIXO_RISCO);
        projeto.setDataFim(LocalDate.of(1970, 1, 1));
        projeto.setDataInicio(LocalDate.of(1970, 1, 1));
        projeto.setDataPrevisaoFim(LocalDate.of(1970, 1, 1));
        projeto.setDescricao("Descricao");
        projeto.setGerente(gerente);
        projeto.setId(1L);
        projeto.setNome("Nome");
        projeto.setOrcamento(new BigDecimal("2.3"));
        projeto.setStatus(StatusProjeto.EM_ANALISE);
        Optional<Projeto> ofResult = Optional.of(projeto);
        doNothing().when(projetoRepository).deleteById(Mockito.<Long>any());
        when(projetoRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        projetoService.excluirProjeto(1L);

        // Assert that nothing has changed
        verify(projetoRepository).deleteById(eq(1L));
        verify(projetoRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link ProjetoService#excluirProjeto(Long)}
     */
    @Test
    void testExcluirProjeto2() {
        // Arrange
        Pessoa gerente = new Pessoa();
        gerente.setCpf("Cpf");
        gerente.setDataNascimento(LocalDate.of(1970, 1, 1));
        gerente.setFuncionario(true);
        gerente.setGerente(true);
        gerente.setIdPessoa(1L);
        gerente.setNome("Nome");

        Projeto projeto = new Projeto();
        projeto.setClassificacaoRisco(ClassificacaoRisco.BAIXO_RISCO);
        projeto.setDataFim(LocalDate.of(1970, 1, 1));
        projeto.setDataInicio(LocalDate.of(1970, 1, 1));
        projeto.setDataPrevisaoFim(LocalDate.of(1970, 1, 1));
        projeto.setDescricao("Descricao");
        projeto.setGerente(gerente);
        projeto.setId(1L);
        projeto.setNome("Nome");
        projeto.setOrcamento(new BigDecimal("2.3"));
        projeto.setStatus(StatusProjeto.EM_ANALISE);
        Optional<Projeto> ofResult = Optional.of(projeto);
        doThrow(new RuntimeException("foo")).when(projetoRepository).deleteById(Mockito.<Long>any());
        when(projetoRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> projetoService.excluirProjeto(1L));
        verify(projetoRepository).deleteById(eq(1L));
        verify(projetoRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link ProjetoService#excluirProjeto(Long)}
     */
    @Test
    void testExcluirProjeto3() {
        // Arrange
        Pessoa gerente = new Pessoa();
        gerente.setCpf("Cpf");
        gerente.setDataNascimento(LocalDate.of(1970, 1, 1));
        gerente.setFuncionario(true);
        gerente.setGerente(true);
        gerente.setIdPessoa(1L);
        gerente.setNome("Nome");

        Projeto projeto = new Projeto();
        projeto.setClassificacaoRisco(ClassificacaoRisco.BAIXO_RISCO);
        projeto.setDataFim(LocalDate.of(1970, 1, 1));
        projeto.setDataInicio(LocalDate.of(1970, 1, 1));
        projeto.setDataPrevisaoFim(LocalDate.of(1970, 1, 1));
        projeto.setDescricao("Descricao");
        projeto.setGerente(gerente);
        projeto.setId(1L);
        projeto.setNome("Nome");
        projeto.setOrcamento(new BigDecimal("2.3"));
        projeto.setStatus(StatusProjeto.INICIADO);
        Optional<Projeto> ofResult = Optional.of(projeto);
        when(projetoRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> projetoService.excluirProjeto(1L));
        verify(projetoRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link ProjetoService#excluirProjeto(Long)}
     */
    @Test
    void testExcluirProjeto4() {
        // Arrange
        Optional<Projeto> emptyResult = Optional.empty();
        when(projetoRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> projetoService.excluirProjeto(1L));
        verify(projetoRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link ProjetoService#excluirProjeto(Long)}
     */
    @Test
    void testExcluirProjeto5() {
        // Arrange
        Pessoa gerente = new Pessoa();
        gerente.setCpf("Cpf");
        gerente.setDataNascimento(LocalDate.of(1970, 1, 1));
        gerente.setFuncionario(true);
        gerente.setGerente(true);
        gerente.setIdPessoa(1L);
        gerente.setNome("Nome");

        Projeto projeto = new Projeto();
        projeto.setClassificacaoRisco(ClassificacaoRisco.BAIXO_RISCO);
        projeto.setDataFim(LocalDate.of(1970, 1, 1));
        projeto.setDataInicio(LocalDate.of(1970, 1, 1));
        projeto.setDataPrevisaoFim(LocalDate.of(1970, 1, 1));
        projeto.setDescricao("Descricao");
        projeto.setGerente(gerente);
        projeto.setId(1L);
        projeto.setNome("Nome");
        projeto.setOrcamento(new BigDecimal("2.3"));
        projeto.setStatus(StatusProjeto.EM_ANDAMENTO);
        Optional<Projeto> ofResult = Optional.of(projeto);
        when(projetoRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> projetoService.excluirProjeto(1L));
        verify(projetoRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link ProjetoService#excluirProjeto(Long)}
     */
    @Test
    void testExcluirProjeto6() {
        // Arrange
        Pessoa gerente = new Pessoa();
        gerente.setCpf("Cpf");
        gerente.setDataNascimento(LocalDate.of(1970, 1, 1));
        gerente.setFuncionario(true);
        gerente.setGerente(true);
        gerente.setIdPessoa(1L);
        gerente.setNome("Nome");

        Projeto projeto = new Projeto();
        projeto.setClassificacaoRisco(ClassificacaoRisco.BAIXO_RISCO);
        projeto.setDataFim(LocalDate.of(1970, 1, 1));
        projeto.setDataInicio(LocalDate.of(1970, 1, 1));
        projeto.setDataPrevisaoFim(LocalDate.of(1970, 1, 1));
        projeto.setDescricao("Descricao");
        projeto.setGerente(gerente);
        projeto.setId(1L);
        projeto.setNome("Nome");
        projeto.setOrcamento(new BigDecimal("2.3"));
        projeto.setStatus(StatusProjeto.ENCERRADO);
        Optional<Projeto> ofResult = Optional.of(projeto);
        when(projetoRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> projetoService.excluirProjeto(1L));
        verify(projetoRepository).findById(eq(1L));
    }
}
