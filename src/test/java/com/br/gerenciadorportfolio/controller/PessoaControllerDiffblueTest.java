package com.br.gerenciadorportfolio.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.br.gerenciadorportfolio.entity.Pessoa;
import com.br.gerenciadorportfolio.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ContextConfiguration(classes = {PessoaController.class})
@ExtendWith(SpringExtension.class)
class PessoaControllerDiffblueTest {
    @Autowired
    private PessoaController pessoaController;

    @MockBean
    private PessoaService pessoaService;

    /**
     * Method under test: {@link PessoaController#buscarPessoa(Long, Model)}
     */
    @Test
    void testBuscarPessoa() throws Exception {
        // Arrange
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("Cpf");
        pessoa.setDataNascimento(LocalDate.of(1970, 1, 1));
        pessoa.setFuncionario(true);
        pessoa.setGerente(true);
        pessoa.setIdPessoa(1L);
        pessoa.setNome("Nome");
        Optional<Pessoa> ofResult = Optional.of(pessoa);
        when(pessoaService.buscarPessoa(Mockito.<Long>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pessoas/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(pessoaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("pessoas"))
                .andExpect(MockMvcResultMatchers.view().name("listar-pessoas"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("listar-pessoas"));
    }

    /**
     * Method under test: {@link PessoaController#buscarPessoa(Long, Model)}
     */
    @Test
    void testBuscarPessoa2() throws Exception {
        // Arrange
        Optional<Pessoa> emptyResult = Optional.empty();
        when(pessoaService.buscarPessoa(Mockito.<Long>any())).thenReturn(emptyResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pessoas/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(pessoaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("pessoas"))
                .andExpect(MockMvcResultMatchers.view().name("listar-pessoas"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("listar-pessoas"));
    }

    /**
     * Method under test: {@link PessoaController#atualizarPessoa(Long, Pessoa)}
     */
    @Test
    void testAtualizarPessoa() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("Cpf");
        pessoa.setDataNascimento(LocalDate.of(1970, 1, 1));
        pessoa.setFuncionario(true);
        pessoa.setGerente(true);
        pessoa.setIdPessoa(1L);
        pessoa.setNome("Nome");
        Optional<Pessoa> ofResult = Optional.of(pessoa);

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setCpf("Cpf");
        pessoa2.setDataNascimento(LocalDate.of(1970, 1, 1));
        pessoa2.setFuncionario(true);
        pessoa2.setGerente(true);
        pessoa2.setIdPessoa(1L);
        pessoa2.setNome("Nome");
        PessoaService pessoaService = mock(PessoaService.class);
        when(pessoaService.salvarPessoa(Mockito.<Pessoa>any())).thenReturn(pessoa2);
        when(pessoaService.buscarPessoa(Mockito.<Long>any())).thenReturn(ofResult);
        PessoaController pessoaController = new PessoaController(pessoaService);

        Pessoa pessoa3 = new Pessoa();
        pessoa3.setCpf("Cpf");
        pessoa3.setDataNascimento(LocalDate.of(1970, 1, 1));
        pessoa3.setFuncionario(true);
        pessoa3.setGerente(true);
        pessoa3.setIdPessoa(1L);
        pessoa3.setNome("Nome");

        // Act
        ResponseEntity<Pessoa> actualAtualizarPessoaResult = pessoaController.atualizarPessoa(1L, pessoa3);

        // Assert
        verify(pessoaService).buscarPessoa(eq(1L));
        verify(pessoaService).salvarPessoa(isA(Pessoa.class));
        assertEquals(200, actualAtualizarPessoaResult.getStatusCodeValue());
        assertEquals(HttpStatus.OK, actualAtualizarPessoaResult.getStatusCode());
        assertTrue(actualAtualizarPessoaResult.hasBody());
        assertTrue(actualAtualizarPessoaResult.getHeaders().isEmpty());
        assertSame(pessoa2, actualAtualizarPessoaResult.getBody());
    }

    /**
     * Method under test: {@link PessoaController#atualizarPessoa(Long, Pessoa)}
     */
    @Test
    void testAtualizarPessoa2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        PessoaService pessoaService = mock(PessoaService.class);
        Optional<Pessoa> emptyResult = Optional.empty();
        when(pessoaService.buscarPessoa(Mockito.<Long>any())).thenReturn(emptyResult);
        PessoaController pessoaController = new PessoaController(pessoaService);

        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("Cpf");
        pessoa.setDataNascimento(LocalDate.of(1970, 1, 1));
        pessoa.setFuncionario(true);
        pessoa.setGerente(true);
        pessoa.setIdPessoa(1L);
        pessoa.setNome("Nome");

        // Act
        ResponseEntity<Pessoa> actualAtualizarPessoaResult = pessoaController.atualizarPessoa(1L, pessoa);

        // Assert
        verify(pessoaService).buscarPessoa(eq(1L));
        assertNull(actualAtualizarPessoaResult.getBody());
        assertEquals(404, actualAtualizarPessoaResult.getStatusCodeValue());
        assertEquals(HttpStatus.NOT_FOUND, actualAtualizarPessoaResult.getStatusCode());
        assertFalse(actualAtualizarPessoaResult.hasBody());
        assertTrue(actualAtualizarPessoaResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link PessoaController#excluirPessoa(Long, RedirectAttributes)}
     */
    @Test
    void testExcluirPessoa() throws Exception {
        // Arrange
        when(pessoaService.listarPessoas()).thenReturn(new ArrayList<>());
        doNothing().when(pessoaService).excluirPessoa(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/pessoas/remover/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(pessoaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/pessoas/listar"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/pessoas/listar"));
    }

    /**
     * Method under test: {@link PessoaController#atualizarPessoa(Long, Pessoa)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAtualizarPessoa3() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDate` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.br.gerenciadorportfolio.entity.Pessoa["dataNascimento"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1300)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:728)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4568)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3821)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("Cpf");
        pessoa.setDataNascimento(LocalDate.of(1970, 1, 1));
        pessoa.setFuncionario(true);
        pessoa.setGerente(true);
        pessoa.setIdPessoa(1L);
        pessoa.setNome("Nome");
        String content = (new ObjectMapper()).writeValueAsString(pessoa);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/pessoas/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(pessoaController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link PessoaController#criarPessoa(Pessoa, Model, RedirectAttributes)}
     */
    @Test
    void testCriarPessoa() throws Exception {
        // Arrange
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("Cpf");
        pessoa.setDataNascimento(LocalDate.of(1970, 1, 1));
        pessoa.setFuncionario(true);
        pessoa.setGerente(true);
        pessoa.setIdPessoa(1L);
        pessoa.setNome("Nome");
        when(pessoaService.salvarPessoa(Mockito.<Pessoa>any())).thenReturn(pessoa);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/pessoas/salvar");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(pessoaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/pessoas/novo"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/pessoas/novo"));
    }

    /**
     * Method under test:
     * {@link PessoaController#criarPessoa(Pessoa, Model, RedirectAttributes)}
     */
    @Test
    void testCriarPessoa2() throws Exception {
        // Arrange
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("Cpf");
        pessoa.setDataNascimento(LocalDate.of(1970, 1, 1));
        pessoa.setFuncionario(true);
        pessoa.setGerente(true);
        pessoa.setIdPessoa(1L);
        pessoa.setNome("Nome");
        when(pessoaService.salvarPessoa(Mockito.<Pessoa>any())).thenReturn(pessoa);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/pessoas/salvar")
                .param("dataNascimento", "alice.liddell@example.org");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(pessoaController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link PessoaController#listar(Model)}
     */
    @Test
    void testListar() throws Exception {
        // Arrange
        when(pessoaService.listarPessoas()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pessoas/listar");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(pessoaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("pessoas"))
                .andExpect(MockMvcResultMatchers.view().name("listar-pessoas"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("listar-pessoas"));
    }

    /**
     * Method under test: {@link PessoaController#novoPessoa()}
     */
    @Test
    void testNovoPessoa() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pessoas/novo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(pessoaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("cadastrar-pessoa"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("cadastrar-pessoa"));
    }
}
