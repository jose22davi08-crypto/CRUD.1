
package com.Thymeleaf.demo.controllers;

import com.Thymeleaf.demo.models.Pessoa;
import org.javalite.activejdbc.Base;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.javalite.activejdbc.Model;

@Controller
@RequestMapping("/pessoas")
public class PessoasController {


    // ABRIR FORMULÁRIO DE CADASTRO


    @GetMapping("/index")
    public String listar(ModelMap modelo) {

        try {
            Base.open("org.sqlite.JDBC",
                    "jdbc:sqlite:meu_app.db", "", "");

            java.util.List<java.util.Map<String, Object>> pessoas =
                    new java.util.ArrayList<>();

            for (Model pessoa : Pessoa.findAll()) {

                java.util.Map<String, Object> dados =
                        new java.util.HashMap<>();

                dados.put("id", pessoa.get("id"));
                dados.put("nome", pessoa.get("nome"));
                dados.put("sobrenome", pessoa.get("sobrenome"));
                dados.put("email", pessoa.get("email"));

                pessoas.add(dados);
            }

            modelo.addAttribute("pessoas", pessoas);

        } finally {
            Base.close();
        }

        return "users/index";
    }



    // ABRIR FORMULÁRIO DE EDIÇÃO
    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            ModelMap modelo) {

        try {
            Base.open("org.sqlite.JDBC",
                    "jdbc:sqlite:meu_app.db", "", "");

            Model pessoa = Pessoa.findById(id);

            if (pessoa == null) {
                return "redirect:/pessoas/index";
            }

            modelo.addAttribute("pessoa", pessoa);

        } finally {
            Base.close();
        }

        return "users/formulario";
    }

    // ATUALIZAR PESSOA
    @PostMapping("/atualizar/{id}")
    public String atualizar(
            @PathVariable Long id,
            @RequestParam String nome,
            @RequestParam String sobrenome,
            @RequestParam String email) {

        try {
            Base.open("org.sqlite.JDBC",
                    "jdbc:sqlite:meu_app.db", "", "");

            Model pessoa = Pessoa.findById(id);

            if (pessoa != null) {

                pessoa.set("nome", nome);
                pessoa.set("sobrenome", sobrenome);
                pessoa.set("email", email);

                pessoa.saveIt();
            }

        } finally {
            Base.close();
        }

        return "redirect:/pessoas/index";
    }

    // EXCLUIR PESSOA
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {

        try {
            Base.open("org.sqlite.JDBC",
                    "jdbc:sqlite:meu_app.db", "", "");

            Model pessoa = Pessoa.findById(id);

            if (pessoa != null) {
                pessoa.delete();
            }

        } finally {
            Base.close();
        }

        return "redirect:/pessoas/index";
    }
    // ABRIR FORMULÁRIO DE CADASTRO
    @GetMapping("/novo")
    public String novo() {
        return "users/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(
            @RequestParam String nome,
            @RequestParam String sobrenome,
            @RequestParam String email) {

        try {
            Base.open("org.sqlite.JDBC",
                    "jdbc:sqlite:meu_app.db", "", "");

            Pessoa pessoa = new Pessoa();

            pessoa.set("nome", nome);
            pessoa.set("sobrenome", sobrenome);
            pessoa.set("email", email);

            pessoa.saveIt();

        } finally {
            Base.close();
        }

        return "redirect:/pessoas/index";
    }

}