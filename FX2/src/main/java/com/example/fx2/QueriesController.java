package com.example.fx2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;


//The QueriesController class actually links us to our home screen in JAVAFX,
//where we choose by clicking the mouse on a button for which query we will present the information.
public class QueriesController{


    //For each method in this class we have associated a button that opens another window associated only with it in FX.
    //This is through connectivity to the fxml document.
    //For example, in the Query1 method, there is a link to the Query1.fxml document.
    //using the MainApplication main class.
    @FXML
    public void Query1(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Query1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage1 = new Stage();
        stage1.setTitle("Welcome to the production control system");
        stage1.setScene(scene);
        stage1.show();

    }
    @FXML
    public void Query2(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Query2.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage2 = new Stage();
        stage2.setTitle("Welcome to the production control system");
        stage2.setScene(scene);
        stage2.show();
    }
    @FXML
    public void Query3(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Query3.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage2 = new Stage();
        stage2.setTitle("Welcome to the production control system");
        stage2.setScene(scene);
        stage2.show();
    }
    @FXML
    public void Query4(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Query4.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage2 = new Stage();
        stage2.setTitle("Welcome to the production control system");
        stage2.setScene(scene);
        stage2.show();
    }
    @FXML
    public void Query5(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Query5.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage2 = new Stage();
        stage2.setTitle("Welcome to the production control system");
        stage2.setScene(scene);
        stage2.show();
    }
    @FXML
    public void Query6(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Query6.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage2 = new Stage();
        stage2.setTitle("Welcome to the production control system");
        stage2.setScene(scene);
        stage2.show();
    }


}

