package com.br.gerenciadorportfolio.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.br.gerenciadorportfolio.entity.Pessoa;
import com.br.gerenciadorportfolio.entity.Projeto;
import com.br.gerenciadorportfolio.entity.enums.ClassificacaoRisco;
import com.br.gerenciadorportfolio.entity.enums.StatusProjeto;
import com.br.gerenciadorportfolio.service.MembroService;
import com.br.gerenciadorportfolio.service.PessoaService;
import com.br.gerenciadorportfolio.service.ProjetoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ContextConfiguration(classes = {ProjetoController.class})
@ExtendWith(SpringExtension.class)
class ProjetoControllerDiffblueTest {
    @MockBean
    private MembroService membroService;

    @MockBean
    private PessoaService pessoaService;

    @Autowired
    private ProjetoController projetoController;

    @MockBean
    private ProjetoService projetoService;

    /**
     * Method under test: {@link ProjetoController#atualizarProjeto(Long, Projeto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAtualizarProjeto() throws Exception {

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
        String content = (new ObjectMapper()).writeValueAsString(projeto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/projetos/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(projetoController).build().perform(requestBuilder);
    }

    /**
     * Method under test: {@link ProjetoController#buscarProjeto(Long)}
     */
    @Test
    void testBuscarProjeto() throws Exception {
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
        when(projetoService.buscarProjeto(Mockito.<Long>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/projetos/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(projetoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"nome\":\"Nome\",\"dataInicio\":[1970,1,1],\"dataPrevisaoFim\":[1970,1,1],\"dataFim\":[1970,1,1],"
                                        + "\"descricao\":\"Descricao\",\"orcamento\":2.3,\"gerente\":{\"idPessoa\":1,\"nome\":\"Nome\",\"dataNascimento\":[1970"
                                        + ",1,1],\"cpf\":\"Cpf\",\"funcionario\":true,\"gerente\":true},\"status\":\"EM_ANALISE\",\"classificacaoRisco\":"
                                        + "\"BAIXO_RISCO\"}"));
    }

    /**
     * Method under test: {@link ProjetoController#buscarProjeto(Long)}
     */
    @Test
    void testBuscarProjeto2() throws Exception {
        // Arrange
        Optional<Projeto> emptyResult = Optional.empty();
        when(projetoService.buscarProjeto(Mockito.<Long>any())).thenReturn(emptyResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/projetos/{id}", 1L);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(projetoController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link ProjetoController#excluirProjeto(Long)}
     */
    @Test
    void testExcluirProjeto() throws Exception {
        // Arrange
        doNothing().when(projetoService).excluirProjeto(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/projetos/{id}", 1L);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(projetoController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test:
     * {@link ProjetoController#criarProjeto(Projeto, Model, RedirectAttributes)}
     */
    @Test
    void testCriarProjeto() throws Exception {
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
        when(projetoService.salvarProjeto(Mockito.<Projeto>any())).thenReturn(projeto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/projetos/salvar");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(projetoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/projetos/novo"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/projetos/novo"));
    }

    /**
     * Method under test:
     * {@link ProjetoController#criarProjeto(Projeto, Model, RedirectAttributes)}
     */
    @Test
    void testCriarProjeto2() throws Exception {
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
        when(projetoService.salvarProjeto(Mockito.<Projeto>any())).thenReturn(projeto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/projetos/salvar")
                .param("dataInicio", "Data Inicio");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(projetoController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link ProjetoController#listar(Model)}
     */
    @Test
    void testListar() throws Exception {
        // Arrange
        when(projetoService.listarProjetos()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/projetos/listar");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(projetoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("projetos"))
                .andExpect(MockMvcResultMatchers.view().name("listar-projetos"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("listar-projetos"));
    }

    /**
     * Method under test: {@link ProjetoController#novoProjeto(Model)}
     */
    @Test
    void testNovoProjeto() throws Exception {
        // Arrange
        when(membroService.listarMembros()).thenReturn(new ArrayList<>());
        when(pessoaService.listarPessoas()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/projetos/novo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(projetoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("membros", "pessoas"))
                .andExpect(MockMvcResultMatchers.view().name("cadastrar-projeto"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("cadastrar-projeto"));
    }
}
