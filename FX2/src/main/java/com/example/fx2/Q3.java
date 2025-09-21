package com.example.fx2;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//The information we want to get from this class:
//What is the LineID where LoggedValue was minimal? (Print LineID)
public class Q3 {

    String str = null;//Defining a string at the head of the class (it can be used in all methods)
    public Label Print;//Setting a label for printing the information of the query 3.
    @FXML
    public TextField username;
    @FXML
    public PasswordField Password;

    @FXML
    //The getPrint method calls the Query3 method and receives the string we want to print in the label.
    //Before printing the string, the user will have to give a username and password,
    //if he does not or makes a mistake in typing, an error will be printed on the screen.
    //All this by clicking the mouse on the button.
    public void getPrint(MouseEvent event){
        if (username.getText().toString().equals("plastic_sheets")&& Password.getText().toString().equals("1234")) {
            Query3();
            Print.setText(str);
        }
        else if (username.getText().toString().equals("")||Password.getText().toString().equals("")) {
            Print.setText("Error Must enter username and password");
        }
        else {
            Print.setText("Error The password or username are incorrect");
        }
    }



    //The Query3 method is linked to our database.
    //checking the minimum LoggedValue between the data
    //(by initializing the variable value to the maximum and inserting the minimum each time a new one is found)
    //Finally returns a string containing the LineID of that minimum LoggedValue.
    public void Query3() {

        float  min=Integer.MAX_VALUE; //Boot at the max to enter the min
        String str3 = null;

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rotem", "root", "rotem");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT LoggedValue , LineID FROM r1");
            while (resultSet.next()) {
                if(resultSet.getFloat("LoggedValue")<min && resultSet.getFloat("LoggedValue")>0){
                    min = resultSet.getFloat("LoggedValue");
                    str3 = resultSet.getString("LineID");
                }
            }
            str = "The minimum LoggedValue is in the following LineID: " +str3;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
