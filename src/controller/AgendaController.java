package controller;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.*;

import java.util.ResourceBundle;

public class AgendaController implements Initializable {
    @FXML
    TextField txtID;
    @FXML
    TextField txtNome;
    @FXML
    TextField txtTelefone;
    @FXML
    TextField txtEmail;
    @FXML
    TextField txtLog;
    @FXML
    PasswordField txtSen;

    @FXML
    Button btnCadastro;
    @FXML
    Button btnAlterar;
    @FXML
    Button btnPesquisar;
    @FXML
    Button btnExcluir;
    @FXML
    Button btnVoltar;

    @FXML
    public void cadastroAction(Event event) {
        System.out.println("ok");
        try {
            Connection connection;
            PreparedStatement stmt;
            connection = DriverManager.getConnection("jdbc:sqlite:agendatelefone.db");
            String save = "insert into usuarios (nome,telefone,email,login,senha) values (?,?,?,?,?)";
            stmt = connection.prepareStatement(save);
            //Receber dados do parâmetro
            stmt.setString(1, txtNome.getText());
            stmt.setInt(2, Integer.parseInt(txtTelefone.getText()));
            stmt.setString(3, txtEmail.getText());
            stmt.setString(4, txtLog.getText());
            stmt.setString(5, txtSen.getText());
            //Executar a instrução
            stmt.executeUpdate();
            connection.close();
            System.out.println("ok");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public void searchAction(ActionEvent event) throws SQLException {
        Connection connection;
        connection = DriverManager.getConnection("jdbc:sqlite:agendatelefone.db");
        PreparedStatement stmt;
        ResultSet rst;
        String pesquisar = "SELECT *from usuarios WHERE id = ?";
        stmt = connection.prepareStatement(pesquisar);
        int id = Integer.parseInt(txtID.getText());
        stmt.setInt(1, id);
        rst = stmt.executeQuery();
        while(rst.next()){
            txtNome.setText(rst.getString("nome"));
            txtTelefone.setText(Integer.toString(rst.getInt("telefone")));
            txtEmail.setText(rst.getString("email"));
            txtLog.setText(rst.getString("login"));
            txtSen.setText(rst.getString("senha"));
        }
        rst.close();
        stmt.close();

    }

    public void updateAction(ActionEvent event) throws SQLException {
        Connection connection;
        connection = DriverManager.getConnection("jdbc:sqlite:agendatelefone.db");
        PreparedStatement stmt;
        if (connection != null) {
            String update = "update usuarios set nome = ?, telefone = ?, email = ? ,login = ?, senha = ? where id = ?";
            stmt = connection.prepareStatement(update);
            stmt.setString(1, txtNome.getText());
            stmt.setInt(2, Integer.parseInt(txtTelefone.getText()));
            stmt.setString(3, txtEmail.getText());
            stmt.setString(4, txtLog.getText());
            stmt.setString(5, txtSen.getText());
            stmt.setInt(6,Integer.valueOf(txtID.getText()));
            if (stmt.execute() == false)
                System.out.println("Registro alterado  com Sucesso");
            else
                System.out.println("Error!!!!");
            stmt.close();
        }
    }

    public void deleteAction (ActionEvent event) throws SQLException {
        Connection connection;
        connection = DriverManager.getConnection("jdbc:sqlite:agendatelefone.db");
        PreparedStatement stmt;
        if (connection != null) {
            String delete = "delete from usuarios where id = ?";
            stmt = connection.prepareStatement(delete);

            stmt.setInt(1,Integer.valueOf(txtID.getText()));
            if (stmt.execute() == false){
                System.out.println("Registro removido com Sucesso");
                txtID.clear();
                txtNome.clear();
                txtTelefone.clear();
                txtEmail.clear();
                txtLog.clear();
                txtSen.clear();
            }
            else
                System.out.println("Falha na remoção");
            stmt.close();
        }
    }

    public boolean voltarAction() throws IOException, SQLException {
        if(voltar()){
            ((Stage)btnVoltar.getScene().getWindow()).close();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();
        }
        return false;
    }

    private boolean voltar() {
        ((Stage) btnVoltar.getScene().getWindow()).close();
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.show();
        return false;
    }


    @Override
    public void initialize (URL location, ResourceBundle resources){

    }
}