package com.example.fx2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//The information we want to get from this class:
//From date A to date B (inclusive)- what is the LineID where production was maximum? (Print LineID)
public class Q4 {


    String Date1, Date2, str;//Defining strings at the top of the class (can be used in all methods)
    public DatePicker from ;//Creating a date selection box
    public DatePicker to ;//Creating a date selection box
    public Label Print;//Setting a label for printing the information of the query 4.

    @FXML
    //The getPrint method calls the Query4 method and receives the string we want to print in the label.
    //By connecting to a mouse click action on a button.
    public void getPrint(MouseEvent event){
        Query4();
        Print.setText(str);
    }

    //Methods for choosing the dates that the user gives.
    public void selectDate1(ActionEvent event){
        Date1 = from.getValue().toString();
    }
    public void selectDate2(ActionEvent event){
        Date2 = to.getValue().toString();
    }


    //The Query4 method is linked to our database.
    //Finds the maximum LoggedValue between the two dates the user entered.
    //and finally returns a string containing the LineID of that LoggedValue.
    public void Query4() {

        float  max4=Integer.MIN_VALUE;//Boot at the max to enter the min
        String str4="\nOne or two of the dates you selected have no data!";
        String[] r = new String[3];
        String R = null ;
        Date1 = reverseString(Date1)+" 00:00";
        String[] date1 = Date1.split("/");
        Date2 = reverseString(Date2)+" 23:59";
        String[] date2 = Date2.split("/");

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rotem", "root", "rotem");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT LoggedValue ,lineID ,LogTime  FROM r1");
            while (resultSet.next()) {
                R = resultSet.getString("LogTime");
                r = R.split("/");
                if(r[0].compareTo(date1[0])>= 0 && r[0].compareTo(date2[0])<= 0 && resultSet.getFloat("LoggedValue")> max4 ) {
                    if (r[1].compareTo(date1[1])>= 0 && r[1].compareTo(date2[1])<= 0 ) {
                        if (r[2].compareTo(date1[2])>= 0 && r[2].compareTo(date2[2])<= 0 ) {
                            max4 = resultSet.getFloat("LoggedValue");
                            str4 = resultSet.getString("LineID") + "\nIts LoggedValue is: " + max4;
                        }
                    }
                }
            }
            str = "From date " +Date1+" to date " +Date2+
                    " (inclusive) \nthe LineID where production(LoggedValue) was maximum is: "+str4;
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //The reverseString method receives each time the date received from the user in DatePicker
    // and reverses the resulting string and replaces the '-' character with the '/'
    // to match the database with which the user works.
    public static String reverseString(String input) {
        // Replace '-' with '/'
        input = input.replace('-', '/');

        // Split the string into words using '-'
        String[] words = input.split("/");

        // Reverse the array of words
        StringBuilder reversed = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]);
            if (i != 0) {
                // Add '/' between words
                reversed.append("/");
            }
        }
        return reversed.toString();
    }

}
