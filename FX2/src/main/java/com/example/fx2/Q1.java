package com.example.fx2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//The information we want to get from this class:
//What is the maximum LoggedValue? (print the entire line of data)
public class Q1 {

    String str = null;  //Defining a string at the head of the class (it can be used in all methods)
    public Label Print; //Setting a label for printing the information of the query 1.


    @FXML
    //getPrint method calls the Query1 method and receives the string that we want to print in the label.
    //By connecting to a mouse click action on a button.
    public void getPrint(MouseEvent event){
        Query1();
        Print.setText(str);
    }


    //The Query1 method is linked to our database.
    //Searches for the maximum LoggedValue among the data (by initializing the minimum variable),
    //and finally returns a string containing all the data rows of that maximum LoggedValue.
    public String Query1() {

        float max1=Integer.MIN_VALUE;//Boot at the min to enter the max
        String  str1= null ;

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rotem", "root", "rotem");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT LoggedValue ,LogTime, LineID ,LogID  FROM r1");
            while (resultSet.next()) {
                if (resultSet.getFloat("LoggedValue") > max1) {
                    max1 = resultSet.getFloat("LoggedValue");
                    str1 = (resultSet.getString("LoggedValue") + "\n His LogTime: "
                            + resultSet.getString("LogTime") + "\n His LineID: " +
                            resultSet.getString("LineID") + "\n And his LogID: "
                            + resultSet.getString("LogID"));
                }
            }
            str = "The LoggedValue maximum is: "+str1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }
}
