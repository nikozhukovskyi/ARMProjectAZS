module com.azs.azsproj {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.azs.azsproj to javafx.fxml;
    exports com.azs.azsproj;
}