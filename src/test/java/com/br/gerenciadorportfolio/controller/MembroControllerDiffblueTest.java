package com.br.gerenciadorportfolio.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.br.gerenciadorportfolio.entity.Membro;
import com.br.gerenciadorportfolio.entity.Pessoa;
import com.br.gerenciadorportfolio.entity.Projeto;
import com.br.gerenciadorportfolio.entity.enums.ClassificacaoRisco;
import com.br.gerenciadorportfolio.entity.enums.StatusProjeto;
import com.br.gerenciadorportfolio.request.MembroRequest;
import com.br.gerenciadorportfolio.service.MembroService;
import com.br.gerenciadorportfolio.service.ProjetoService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@ContextConfiguration(classes = {MembroController.class})
@ExtendWith(SpringExtension.class)
class MembroControllerDiffblueTest {
    @Autowired
    private MembroController membroController;

    @MockBean
    private MembroService membroService;

    @MockBean
    private ProjetoService projetoService;

    /**
     * Method under test: {@link MembroController#listarTodosMembros(Model)}
     */
    @Test
    void testListarTodosMembros() throws Exception {
        // Arrange
        when(membroService.listarTodosMembros()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/membros/listar");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(membroController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("membros"))
                .andExpect(MockMvcResultMatchers.view().name("listar-membros"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("listar-membros"));
    }

    /**
     * Method under test:
     * {@link MembroController#listarMembrosPorProjeto(Long, RedirectAttributes)}
     */
    @Test
    void testListarMembrosPorProjeto() throws Exception {
        // Arrange
        when(membroService.listarMembrosPorProjeto(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/membros/listar/{projetoId}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(membroController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/membros/listar"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/membros/listar"));
    }

    /**
     * Method under test:
     * {@link MembroController#listarMembrosPorProjeto(Long, RedirectAttributes)}
     */
    @Test
    void testListarMembrosPorProjeto2() throws Exception {
        // Arrange
        when(membroService.listarTodosMembros()).thenReturn(new ArrayList<>());
        when(membroService.listarMembrosPorProjeto(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/membros/listar/{projetoId}", "",
                "Uri Variables");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(membroController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("membros"))
                .andExpect(MockMvcResultMatchers.view().name("listar-membros"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("listar-membros"));
    }

    /**
     * Method under test:
     * {@link MembroController#cadastrarMembro(Model, RedirectAttributes, MembroRequest)}
     */
    @Test
    void testCadastrarMembro() throws Exception {
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

        Membro membro = new Membro();
        membro.setAtribuicao("Atribuicao");
        membro.setId(1L);
        membro.setNome("Nome");
        membro.setProjeto(projeto);
        when(membroService.cadastrarMembro(Mockito.<Membro>any())).thenReturn(membro);

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
        Optional<Projeto> ofResult = Optional.of(projeto2);
        when(projetoService.buscarProjeto(Mockito.<Long>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/membros/salvar");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(membroController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/membros/novo"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/membros/novo"));
    }

    /**
     * Method under test:
     * {@link MembroController#atualizarMembro(Long, Long, Membro)}
     */
    @Test
    void testAtualizarMembro() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

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

        Membro membro = new Membro();
        membro.setAtribuicao("Atribuicao");
        membro.setId(1L);
        membro.setNome("Nome");
        membro.setProjeto(projeto);
        MembroService membroService = mock(MembroService.class);
        when(membroService.atualizarMembro(Mockito.<Long>any(), Mockito.<Membro>any())).thenReturn(membro);
        MembroController membroController = new MembroController(membroService, new ProjetoService());

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

        Membro membro2 = new Membro();
        membro2.setAtribuicao("Atribuicao");
        membro2.setId(1L);
        membro2.setNome("Nome");
        membro2.setProjeto(projeto2);

        // Act
        ResponseEntity<Membro> actualAtualizarMembroResult = membroController.atualizarMembro(1L, 1L, membro2);

        // Assert
        verify(membroService).atualizarMembro(eq(1L), isA(Membro.class));
        Projeto projeto3 = membro2.getProjeto();
        assertNull(projeto3.getGerente());
        assertNull(projeto3.getClassificacaoRisco());
        assertNull(projeto3.getStatus());
        assertNull(projeto3.getDescricao());
        assertNull(projeto3.getNome());
        assertNull(projeto3.getOrcamento());
        assertNull(projeto3.getDataFim());
        assertNull(projeto3.getDataInicio());
        assertNull(projeto3.getDataPrevisaoFim());
        assertEquals(200, actualAtualizarMembroResult.getStatusCodeValue());
        assertEquals(HttpStatus.OK, actualAtualizarMembroResult.getStatusCode());
        assertTrue(actualAtualizarMembroResult.hasBody());
        assertTrue(actualAtualizarMembroResult.getHeaders().isEmpty());
        assertSame(membro, actualAtualizarMembroResult.getBody());
    }

    /**
     * Method under test:
     * {@link MembroController#excluirMembro(RedirectAttributes, Long)}
     */
    @Test
    void testExcluirMembro() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        MembroService membroService = mock(MembroService.class);
        when(membroService.listarTodosMembros()).thenReturn(new ArrayList<>());
        doNothing().when(membroService).excluirMembro(Mockito.<Long>any());
        MembroController membroController = new MembroController(membroService, new ProjetoService());

        // Act
        String actualExcluirMembroResult = membroController.excluirMembro(new RedirectAttributesModelMap(), 1L);

        // Assert
        verify(membroService).excluirMembro(eq(1L));
        verify(membroService).listarTodosMembros();
        assertEquals("redirect:/membros/listar", actualExcluirMembroResult);
    }

    /**
     * Method under test: {@link MembroController#novoMembro(Model)}
     */
    @Test
    void testNovoMembro() throws Exception {
        // Arrange
        when(projetoService.listarProjetos()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/membros/novo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(membroController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("membroRequest", "projetos"))
                .andExpect(MockMvcResultMatchers.view().name("cadastrar-membro"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("cadastrar-membro"));
    }
}
