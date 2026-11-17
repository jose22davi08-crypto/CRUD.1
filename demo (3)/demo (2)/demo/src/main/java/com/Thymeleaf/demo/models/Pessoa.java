
package com.Thymeleaf.demo.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("pessoas")
public class Pessoa extends Model {

    public String getNome() {
        return getString("nome");
    }

    public void setNome(String nome) {
        set("nome", nome);
    }

    public String getSobrenome() {
        return getString("sobrenome");
    }

    public void setSobrenome(String sobrenome) {
        set("sobrenome", sobrenome);
    }

    public String getEmail() {
        return getString("email");
    }

    public void setEmail(String email) {
        set("email", email);
    }
}

