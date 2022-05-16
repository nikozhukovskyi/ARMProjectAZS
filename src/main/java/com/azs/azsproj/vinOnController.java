package com.azs.azsproj;

import com.azs.azsproj.DBStructure.ReservAZS;
import com.azs.azsproj.DBa.DBconecction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

public class vinOnController implements Initializable {
    @FXML
    private TableView<ReservAZS> bd_reserv;
    @FXML
    private TableColumn<?, ?> col_ID;
    @FXML
    private TableColumn<?, ?> col_Code;
    @FXML
    private TableColumn<?, ?> col_Oil;
    @FXML
    private TableColumn<?, ?> col_Liters;
    @FXML
    private TableColumn<?, ?> col_Temp;
    @FXML
    private TableColumn<?,?> col_Mass;

    @FXML
    private TextField Field_Liters;
    @FXML
    private TextField Field_Mass;
    @FXML
    private TextField Field_ID;
    @FXML
    private TextField Field_Oil;
    @FXML
    private TextField Field_Temp;
    @FXML
    private Label ColLabelID;


    private Connection con = null;
    private PreparedStatement pst = null;
    private ObservableList<ReservAZS> dataAZS;
    int index = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       con = DBconecction.DBaConecction();
        UpdateTable();
    }

    @FXML
    void getSelected() {
        index = bd_reserv.getSelectionModel().getFocusedIndex();
        if(index <= -1)return;
        ColLabelID.setText(col_ID.getCellData(index).toString());
        Field_ID.setText(col_Code.getCellData(index).toString());
        Field_Oil.setText(col_Oil.getCellData(index).toString());
        Field_Liters.setText(col_Liters.getCellData(index).toString());
        Field_Temp.setText(col_Temp.getCellData(index).toString());
        Field_Mass.setText(col_Mass.getCellData(index).toString());
    }

    public void EDIT_reserve(){
        try{
            con = DBconecction.DBaConecction();
            String value0 = ColLabelID.getText();
            String value1 = Field_ID.getText();
            String value2 = Field_Oil.getText();
            String value3 = Field_Liters.getText();
            String value4 = Field_Temp.getText();
            String value5 = Field_Mass.getText();

            String sql = "UPDATE reserv SET id ='"+value0+"',Code ='"+value1+"', Oil='"+value2+"',Liters='"+value3+"',Temp='"+value4+"', Mass='"+value5+"' where id='"+value0+"'";
            pst = con.prepareStatement(sql);
            pst.execute();
            UpdateTable();
            System.out.println("Update");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddReservs() throws SQLException {
        String sql = "INSERT INTO reserv(Code, Oil, Liters, Temp, Mass) Values(?,?,?,?,?)";
        String Code = Field_ID.getText();
        String Oil = Field_Oil.getText();
        double Liters = Double.parseDouble(Field_Liters.getText());
        double Temp = Double.parseDouble(Field_Temp.getText());
        double Mass = Double.parseDouble(Field_Mass.getText());

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,Code);
            pst.setString(2, Oil);
            pst.setDouble(3, Liters);
            pst.setDouble(4, Temp);
            pst.setDouble(5, Mass);

            int i = pst.executeUpdate();
            if (i == 1)
                System.out.println("Data insert Successfully");
            UpdateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pst.close();
        }
    }

    public void DEL_reserve(){
        con = DBconecction.DBaConecction();
        try {
            pst = con.prepareStatement("DELETE FROM RESERV WHERE id =?");
            pst.setString(1, ColLabelID.getText());
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Noh");
        }
        UpdateTable();
    }


    private void UpdateTable(){
        setCellTable();
        loadDataAZSFromDB();
    }

    private void setCellTable(){
        col_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_Code.setCellValueFactory(new PropertyValueFactory<>("Code"));
        col_Oil.setCellValueFactory(new PropertyValueFactory<>("Oil"));
        col_Liters.setCellValueFactory(new PropertyValueFactory<>("Liters"));
        col_Temp.setCellValueFactory(new PropertyValueFactory<>("Temp"));
        col_Mass.setCellValueFactory(new PropertyValueFactory<>("Mass"));


        dataAZS = FXCollections.observableArrayList();
        bd_reserv.setItems(dataAZS);
    }

    private void loadDataAZSFromDB(){
        try {
            pst = con.prepareStatement("SELECT * FROM reserv");
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                dataAZS.add(new ReservAZS(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7)));
            }
        } catch (SQLException e) {
           // e.printStackTrace();
        }
    }

}
