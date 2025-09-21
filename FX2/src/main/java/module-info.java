module com.example.fx2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.fx2 to javafx.fxml;
    exports com.example.fx2;
}