package controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import model.Usuarios;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

import java.util.ResourceBundle;

public class LoginController implements Initializable {


    @FXML
    TextField txtLogin;
    @FXML
    PasswordField txtSenha;
    @FXML
    Button btnLogin;

    public void btnLogin() throws IOException, SQLException {
        if(login()){
            ((Stage)btnLogin.getScene().getWindow()).close();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/views/agenda.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Agenda");
            stage.show();
        }
    }

    public boolean login(ActionEvent event) throws SQLException {
        //1. Conectar ao banco
        //2. Montar lista de usuários
        //3. Pegar dados digitados login e senha
        //4. Percorrer a lista e comparar login e senha
        Connection connection;
        connection = DriverManager.getConnection("jdbc:sqlite:agendatelefone.db");
        Statement stmt;
        ResultSet rst;
        stmt = connection.createStatement();
        rst = stmt.executeQuery("select * from usuarios");
        ArrayList<Usuarios> list = new ArrayList<Usuarios>();
        //Pegar os itens do resultset e inserir na lista
        while (rst.next()){
            Usuarios u = new Usuarios();

            u.setLogin(rst.getString("login"));
            u.setPassword(rst.getString("senha"));

        }

        //login
        String login = txtLogin.getText();
        String password = txtSenha.getText();
        //validar login
        for (Usuarios u :list) {
            if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
                System.out.println("Login feito com sucesso");
                return  true;

            } else {
                System.out.println("Falha no login");

            }
            rst.close();
            connection.close();
        }
        return false;
    }

    public boolean login() throws SQLException {
        //1. Conectar ao banco
        //2. Montar lista de usuários
        //3. Pegar dados digitados login e senha
        //4. Percorrer a lista e comparar login e senha
        Connection connection;
        connection = DriverManager.getConnection("jdbc:sqlite:agendatelefone.db");
        Statement stmt;
        ResultSet rst;
        stmt = connection.createStatement();
        rst = stmt.executeQuery("select * from usuarios");
        ArrayList<Usuarios> list = new ArrayList<Usuarios>();
        //Pegar os itens do resultset e inserir na lista
        while (rst.next()){
            Usuarios u = new Usuarios();

            u.setLogin(rst.getString("login"));
            u.setPassword(rst.getString("senha"));
            list.add(u);
        }

        //login
        String login = txtLogin.getText();
        String password = txtSenha.getText();
        //validar login
        for (Usuarios u : list) {
            if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
                System.out.println("Login ok");
                return  true;

            } else {
                System.out.println("Falha no login");
            }
            rst.close();
            connection.close();
        }
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}