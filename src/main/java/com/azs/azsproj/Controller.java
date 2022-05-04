package com.azs.azsproj;

import com.azs.azsproj.DBa.DBconecction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller extends Fill_cars implements Initializable  {


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        con = DBconecction.DBaConecction();
        setCellTable_his();
        loadDataAZSFromDB_His();
        setCellTable_Cart();
        loadDataAZSFromDB_Cart();

    }


    /**
     *
     * Alert alert = new Alert(Alert.AlertType.INFORMATION, "WOF WOF WOF!!!"); // alert change information
     *         alert.showAndWait();
     *
     * @NikoZhukovskyi
     */


    @FXML
    private void AddReserv() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("2vinOn.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Reserv");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    private void AddPrice(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("price.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Price");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}