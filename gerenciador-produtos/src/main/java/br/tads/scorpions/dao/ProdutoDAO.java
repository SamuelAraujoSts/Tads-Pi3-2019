/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tads.scorpions.dao;

import br.tads.scorpions.model.Categoria;
import br.tads.scorpions.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author vinicius.oelias
 */
public class ProdutoDAO {

    private static PreparedStatement instrucao;
    private static ArrayList<Produto> listaDeProdutos;

    public ProdutoDAO() { // Avisa no console caso o programa consiga se conectar sem problemas ao BD;
        try {
            Connection testaConexao = new ConnectionFactory().getConnection();
            System.out.println("Conexão estabelecida com sucesso!");
            testaConexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o Banco de Dados!");
        }

    }

    public static boolean incluirProduto(Produto produto) {
        boolean retorno = false;

        String insereProdutoSQL = "INSERT INTO produto(nome, descricao, preco_compra, preco_venda, quantidade, disponivel, dt_cadastro)"
                + " VALUES (?,?,?,?,?,?,CURRENT_TIMESTAMP())";

        String insereRelacaoCategoriaSQL = "INSERT INTO produto_categoria(id_produto, id_categoria)"
                + " VALUES ((SELECT LAST_INSERT_ID()), ?) ";

        // try-with-resources || Conexão será aberta novamente dentro do "try" e fechada automaticamente ao final dele.
        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(insereProdutoSQL);

            instrucao.setString(1, produto.getNome());
            instrucao.setString(2, produto.getDescricao());
            instrucao.setDouble(3, produto.getPrecoCompra());
            instrucao.setDouble(4, produto.getPrecoVenda());
            instrucao.setInt(5, produto.getQuantidade());
            instrucao.setBoolean(6, produto.isStatus());

            instrucao.execute();
            instrucao.close();

            // Caso o produto tenha categoria associada.
            if (produto.getCategoria() != null) {
                instrucao = conexao.prepareStatement(insereRelacaoCategoriaSQL);
                Categoria categoria = produto.getCategoria();
                instrucao.setInt(1, categoria.getIDCategoria());

                instrucao.execute();
                instrucao.close();
            }

            retorno = true;

        } catch (SQLException e) {
            System.out.println("Erro na operação de Cadastro!");
            throw new RuntimeException(e);

        } finally {
            return retorno;
        }
    }

    public static ArrayList<Produto> listarProdutos() {
        String codigoSQL
                = "SELECT "
                + "produto.id,"
                + "produto.nome,"
                + "produto.descricao,"
                + "produto.preco_compra,"
                + "produto.preco_venda,"
                + "produto.quantidade,"
                + "produto.disponivel,"
                + "produto.dt_cadastro,"
                + "categoria.id,"
                + "categoria.nome "
                + "FROM produto "
                + "INNER JOIN produto_categoria ON "
                + "produto_categoria.id_produto = produto.id "
                + "INNER JOIN categoria ON "
                + "categoria.id = produto_categoria.id_categoria";

        listaDeProdutos = new ArrayList<Produto>();

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            ResultSet resultado = instrucao.executeQuery();

            while (resultado.next()) {
                Produto produto = new Produto(
                        resultado.getInt("produto.id"),
                        resultado.getString("produto.nome"),
                        resultado.getString("produto.descricao"),
                        resultado.getDouble("produto.preco_compra"),
                        resultado.getDouble("produto.preco_venda"),
                        resultado.getInt("produto.quantidade"),
                        resultado.getBoolean("produto.disponivel"),
                        resultado.getDate("produto.dt_cadastro")
                );

                Categoria categoria = new Categoria();
                categoria.setIDCategoria(resultado.getInt("categoria.id"));
                categoria.setNome(resultado.getString("categoria.nome"));
                produto.setCategoria(categoria);

                listaDeProdutos.add(produto);
            }

            resultado.close();
            instrucao.close();
            conexao.close();

            return listaDeProdutos;
        } catch (SQLException e) {
            System.out.println("Erro na operação de Consulta!");
            throw new RuntimeException(e);
        }
    }

    public static boolean atualizarProduto(Produto produto) {

        boolean retorno = false;

        String atualizaProdutoSQL = "UPDATE produto SET "
                + "nome = ?, "
                + "descricao = ?,"
                + "preco_compra = ?,"
                + "preco_venda = ?,"
                + "quantidade = ?,"
                + "disponivel = ?,"
                + "WHERE id = ?";

        String atualizaCategoriaSQL = "UPDATE produto_categoria SET "
                + "id_categoria = ? "
                + "WHERE id_produto = ?";

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(atualizaProdutoSQL);

            instrucao.setString(1, produto.getNome());
            instrucao.setString(2, produto.getDescricao());
            instrucao.setDouble(3, produto.getPrecoCompra());
            instrucao.setDouble(4, produto.getPrecoVenda());
            instrucao.setInt(5, produto.getQuantidade());
            instrucao.setBoolean(6, produto.isStatus());

            int linhasAfetadasProduto = instrucao.executeUpdate();
            instrucao.close();

            int linhasAfetadasCategoria = 0;

            // Caso o produto tenha categoria associada.
            if (produto.getCategoria() != null) {
                instrucao = conexao.prepareStatement(atualizaCategoriaSQL);

                Categoria categoria = produto.getCategoria();

                instrucao.setInt(1, categoria.getIDCategoria());
                instrucao.setInt(2, produto.getIdProduto());

                linhasAfetadasCategoria = instrucao.executeUpdate();
                instrucao.close();

            }

            retorno = linhasAfetadasProduto > 0 || linhasAfetadasCategoria > 0;

        } catch (SQLException e) {
            System.out.println("Erro na operação de Atualização!");
            throw new RuntimeException(e);

        } finally {
            return retorno;
        }

    }

    public static boolean excluirProduto(int i) {

        boolean retorno = false;

        String deletaProdutoSQL = "DELETE FROM produto WHERE id = ?";
        String deletaRelacaoCategoriaSQL = "DELETE FROM produto_categoria WHERE id_produto = ?";

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(deletaProdutoSQL);

            instrucao.setInt(1, i);

            int linhasAfetadasProduto = instrucao.executeUpdate();
            instrucao.close();

            instrucao = conexao.prepareStatement(deletaRelacaoCategoriaSQL);

            instrucao.setInt(1, i);

            int linhasAfetadasCategoria = instrucao.executeUpdate();
            instrucao.close();

            retorno = linhasAfetadasProduto > 0 || (linhasAfetadasProduto > 0 && linhasAfetadasCategoria > 0);

        } catch (SQLException e) {
            System.out.println("Erro na operação de Exclusão!");
            throw new RuntimeException(e);

        } finally {
            return retorno;
        }

    }

}
