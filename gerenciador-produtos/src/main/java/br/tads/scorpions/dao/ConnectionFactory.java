/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tads.scorpions.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vinicius
 */
public class ConnectionFactory {

    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/produtobd";
        try {
            return DriverManager.getConnection(url, "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
