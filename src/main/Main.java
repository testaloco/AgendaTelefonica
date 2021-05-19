package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{

        //Tela de Login

        Stage prymaryStage;
        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
        Scene mainScene = new Scene(fxmlLogin);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Login do Sistema");
        primaryStage.setResizable(false);
        primaryStage.show();
    }



    public static void main(String[] args) throws ClassNotFoundException{
        launch(args);
    }
}
