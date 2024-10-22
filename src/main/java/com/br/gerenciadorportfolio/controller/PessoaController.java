package com.br.gerenciadorportfolio.controller;

import com.br.gerenciadorportfolio.entity.Pessoa;
import com.br.gerenciadorportfolio.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

    private static final String LISTAR_PESSOAS_VIEW = "listar-pessoas";
    private PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        });
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Pessoa> lista = pessoaService.listarPessoas();
        model.addAttribute("pessoas", lista);
        return LISTAR_PESSOAS_VIEW;
    }


    @GetMapping("/{id}")
    public String buscarPessoa(@PathVariable Long id, Model model) {
        List<Pessoa> lista = new ArrayList<>();
        Optional<Pessoa> pessoa = pessoaService.buscarPessoa(id);
        if (pessoa.isPresent()) {
            lista.add(pessoa.get());
            model.addAttribute("pessoas", lista);
            return LISTAR_PESSOAS_VIEW;
        } else {
            model.addAttribute("pessoas", lista);
            return LISTAR_PESSOAS_VIEW;
        }
    }

    @GetMapping("/novo")
    public String novoPessoa(){
        return "cadastrar-pessoa";
    }

    @PostMapping("/salvar")
    public String criarPessoa(Pessoa pessoa, Model model,
                               RedirectAttributes attributes) {
        Pessoa novoPessoa = pessoaService.salvarPessoa(pessoa);
        if(Objects.nonNull(novoPessoa)){
            attributes.addFlashAttribute("mensagem", "Pessoa salvo com sucesso!");
            return "redirect:/pessoas/novo";
        }
        attributes.addFlashAttribute("mensagem-error", "Erro ao cadastrar pessoa");
        return "redirect:/pessoas/novo";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Optional<Pessoa> pessoaExistente = pessoaService.buscarPessoa(id);
        if (pessoaExistente.isPresent()) {
            Pessoa pessoatoAtualizado = pessoaService.salvarPessoa(pessoa);
            return ResponseEntity.ok(pessoatoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/remover/{id}")
    public String excluirPessoa(@PathVariable Long id, RedirectAttributes attributes) {
        this.pessoaService.excluirPessoa(id);
        List<Pessoa> lista = this.pessoaService.listarPessoas();
        attributes.addFlashAttribute("pessoas", lista);
        return "redirect:/pessoas/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarPessoa(@PathVariable Long id, Model model) {
        Optional<Pessoa> pessoa = pessoaService.buscarPessoa(id);
        if (pessoa.isPresent()) {
            model.addAttribute("pessoa", pessoa.get());
            return "editar-pessoa";
        } else {
            // Redirecionar para uma página de erro ou listar caso a pessoa não seja encontrada
            return "redirect:/pessoas/listar";
        }
    }

}

