package com.Thymeleaf.demo.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("carros")
public class Carro extends Model {

    public String getModelo() {
        return getString("modelo");
    }

    public void setModelo(String modelo) {
        set("modelo", modelo);
    }

    public String getMarca() {
        return getString("marca");
    }

    public void setMarca(String marca) {
        set("marca", marca);
    }

    public String getAno() {
        return getString("ano");
    }

    public void setAno(String ano) {
        set("ano", ano);
    }
}