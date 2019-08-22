/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tads.scorpions.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import br.tads.scorpions.model.Produto;
import br.tads.scorpions.dao.ProdutoDAO;

/**
 *
 * @author samuel.asantos8
 */
public class ProdutoController {
    public static boolean salvar(String pNome, String pDscProduto, Double pPrecCompra,  
             Double pPrecVenda, int pQuant, boolean pStatus, Date pDataProd)   throws SQLException, Exception {
        
        Produto p = new Produto(pNome, pDscProduto, pPrecCompra, pPrecVenda, pQuant, pStatus, pDataProd);

        return ProdutoDAO.incluirProduto(p);
    }

    
    public static boolean atualizar(int pCodProd, String pNome, String pDscProduto, Double pPrecCompra,  
             Double pPrecVenda, int pQuant, boolean pStatus, Date pDataProd)   throws SQLException, Exception {
        
        Produto p = new Produto(pCodProd, pNome, pDscProduto, pPrecCompra, pPrecVenda, pQuant, pStatus, pDataProd);

        return ProdutoDAO.atualizarProduto(p);
    }
    
    
    
    public static boolean excluir(int pCodProd)   throws SQLException, Exception {
        return ProdutoDAO.excluirProduto(pCodProd);
    }

    
    public static ArrayList<Produto> listar()   
            throws SQLException, Exception {
        ArrayList<Produto> produtos = ProdutoDAO.listarProdutos();
       
        return produtos;
    }
    
    /*public static Produto getPesquisaID(int id)   throws SQLException, Exception {
        Produto produtos = ProdutoDAO.procurarID(id);
       
        return produtos;
    }*/
}
