package com.example.fx2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


//The information we want to get from this class:
//When the LoggedValue is equal to zero? (Display all rows of data)
public class Q5 {
    String str = "";//Defining a string at the head of the class (it can be used in all methods)
    public Label Print;//Setting a label for printing the information of the query 5.

    //getPrint method calls the Query5 method and receives the string that we want to print in the label.
    //By connecting to a mouse click action on a button.
    @FXML
    public void getPrint(MouseEvent event){
        Query5();
        Print.setText(str);
    }

    //The Query5 method is linked to our database.
    //Looking for all the lines where the LoggedValue worthless.
    //and finally returns a string containing all the data rows that meet this.
    public void Query5() {

        int x=0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rotem", "root", "rotem");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM r1");
            while (resultSet.next()) {
                if(resultSet.getFloat("LoggedValue")==0)
                {
                    x = x+1;
                    str =str+x +")  "+ resultSet.getString("UnitType")+"\t"+resultSet.getString("Description")+
                            "\t\t"+resultSet.getString("CmdType")+"\t\t"+resultSet.getString("LoggedValue")
                            +"\t"+resultSet.getString("LogTime")+"\t"+resultSet.getString("LineID")+
                            "\t\t"+resultSet.getString("LogID")+"\t\t";
                    if (x%2 == 0){
                        str = str+"\n";
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
