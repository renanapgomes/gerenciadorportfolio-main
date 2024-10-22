package com.br.gerenciadorportfolio.controller;

import com.br.gerenciadorportfolio.entity.Pessoa;
import com.br.gerenciadorportfolio.repository.PessoaRepository;
import com.br.gerenciadorportfolio.service.PessoaService;
import com.br.gerenciadorportfolio.util.MockProvider;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@WebMvcTest(PessoaControllerTest.class)
public class PessoaControllerTest {

    @InjectMocks
    private PessoaController pessoaController;

    @Mock
    private PessoaService pessoaService;

//    @InjectMocks
    private PessoaRepository pessoaRepository;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Model model;

    @Test
    void should_returnTrue_when_postPessoa_given_anInvalidPayload(){
        Pessoa pessoa = MockProvider.generateMockPessoa();

        when(pessoaService.salvarPessoa(any(Pessoa.class)))
                .thenReturn(any(Pessoa.class));

        String pessoaCriado = pessoaController.criarPessoa(pessoa, model, redirectAttributes);

        assertEquals(Boolean.TRUE, pessoaCriado.equals("redirect:/pessoas/novo"));
        assertNotNull(pessoaCriado);
        verify(pessoaService, times(1)).salvarPessoa(pessoa);
    }

    @Test
    void should_returnTruePessoaService_when_find_given_aParameterValid(){
        Long id = MockProvider.generateMockPessoa().getIdPessoa();
        Optional<Pessoa> pessoa = Optional.ofNullable(MockProvider.generateMockPessoa());

        when(pessoaService.buscarPessoa(any())).thenReturn(pessoa);

        String pessoaBD = pessoaController.buscarPessoa(id, model);
        assertNotNull(pessoaBD);
        verify(pessoaService, times(1)).buscarPessoa(id);
    }

    @Test
    void should_returnFalsePessoaService_when_find_given_aParameterInValid(){
        Optional<Pessoa> pessoa =
                Optional.ofNullable(MockProvider.generateMockPessoa());

        when(pessoaService.buscarPessoa(any())).thenReturn(pessoa);

        String pessoaBD = pessoaController.buscarPessoa(0L, model);
        assertEquals(Boolean.FALSE, pessoaBD.contains("false"));
        assertNotNull(pessoaBD);
        verify(pessoaService, times(1)).buscarPessoa(0L);
    }


}
