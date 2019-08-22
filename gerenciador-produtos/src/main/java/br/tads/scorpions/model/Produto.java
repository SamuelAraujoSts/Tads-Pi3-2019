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

    private int idProduto;
    private String nome;
    private String descricao;
    private double precoCompra;
    private double precoVenda;
    private int quantidade;
    private boolean status;
    private Date dataCadastro;
    private Categoria categoria;
    
    // Construtor utilizado no método listarProdutos()
    public Produto(int idProduto, String nome, String descricao, double precoCompra, double PrecoVenda, int Quantidade, boolean status, Date DataCadastro) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.precoCompra = precoCompra;
        this.precoVenda = PrecoVenda;
        this.quantidade = Quantidade;
        this.status = status;
        this.dataCadastro = DataCadastro;
    }

    public Produto(String nome, String Descricao, double PrecoCompra, double PrecoVenda, int Quantidade, boolean status, Date DataCadastro) {
        this.nome = nome;
        this.descricao = Descricao;
        this.precoCompra = PrecoCompra;
        this.precoVenda = PrecoVenda;
        this.quantidade = Quantidade;
        this.status = status;
        this.dataCadastro = DataCadastro;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String Nome) {
        this.nome = Nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String Descricao) {
        this.descricao = Descricao;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(double PrecoCompra) {
        this.precoCompra = PrecoCompra;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double PrecoVenda) {
        this.precoVenda = PrecoVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.quantidade = Quantidade;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date DataCadastro) {
        this.dataCadastro = DataCadastro;
    }

    public Categoria getCategoria(){
        return categoria;
    }
    
    public void setCategoria(Categoria categoria){
        this.categoria = categoria;
    }
    
    
}
