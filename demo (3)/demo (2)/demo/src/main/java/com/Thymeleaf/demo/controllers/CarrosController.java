package com.Thymeleaf.demo.controllers;

import com.Thymeleaf.demo.models.Carro;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carros")
public class CarrosController {

    // LISTAR CARROS
    @GetMapping("/index")
    public String listar(ModelMap modelo) {

        try {
            Base.open("org.sqlite.JDBC",
                    "jdbc:sqlite:meu_app.db", "", "");

            java.util.List<java.util.Map<String, Object>> carros =
                    new java.util.ArrayList<>();

            for (Model carro : Carro.findAll()) {

                java.util.Map<String, Object> dados =
                        new java.util.HashMap<>();

                dados.put("id", carro.get("id"));
                dados.put("modelo", carro.get("modelo"));
                dados.put("marca", carro.get("marca"));
                dados.put("ano", carro.get("ano"));

                carros.add(dados);
            }

            modelo.addAttribute("carros", carros);

        } finally {
            Base.close();
        }

        return "carros/index";
    }

    // ABRIR FORMULÁRIO DE EDIÇÃO
    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            ModelMap modelo) {

        try {
            Base.open("org.sqlite.JDBC",
                    "jdbc:sqlite:meu_app.db", "", "");

            Model carro = Carro.findById(id);

            if (carro == null) {
                return "redirect:/carros/index";
            }

            modelo.addAttribute("carro", carro);

        } finally {
            Base.close();
        }

        return "carros/formulario";
    }

    // ATUALIZAR CARRO
    @PostMapping("/atualizar/{id}")
    public String atualizar(
            @PathVariable Long id,
            @RequestParam String modelo,
            @RequestParam String marca,
            @RequestParam String ano) {

        try {
            Base.open("org.sqlite.JDBC",
                    "jdbc:sqlite:meu_app.db", "", "");

            Model carro = Carro.findById(id);

            if (carro != null) {

                carro.set("modelo", modelo);
                carro.set("marca", marca);
                carro.set("ano", ano);

                carro.saveIt();
            }

        } finally {
            Base.close();
        }

        return "redirect:/carros/index";
    }

    // EXCLUIR CARRO
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {

        try {
            Base.open("org.sqlite.JDBC",
                    "jdbc:sqlite:meu_app.db", "", "");

            Model carro = Carro.findById(id);

            if (carro != null) {
                carro.delete();
            }

        } finally {
            Base.close();
        }

        return "redirect:/carros/index";
    }

    // ABRIR FORMULÁRIO DE CADASTRO
    @GetMapping("/novo")
    public String novo() {
        return "carros/formulario";
    }

    // SALVAR CARRO
    @PostMapping("/salvar")
    public String salvar(
            @RequestParam String modelo,
            @RequestParam String marca,
            @RequestParam String ano) {

        try {
            Base.open("org.sqlite.JDBC",
                    "jdbc:sqlite:meu_app.db", "", "");

            Carro carro = new Carro();

            carro.set("modelo", modelo);
            carro.set("marca", marca);
            carro.set("ano", ano);

            carro.saveIt();

        } finally {
            Base.close();
        }

        return "redirect:/carros/index";
    }
}
