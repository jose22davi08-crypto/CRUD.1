package com.Thymeleaf.demo.controllers;

import com.Thymeleaf.demo.models.Restaurante;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/restaurantes")
public class RestaurantesController {

    // LISTAR RESTAURANTES
    @GetMapping("/index")
    public String listar(ModelMap modelo) {

        try {
            Base.open("org.sqlite.JDBC",
                    "jdbc:sqlite:meu_app.db", "", "");

            java.util.List<java.util.Map<String, Object>> restaurantes =
                    new java.util.ArrayList<>();

            for (Model restaurante : Restaurante.findAll()) {

                java.util.Map<String, Object> dados =
                        new java.util.HashMap<>();

                dados.put("id", restaurante.get("id"));
                dados.put("nome", restaurante.get("nome"));
                dados.put("tipo_comida", restaurante.get("tipo_comida"));
                dados.put("endereco", restaurante.get("endereco"));

                restaurantes.add(dados);
            }

            modelo.addAttribute("restaurantes", restaurantes);

        } finally {
            Base.close();
        }

        return "/restaurantes/index";
    }

    // ABRIR FORMULÁRIO DE EDIÇÃO
    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            ModelMap modelo) {

        try {
            Base.open("org.sqlite.JDBC",
                    "jdbc:sqlite:meu_app.db", "", "");

            Model restaurante = Restaurante.findById(id);

            if (restaurante == null) {
                return "redirect:/restaurantes/index";
            }

            modelo.addAttribute("restaurante", restaurante);

        } finally {
            Base.close();
        }

        return "restaurantes/formulario";
    }

    // ATUALIZAR RESTAURANTE
    @PostMapping("/atualizar/{id}")
    public String atualizar(
            @PathVariable Long id,
            @RequestParam String nome,
            @RequestParam String tipo_comida,
            @RequestParam String endereco) {

        try {
            Base.open("org.sqlite.JDBC",
                    "jdbc:sqlite:meu_app.db", "", "");

            Model restaurante = Restaurante.findById(id);

            if (restaurante != null) {

                restaurante.set("nome", nome);
                restaurante.set("tipo_comida", tipo_comida);
                restaurante.set("endereco", endereco);

                restaurante.saveIt();
            }

        } finally {
            Base.close();
        }

        return "redirect:/restaurantes/index";
    }

    // EXCLUIR RESTAURANTE
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {

        try {
            Base.open("org.sqlite.JDBC",
                    "jdbc:sqlite:meu_app.db", "", "");

            Model restaurante = Restaurante.findById(id);

            if (restaurante != null) {
                restaurante.delete();
            }

        } finally {
            Base.close();
        }

        return "redirect:/restaurantes/index";
    }

    // ABRIR FORMULÁRIO DE CADASTRO
    @GetMapping("/novo")
    public String novo() {
        return "restaurantes/formulario";
    }

    // SALVAR RESTAURANTE
    @PostMapping("/salvar")
    public String salvar(
            @RequestParam String nome,
            @RequestParam String tipo_comida,
            @RequestParam String endereco) {

        try {
            Base.open("org.sqlite.JDBC",
                    "jdbc:sqlite:meu_app.db", "", "");

            Restaurante restaurante = new Restaurante();

            restaurante.set("nome", nome);
            restaurante.set("tipo_comida", tipo_comida);
            restaurante.set("endereco", endereco);

            restaurante.saveIt();

        } finally {
            Base.close();
        }

        return "redirect:restaurantes/index";
    }
}