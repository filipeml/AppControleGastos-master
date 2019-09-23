package com.example.gastos;

import java.io.Serializable;

public class Gastos implements Serializable {
    private int _id;
    private String descricao;
    private String data;
    private String tipo;
    private String valor;

    public Gastos(String descricao, String data, String tipo, String valor) {
        this.descricao = descricao;
        this.data= data;
        this.tipo = tipo;
        this.valor = valor;
    }

    public Gastos(int id, String descricao, String data, String tipo, String valor) {
        this.descricao = descricao;
        this.data= data;
        this.tipo = tipo;
        this.valor = valor;
        this._id = id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getId(){
        return _id;
    }

    @Override
    public String toString() {
        return "Gastos{" +
                "descricao='" + descricao + '\'' +
                ", data=" + data +
                ", tipo='" + tipo + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}
