package com.example.fx2; //All classes are in package com.example.fx2.

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

//In the MainApplication class we run the program,
//and all the classes and queries that were available as soon as we want to run them.
public class MainApplication extends Application {


    //The start method belongs to the home window in FX. Through connectivity to the Queries.fxml document.
    //There the call to all the methods in the QueriesController class is created.
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Queries.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Plastic sheets for greenhouses");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}