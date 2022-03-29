package com.azs.azsproj;


import DBa.DBconecction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import static java.lang.String.valueOf;

public class Controller implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<HistoryAZS> bd_history;
    @FXML
    private TableColumn<?, ?> id_his;
    @FXML
    private TableColumn<?, ?> idCo;
    @FXML
    private TableColumn<?, ?> Liters_his;
    @FXML
    private TableColumn<?, ?> Oil_his;
    @FXML
    private TableColumn<?, ?> Price_his;
    @FXML
    private TableColumn<?, ?> Status_his;
    @FXML
    private TableColumn<?, ?> Summ_his;


    @FXML
    private TableView<ReservAZS> bd_reserv_st;
    @FXML
    private TableColumn<?, ?> col_ID_st;
    @FXML
    private TableColumn<?, ?> col_Liters_st;
    @FXML
    private TableColumn<?, ?> col_Mass_st;
    @FXML
    private TableColumn<?, ?> col_Oil_st;
    @FXML
    private TableColumn<?, ?> col_Temp_st;
    @FXML
    private TableColumn<?,?> col_Price_st;

    @FXML
    private TextField Field_oil_st;
    @FXML
    private TextField Field_Liters_oil;
    @FXML
    private Label Label_price;

    @FXML
    private Label Label_sum;

    @FXML
    private Button button_choos;



    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<ReservAZS> dataAZS;
    private ObservableList<HistoryAZS> historyAZS;
    int index = -1;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        con = DBconecction.DBaConecction();
        setCellTable_st();
        loadDataAZSFromDB_st();
        getSelected_st();
        setCellTable_his();
        loadDataAZSFromDB_His();


    }

    @FXML
    private void button_choos(ActionEvent event){
        
    }

    @FXML
    private void AddReserv(ActionEvent event) throws SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("2vinOn.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
        setCellTable_st();
        loadDataAZSFromDB_st();
    }

    @FXML
    public void getSelected_st() {
        index = bd_reserv_st.getSelectionModel().getFocusedIndex();
             if(index <= -1)return;
        Field_oil_st.setText(col_Oil_st.getCellData(index).toString());
        Label_price.setText(col_Price_st.getCellData(index).toString());
             double price = Double.parseDouble(Label_price.getText());
             double lit_oil = Double.parseDouble(Field_Liters_oil.getText());
        Label_sum.setText(valueOf(price*lit_oil));
        System.out.println(Label_sum);
    }

    public   void  FilCar() throws SQLException {
        try{
            con = DBconecction.DBaConecction();
            String value1= Field_oil_st.getText();
            double value2 = Double.parseDouble(Field_Liters_oil.getText());
            String sq = "UPDATE reserv SET Oil ='"+value1+"', Liters=Liters - '"+value2+"' where Oil='"+value1+"'";
            pst = con.prepareStatement(sq);
            pst.execute();
            setCellTable_st();
            loadDataAZSFromDB_st();
            System.out.println("Update");
        } catch (Exception e){

        }



        try {
            String sql = "INSERT INTO history(Oil, Liters, Price, Summ, Status) Values(?,?,?,?,?)";

            String Oil = Field_oil_st.getText();
            double Liters = Double.valueOf(Field_Liters_oil.getText());
            String Price = Label_price.getText();
            String Summ = Label_sum.getText();
            String Status = "a";


            pst = con.prepareStatement(sql);
            pst.setString(1, Oil);
            pst.setDouble(2, Liters);
            pst.setString(3, Price);
            pst.setString(4, Summ);
            pst.setString(5, Status);


            int i = pst.executeUpdate();
            if (i == 1)
                System.out.println("Data insert Successfully");
            setCellTable_his();
            loadDataAZSFromDB_His();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        finally {
            pst.close();
        }
        sqlUp();

    }

    private void setCellTable_st(){
        col_ID_st.setCellValueFactory(new PropertyValueFactory<>("Code"));
        col_Oil_st.setCellValueFactory(new PropertyValueFactory<>("Oil"));
        col_Liters_st.setCellValueFactory(new PropertyValueFactory<>("Liters"));
        col_Temp_st.setCellValueFactory(new PropertyValueFactory<>("Temp"));
        col_Mass_st.setCellValueFactory(new PropertyValueFactory<>("Mass"));
        col_Price_st.setCellValueFactory(new PropertyValueFactory<>("Price"));


        dataAZS = FXCollections.observableArrayList();
        bd_reserv_st.setItems(dataAZS);
    }

    private void setCellTable_his(){                                                //Set Cell History DB
        id_his.setCellValueFactory(new PropertyValueFactory<>("ID"));
        idCo.setCellValueFactory(new PropertyValueFactory<>("idCo"));
        Liters_his.setCellValueFactory(new PropertyValueFactory<>("Liters"));
        Oil_his.setCellValueFactory(new PropertyValueFactory<>("Oil"));
        Price_his.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Summ_his.setCellValueFactory(new PropertyValueFactory<>("Summ"));
        Status_his.setCellValueFactory(new PropertyValueFactory<>("Status"));

        historyAZS = FXCollections.observableArrayList();
        bd_history.setItems(historyAZS);
    }

    private void loadDataAZSFromDB_st(){
        try {
            pst = con.prepareStatement("SELECT * FROM reserv");
            rs = pst.executeQuery();
            while (rs.next()){
                dataAZS.add(new ReservAZS(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7)));
            }
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }

    private void loadDataAZSFromDB_His(){
        try {
            pst = con.prepareStatement("SELECT * FROM history");
            rs = pst.executeQuery();
            while(rs.next()){
                historyAZS.add(new HistoryAZS(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5), rs.getString(6), rs.getString(7)));
            }
        } catch (SQLException e) {
           // e.printStackTrace();
        }

    }


    public void sqlUp(){
        try{
            String qs = "UPDATE history SET idCo = id where id =id";
            pst =con.prepareStatement(qs);
            pst.execute();
            setCellTable_his();
            loadDataAZSFromDB_His();
        }
        catch (Exception e){

        }
    }
    public void sqlSor(){
        try{
            String spr = "SELECT * FROM history ORDER BY idCo";
            pst =con.prepareStatement(spr);
            pst.execute();
            setCellTable_his();
            loadDataAZSFromDB_His();
        }
        catch (Exception e){

        }
    }



}