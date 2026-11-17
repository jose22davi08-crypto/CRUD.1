package com.Thymeleaf.demo.config;

import org.javalite.activejdbc.Base;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InicializadorBanco implements CommandLineRunner {

    @Override
    public void run(String... args) {

        try {
            System.out.println("Conectando ao banco de dados...");

            Base.open("org.sqlite.JDBC",
                    "jdbc:sqlite:meu_app.db", "", "");

            // TABELA DE PESSOAS
            Base.exec("CREATE TABLE IF NOT EXISTS pessoas (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome VARCHAR(100), " +
                    "sobrenome VARCHAR(100), " +
                    "email VARCHAR(100))");

            // TABELA DE RESTAURANTES
            Base.exec("CREATE TABLE IF NOT EXISTS restaurantes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome VARCHAR(100), " +
                    "tipo_comida VARCHAR(100), " +
                    "endereco VARCHAR(150))");

            // TABELA DE CARROS
            Base.exec("CREATE TABLE IF NOT EXISTS carros (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "modelo VARCHAR(100), " +
                    "marca VARCHAR(100), " +
                    "ano VARCHAR(4))");

            System.out.println("Banco de dados conectado!");

        } finally {
            Base.close();
        }
    }
}