package com.azs.azsproj;

import com.azs.azsproj.DBa.DBconecction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class Controller extends Fill_cars implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        con = DBconecction.DBaConecction();
        setCellTable_his();
        loadDataAZSFromDB_His();
        setCellTable_Cart();
        loadDataAZSFromDB_Cart();

    }

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
    private void AddPrice() {
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

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void OneKolonka(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Kolon_OneA95A92DP.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("One");
        //stage.setFullScreen(true);
    }

    @FXML
    public void TwoKolonka(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Kolon_TwoEuro5A92_A95.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("two");
        //stage.setFullScreen(true);
    }

    @FXML
    public void ThreeKolonka(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Kolon_ThreeA95A92DP-Premium.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("three");
        //stage.setFullScreen(true);
    }

    @FXML
    public void FourKolonka(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Kolon_FourA92A80.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("four");
        //stage.setFullScreen(true);
    }

    @FXML
    public void FiveKolinkaGas(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Kolon_FiveGas.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("five");
       // stage.setFullScreen(true);
    }


}