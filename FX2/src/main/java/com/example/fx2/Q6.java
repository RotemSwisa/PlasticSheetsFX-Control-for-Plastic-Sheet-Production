package com.example.fx2;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

//The information we want to get from this class:
//The sum of the LoggedValue on each production day is shown by a pie diagram.
public class Q6 implements Initializable {
    float[] arr = new float[31];//Defining an array at the top of the class (can be used in all methods)
   public PieChart pieChart;//Pie chart definition



    @Override
    //The initialize method calls the Query6 method from which it receives the LoggedValue sum values of each production day.
    //and enters them as pie chart values.
    //Finally, the diagram shows the results.
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Query6();

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("1", arr[0]),
                new PieChart.Data("2", arr[1]),
                new PieChart.Data("3", arr[2]),
                new PieChart.Data("4", arr[3]),
                new PieChart.Data("5", arr[4]),
                new PieChart.Data("6", arr[5]),
                new PieChart.Data("7", arr[6]),
                new PieChart.Data("8", arr[7]),
                new PieChart.Data("9", arr[8]),
                new PieChart.Data("10", arr[9]),
                new PieChart.Data("11", arr[10]),
                new PieChart.Data("12", arr[11]),
                new PieChart.Data("13", arr[12]),
                new PieChart.Data("14", arr[13]),
                new PieChart.Data("15", arr[14]),
                new PieChart.Data("16", arr[15]),
                new PieChart.Data("17", arr[16]),
                new PieChart.Data("18", arr[17]),
                new PieChart.Data("19", arr[18]),
                new PieChart.Data("20", arr[19]),
                new PieChart.Data("21", arr[20]),
                new PieChart.Data("22", arr[21]),
                new PieChart.Data("23", arr[22]),
                new PieChart.Data("24", arr[23]),
                new PieChart.Data("25", arr[24]),
                new PieChart.Data("26", arr[25]),
                new PieChart.Data("27", arr[26]),
                new PieChart.Data("28", arr[27]),
                new PieChart.Data("29", arr[28]),
                new PieChart.Data("30", arr[29]),
                new PieChart.Data("31", arr[30]));

        pieChartData.forEach(data -> {
            String formattedValue = decimalFormat.format(data.getPieValue());
            data.nameProperty().bind(Bindings.concat("Day ", data.getName(), " : ", formattedValue));
        });

        pieChart.getData().addAll(pieChartData);
    }



    //The Query6 method is linked to our database.
    //It sums up the LoggedValue of each day of production in the factory
    //and inserts the amount into the place in the appropriate index in the array.
        public void Query6() {

        String str, str2;
        String[] str1 = new String[3];

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rotem", "root", "rotem");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM r1");
            while (resultSet.next()) {
                str = resultSet.getString("LogTime");
                str1 = str.split("/");
                for (int i = 1; i <= 31; i++) {
                    if (i < 10) {
                        str2 = "0" + i;
                    } else str2 = "" + i;

                    if (str1[0].compareTo(str2) == 0) {
                        arr[i - 1] = arr[i - 1] +resultSet.getFloat("LoggedValue");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
