package com.Thymeleaf.demo.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("restaurantes")
public class Restaurante extends Model {

    public String getNome() {
        return getString("nome");
    }

    public void setNome(String nome) {
        set("nome", nome);
    }

    public String getTipoComida() {
        return getString("tipo_comida");
    }

    public void setTipoComida(String tipoComida) {
        set("tipo_comida", tipoComida);
    }

    public String getEndereco() {
        return getString("endereco");
    }

    public void setEndereco(String endereco) {
        set("endereco", endereco);
    }
}
