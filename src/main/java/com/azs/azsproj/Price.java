package com.azs.azsproj;

import com.azs.azsproj.DBa.DBconecction;
import com.azs.azsproj.DBStructure.PriceSruct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Price implements Initializable {

    @FXML
    public TextField Field_IDPrice;

    @FXML
    public TextField Field_OilPrice;

    @FXML
    public TextField Field_PricePrice;
    @FXML
    private TextField id_fil;
    @FXML
    public TableView<PriceSruct> bd_price;
    @FXML
    public TableColumn<?, ?> col_PricePrice;
    @FXML
    public TableColumn<?, ?> col_CodePrice;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    public TableColumn<?, ?> col_OilPrice;


    public Connection con = null;
    public PreparedStatement pst = null;
    public ResultSet rs = null;
    public ObservableList<PriceSruct> dataPrice;
    int index = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        con = DBconecction.DBaConecction();
        UpdateTable();
    }

    @FXML
    public void Add_Price() throws SQLException {
        String sql = "INSERT INTO price (PosCode, name, price_name) value(?,?,?)";
        String Code = Field_IDPrice.getText();
        String Name = Field_OilPrice.getText();
        double Price = Double.parseDouble(Field_PricePrice.getText());


        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, Code);
            pst.setString(2, Name);
            pst.setDouble(3, Price);


            int i = pst.executeUpdate();
            if (i == 1) System.out.println("Data insert Successfully");
            UpdateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pst.close();
        }
        //sqlUp();
        clear();
    }

    public void Edit_Prices() {
        con = DBconecction.DBaConecction();
        int value1 = Integer.parseInt(id_fil.getText());
        String value2 = Field_IDPrice.getText();
        String value3 = Field_OilPrice.getText();
        double value4 = Double.parseDouble(Field_PricePrice.getText());

        String sql = "UPDATE price SET id ='" + value1 + "',PosCode ='" + value2 + "', name='" + value3 + "',price_name='" + value4 + "' where id='" + value1 + "'";

        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            UpdateTable();
            System.out.println("Update");
        } catch (SQLException exception) {
            System.out.println("aaaaaaaaaaaaaaaa");
        }
        clear();
    }

    public void Del_Prices() {
        con = DBconecction.DBaConecction();
        try {
            pst = con.prepareStatement("DELETE FROM PRICE WHERE id =?");
            pst.setString(1, id_fil.getText());
            pst.execute();
            UpdateTable();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Noh");
        }
        clear();
    }

    private void UpdateTable() {
        setCellTablePrice();
        loadDataPriceFromDB();
    }

    private void clear() {
        Field_IDPrice.setText("");
        Field_OilPrice.setText("");
        Field_PricePrice.setText("");
    }

    @FXML
    public void getSelected() {
        index = bd_price.getSelectionModel().getFocusedIndex();
        if (index <= -1) return;
        id_fil.setText(id.getCellData(index).toString());
        Field_IDPrice.setText(col_CodePrice.getCellData(index).toString());
        Field_OilPrice.setText(col_OilPrice.getCellData(index).toString());
        Field_PricePrice.setText(col_PricePrice.getCellData(index).toString());
    }

    public void setCellTablePrice() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_CodePrice.setCellValueFactory(new PropertyValueFactory<>("PosCode"));
        col_OilPrice.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_PricePrice.setCellValueFactory(new PropertyValueFactory<>("price_name"));


        dataPrice = FXCollections.observableArrayList();
        bd_price.setItems(dataPrice);
    }

    public void loadDataPriceFromDB() {
        try {
            pst = con.prepareStatement("SELECT * FROM price");
            rs = pst.executeQuery();
            while (rs.next()) {
                dataPrice.add(new PriceSruct(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
            }
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }
}
