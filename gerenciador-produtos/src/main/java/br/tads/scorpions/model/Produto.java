/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tads.scorpions.model;

import java.util.Date;

/**
 *
 * @author samuel.asantos8
 */
public class Produto {

    private String Nome;
    private String Descricao;
    private double PrecoCompra;
    private double PrecoVenda;
    private int Quantidade;
    private boolean status;
    private Date DataCadastro;

    public Produto(String nome, String Descricao, double PrecoCompra, double PrecoVenda, int Quantidade, boolean status, Date DataCadastro) {
        this.Nome = nome;
        this.Descricao = Descricao;
        this.PrecoCompra = PrecoCompra;
        this.PrecoVenda = PrecoVenda;
        this.Quantidade = Quantidade;
        this.status = status;
        this.DataCadastro = DataCadastro;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public double getPrecoCompra() {
        return PrecoCompra;
    }

    public void setPrecoCompra(double PrecoCompra) {
        this.PrecoCompra = PrecoCompra;
    }

    public double getPrecoVenda() {
        return PrecoVenda;
    }

    public void setPrecoVenda(double PrecoVenda) {
        this.PrecoVenda = PrecoVenda;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDataCadastro() {
        return DataCadastro;
    }

    public void setDataCadastro(Date DataCadastro) {
        this.DataCadastro = DataCadastro;
    }

    
    
}
