package dao;

import database.DbConnection;
import javafx.fxml.Initializable;
import model.Usuarios;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class UsuarioDAO implements Initializable {

    public  void create(Usuarios user) throws SQLException {
        Connection connection;
        PreparedStatement stmt;
        connection = DbConnection.getConnectionSqlite();
        String save = "insert into usuarios (nome, telefone, email, login, senha) values (?, ?, ?, ?, ?,)";
        stmt = connection.prepareStatement(save);
        //Receber dados do parâmetro
        stmt.setString(1, Usuarios.getName());
        stmt.setInt(2, Usuarios.getTelefone());
        stmt.setString(3, Usuarios.getEmail());
        stmt.setString(3, user.getLogin());
        stmt.setString(4, user.getPassword());
        //Executar a instrução
        stmt.execute();
        connection.close();
    }

    public static Usuarios search(int id) throws SQLException {
        Connection connection;
        PreparedStatement stmt;
        ResultSet rst;
        connection = DbConnection.getConnectionSqlite();
        String pesquisar = "select *from usuarios where id = ?";
        stmt = connection.prepareStatement(pesquisar);
        stmt.setInt(1, id);
        //Receber resultado da consulta
        rst = stmt.executeQuery();
        Usuarios user = new Usuarios();
        while(rst.next()){

            user.setName(rst.getString("name"));
            user.setTelefone(rst.getInt("telefone"));
            user.setEmail(rst.getString("email"));
            user.setLogin(rst.getString("login"));
            user.setPassword(rst.getString("senha"));
        }
        rst.close();
        connection.close();
        return user;
    }

    public  void update(Usuarios user) throws SQLException {
        Connection connection;
        connection = DbConnection.getConnectionSqlite();
        PreparedStatement stmt;
        if(connection != null){
            String update = "update usuarios set nome = ?, telefone = ?, email = ?, login = ?, senha = ? where id = ?";
            stmt = connection.prepareStatement(update);
            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getTelefone());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getLogin());
            stmt.setString(5, user.getPassword());
            stmt.setInt(6, user.getId());
            stmt.close();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
