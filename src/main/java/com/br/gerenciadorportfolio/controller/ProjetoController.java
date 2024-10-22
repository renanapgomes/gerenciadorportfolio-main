package com.br.gerenciadorportfolio.controller;


import com.br.gerenciadorportfolio.entity.Pessoa;
import com.br.gerenciadorportfolio.entity.Membro;
import com.br.gerenciadorportfolio.entity.Projeto;
import com.br.gerenciadorportfolio.service.PessoaService;
import com.br.gerenciadorportfolio.service.MembroService;
import com.br.gerenciadorportfolio.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/projetos")
public class ProjetoController {

    private List<Pessoa> pessoas;

    private List<Membro> membros;

    private String resultado;
    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private MembroService membroService;

    @Autowired
    private PessoaService pessoaService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        });
    }

    @GetMapping("/novo")
    public String novoProjeto(Model model){
        this.pessoas = this.pessoaService.listarPessoas();
        model.addAttribute("pessoas", this.pessoas);
        this.membros = this.membroService.listarMembros();
        model.addAttribute("membros", this.membros);
        return "cadastrar-projeto";
    }

    @PostMapping("/salvar")
    public String criarProjeto(Projeto projeto,Model model,
                               RedirectAttributes attributes) {
        Projeto novoProjeto = projetoService.salvarProjeto(projeto);
        if(Objects.nonNull(novoProjeto)){
            this.resultado = "sucesso";
            attributes.addFlashAttribute("mensagem", "Projeto salvo com sucesso!");
            return "redirect:/projetos/novo";
        }
        attributes.addFlashAttribute("mensagem-error", "Erro ao cadastrar projeto");
        return "redirect:/projetos/novo";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Projeto> lista = projetoService.listarProjetos();
        model.addAttribute("projetos", lista);
        return "listar-projetos";
    }

    @GetMapping(value = "/{id}", produces = { MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Projeto> buscarProjeto(@PathVariable Long id) {
        Optional<Projeto> projeto = projetoService.buscarProjeto(id);
        if (projeto.isPresent()) {
            return ResponseEntity.ok(projeto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizarProjeto(@PathVariable Long id, @RequestBody Projeto projeto) {
        Optional<Projeto> projetoExistente = projetoService.buscarProjeto(id);
        if (projetoExistente.isPresent()) {
            Projeto projetoAtualizado = projetoService.salvarProjeto(projeto);
            return ResponseEntity.ok(projetoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping("/remover/{id}")
    public String excluirProjeto(@PathVariable Long id, RedirectAttributes attributes) {
        projetoService.excluirProjeto(id);
        List<Projeto> lista = this.projetoService.listarProjetos();
        attributes.addFlashAttribute("projetos", lista);
        return "redirect:/projetos/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarProjeto(@PathVariable Long id, Model model) {
        Optional<Projeto> projetoOptional = projetoService.buscarProjeto(id);

        if (projetoOptional.isPresent()) {
            Projeto projeto = projetoOptional.get();

            // Adiciona o projeto ao modelo
            model.addAttribute("projeto", projeto);

            List<Pessoa> pessoas = pessoaService.listarPessoas();
            model.addAttribute("pessoas", pessoas);

            return "editar-projeto";
        } else {
            return "redirect:/projetos/listar";
        }
    }

}

