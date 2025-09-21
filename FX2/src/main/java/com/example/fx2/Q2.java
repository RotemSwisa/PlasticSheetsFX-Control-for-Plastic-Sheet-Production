package com.example.fx2;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.ResourceBundle;

//The information we want to get from this class:
//What is the total LoggedValue for LogID? (Print the total)
public class Q2 implements Initializable {

    int[] arr = new int[6]; // Array to store the sum of LoggedValue for each LogID
    int logid; // Selected LogID
    String str = null; // String to display the total sum
    public Label Print; // Label to display the sum information
    public BarChart<String, Integer> barchart; // BarChart to display the sum of LoggedValue
    @FXML
    public ChoiceBox<Integer> LogID; // ChoiceBox to select LogID



    // Method executed when the LogID button is clicked
    @FXML
    public void getLogID(MouseEvent event){
        logid = LogID.getSelectionModel().getSelectedItem();

        // Clear existing data in the BarChart
        barchart.getData().clear();

        // Run the database query and update UI components in a separate thread
        runQueryInBackground();
    }



    // Method executed when the class is initialized
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LogID.getItems().addAll(1, 2, 3, 4, 5, 6);

        // Initialize BarChart with empty data
        XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
        series1.setName("LogID"); // Set series name

        // Add LogID and corresponding sum of LoggedValue to the series
        for (int i = 0; i < arr.length; i++) {
            series1.getData().add(new XYChart.Data<>(String.valueOf(i + 1), 0)); // Initialize with 0
        }

        barchart.getData().add(series1); // Add series to the BarChart
    }



    // Method to execute database query in background thread
    private void runQueryInBackground() {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                Query2(); // Execute database query
                return null;
            }

            @Override
            protected void succeeded() {
                // Update UI components after the database query is complete
                Print.setText(str); // Update label with sum information
                updateBarChart(); // Update BarChart with data
            }
        };

        new Thread(task).start(); // Start background task
    }



    // Method to update the BarChart with data
    private void updateBarChart() {
        // Update BarChart in JavaFX application thread
        XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
        series1.setName("LogID"); // Set series name

        // Add LogID and corresponding sum of LoggedValue to the series
        for (int i = 0; i < arr.length; i++) {
            series1.getData().add(new XYChart.Data<>(String.valueOf(i + 1), arr[i]));
        }

        barchart.getData().add(series1); // Add series to the BarChart
    }



    // Method to execute database query and calculate sum of LoggedValue
    public void Query2() {
        float sum = 0;
        int logID = logid;

        // Reset array values to 0
        Arrays.fill(arr, 0);

        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rotem", "root", "rotem");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT LoggedValue ,LogID  FROM r1");

            // Iterate through the result set
            while (resultSet.next()) {
                // Calculate total sum for the selected LogID
                if (resultSet.getInt("LogID") == logID) {
                    sum += resultSet.getFloat("LoggedValue");
                }

                // Store sum of LoggedValue for each LogID in the array
                arr[resultSet.getInt("LogID") - 1] += resultSet.getInt("LoggedValue");
            }

            // Create a string to display the sum information
            str = "The sum of the LoggedValue for LogID " + logID + " is: " + sum;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}