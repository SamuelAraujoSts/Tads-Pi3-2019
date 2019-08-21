/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tads.scorpions.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author samuel.asantos8
 */
public class ProdutoController {
    public static boolean salvar(String pNome, String pDscProduto, Double pPrecCompra,  
             Double pPrecVenda, int pQuant, boolean pStatus, Date pDataProd)   throws SQLException, Exception {
        
        Produto p = new Produto(pNome, pDscProduto, pPrecCompra, pPrecVenda, pQuant, pStatus, pDataProd);

        return ProdutoDAO.inserir(p);
    }

    
    public static boolean atualizar(int pCodProd, String pNome, String pDscProduto, Double pPrecCompra,  
             Double pPrecVenda, int pQuant, boolean pStatus, Date pDataProd)   throws SQLException, Exception {
        
        Produto p = new Produto(pCodProd, pNome, pDscProduto, pPrecCompra, pPrecVenda, pQuant, pStatus, pDataProd);

        return ProdutoDAO.atualizar(p);
    }
    
    
    
    public static boolean excluir(int pCodProd)   throws SQLException, Exception {
        return ProdutoDAO.excluir(pCodProd);
    }

    
    public static ArrayList<Produto> getPesquisa(int id)   
            throws SQLException, Exception {
        ArrayList<Produto> produtos = ProdutoDAO.procurar(id);
       
        return produtos;
    }
    
    public static Produto getPesquisaID(int id)   throws SQLException, Exception {
        Produto produtos = ProdutoDAO.procurarID(id);
       
        return produtos;
    }
}
