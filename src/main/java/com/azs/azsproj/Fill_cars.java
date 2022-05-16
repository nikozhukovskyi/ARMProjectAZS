package com.azs.azsproj;

import com.azs.azsproj.DBStructure.CartStruct;
import com.azs.azsproj.DBStructure.HistoryAZS;
import com.azs.azsproj.DBa.DBconecction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static java.lang.Double.parseDouble;

class Fill_cars extends Price {


    @FXML
    public TableView<HistoryAZS> bd_history;
    @FXML
    public TableColumn<?, ?> id_his;
    @FXML
    public TableColumn<?, ?> idCo;
    @FXML
    public TableColumn<?, ?> Liters_his;
    @FXML
    public TableColumn<?, ?> Oil_his;
    @FXML
    public TableColumn<?, ?> Price_his;
    @FXML
    public TableColumn<?, ?> Status_his;
    @FXML
    public TableColumn<?, ?> Summ_his;
    @FXML
    private TableColumn<?, ?> PosCod_his;


    @FXML
    public TableView<CartStruct> bd_cart;
    @FXML
    public TableColumn<?, ?> Cartid;
    @FXML
    public TableColumn<?, ?> Cart_id;
    @FXML
    public TableColumn<?, ?> Cart_liters;
    @FXML
    public TableColumn<?, ?> Cart_name;
    @FXML
    public TableColumn<?, ?> Cart_price;
    @FXML
    public TableColumn<?, ?> Cart_summ;


    @FXML
    public Button button_A95;
    @FXML
    public Button button_A92;
    @FXML
    public Button button_DP;
    @FXML
    public Button StartFill;


    @FXML
    public Label Label_Oil;
    @FXML
    public Label fil_price;
    @FXML
    public Label label_yes_or_no;

    @FXML
    public TextField Field_sum;
    @FXML
    public TextField Field_Liters_oil;
    @FXML
    public TextField PosCodField;
    @FXML
    public TextField FieldSummToPay;
    @FXML
    public TextField Field_RegCartID;

    public Connection con = null;
    public PreparedStatement pst = null;
    public ResultSet rs = null;
    public ObservableList<HistoryAZS> historyAZS;
    private ObservableList<CartStruct> cart;

    public void Field_sum() {
        double price = parseDouble(fil_price.getText());
        double liters = parseDouble(Field_sum.getText());
        double sum = liters / price;
        Field_Liters_oil.setText(String.format("%.3f", sum));
        //  FieldSummToPay.setText(Field_sum.getText());
    }

    public void Field_Liters_oil() {
        double price = parseDouble(fil_price.getText());
        double liters = parseDouble(Field_Liters_oil.getText());
        double sum = price * liters;
        Field_sum.setText(String.format("%.2f", sum));
        //FieldSummToPay.setText(Field_sum.getText());
    }

    public void button_DP() throws SQLException {
        // A95
        button_DP.setVisible(false);
        button_A92.setVisible(true);

        pst = con.prepareStatement("SELECT * FROM price where name = 'A92' ");
        rs = pst.executeQuery();
        while (rs.next()) {
            String code = rs.getString(2);
            String name = rs.getString(3);
            double pri = rs.getDouble(4);

            PosCodField.setText(code);
            fil_price.setText(String.valueOf(pri));
            Label_Oil.setText(name);
            Field_sum.setText("");
            Field_Liters_oil.setText("");

        }

    }

    public void button_A95() throws SQLException {
        // Diesel
        button_A95.setVisible(false);
        button_DP.setVisible(true);

        pst = con.prepareStatement("SELECT * FROM price where name = 'Diesel' ");
        rs = pst.executeQuery();
        while (rs.next()) {
            String code = rs.getString(2);
            String name = rs.getString(3);
            double pri = rs.getDouble(4);
            PosCodField.setText(code);
            fil_price.setText(String.valueOf(pri));
            Label_Oil.setText(name);
            Field_sum.setText("");
            Field_Liters_oil.setText("");
        }
    }

    public void button_A92() throws SQLException {
        // A95
        button_A92.setVisible(false);
        button_A95.setVisible(true);

        pst = con.prepareStatement("SELECT * FROM price where name = 'A95' ");
        rs = pst.executeQuery();
        while (rs.next()) {
            String code = rs.getString(2);
            String name = rs.getString(3);
            double pri = rs.getDouble(4);
            PosCodField.setText(code);
            fil_price.setText(String.valueOf(pri));
            Label_Oil.setText(name);
            Field_sum.setText("");
            Field_Liters_oil.setText("");
        }

    }

    public void StartFill() throws SQLException {
        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
        al.setTitle("Підтвердження замовлення");
        al.setContentText("Ви хочите продовжити процес замовлення?");
        Optional<ButtonType> result = al.showAndWait();
        if (result.get() == ButtonType.OK) {
            Progresstr();
            try {
                String sql = "INSERT INTO history(Oil, Liters, Price, Summ, Status, PosCode) Values(?,?,?,?,?,?)";
                String Oil = Label_Oil.getText();
                double Liters = parseDouble(Field_Liters_oil.getText());
                String Price = fil_price.getText();
                String Summ = FieldSummToPay.getText();
                String Status = "Ok";
                String PosCode = PosCodField.getText();

                pst = con.prepareStatement(sql);
                pst.setString(1, Oil);
                pst.setDouble(2, Liters);
                pst.setString(3, Price);
                pst.setString(4, Summ);
                pst.setString(5, Status);
                pst.setString(6, PosCode);

                int i = pst.executeUpdate();
                if (i == 1) System.out.println("Data insert Successfully");
                setCellTable_his();
                loadDataAZSFromDB_His();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                pst.close();
            }
            sqlUp();
            deletecartRecord();
        } else if (result.get() == ButtonType.CANCEL) {
            System.out.println("noo");
        }
    }
    //start fill Write to DB History

    public void Progresstr() {
        double Liter = parseDouble(Field_Liters_oil.getText());
        ProgressBarExample pbr = new ProgressBarExample();
        pbr.initializeUI(Liter);
        ProgressBarExample.main(Liter);
    }

    public void deletecartRecord() {

        con = DBconecction.DBaConecction();
        try {
            pst = con.prepareStatement("DELETE FROM CART");
            pst.execute();
            UpdateCart();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fuuck");
        }
        clear();
    }

    public void loadDataAZSFromDB_His() {
        try {
            pst = con.prepareStatement("SELECT * FROM history");
            rs = pst.executeQuery();
            while (rs.next()) {
                historyAZS.add(new HistoryAZS(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }

    public void setCellTable_his() {                                                //Set Cell History DB
        id_his.setCellValueFactory(new PropertyValueFactory<>("ID"));
        idCo.setCellValueFactory(new PropertyValueFactory<>("idCo"));
        Liters_his.setCellValueFactory(new PropertyValueFactory<>("Liters"));
        Oil_his.setCellValueFactory(new PropertyValueFactory<>("Oil"));
        Price_his.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Summ_his.setCellValueFactory(new PropertyValueFactory<>("Summ"));
        Status_his.setCellValueFactory(new PropertyValueFactory<>("Status"));
        PosCod_his.setCellValueFactory(new PropertyValueFactory<>("PosCod"));
        historyAZS = FXCollections.observableArrayList();
        bd_history.setItems(historyAZS);
    }//Set Cell History DB

    public void sqlUp() {
        try {
            String qs = "UPDATE history SET idCo = id where id =id";
            pst = con.prepareStatement(qs);
            pst.execute();
            setCellTable_his();
            loadDataAZSFromDB_His();
        } catch (Exception e) {
            System.out.println("aas");
        }
    }

    public void alertError() {
        Alert wro = new Alert(Alert.AlertType.ERROR, "Перевірте правельність вводу");
        wro.showAndWait();

    }

    public void preview() throws SQLException {
        String getNameOil = Label_Oil.getText();
        pst = con.prepareStatement("SELECT * FROM price where name = '" + getNameOil + "' ");
        rs = pst.executeQuery();
        while (rs.next()) {
            String nameOil = rs.getString(2);
            PosCodField.setText(nameOil);
        }
        pst = con.prepareStatement("select exists(select CartOil from cart where CartOil='A95' or CartOil='A92' or CartOil='Diesel')");
        rs = pst.executeQuery();
        while (rs.next()) {
            String yes_or_no = rs.getString(1);
            label_yes_or_no.setText(yes_or_no);

        }
        if (label_yes_or_no.getText().equals("1")) {
            System.out.println("yeee");
            alertError();
        } else {

            String sql = "insert into cart(Cartid, CartOil, CartLiters, CartPrice, CartSumm) values(?,?,?,?,?)";
            String cartPos = PosCodField.getText();
            String cartOil = Label_Oil.getText();
            double cartLiters = parseDouble(Field_Liters_oil.getText());
            double cartPrice = parseDouble(fil_price.getText());
            double cartSumm = parseDouble(Field_sum.getText());


            try {
                pst = con.prepareStatement(sql);
                pst.setString(1, cartPos);
                pst.setString(2, cartOil);
                pst.setDouble(3, cartLiters);
                pst.setDouble(4, cartPrice);
                pst.setDouble(5, cartSumm);


                int i = pst.executeUpdate();
                if (i == 1) System.out.println("Data insert Successfully");
                setCellTable_Cart();
                loadDataAZSFromDB_Cart();

            } catch (SQLException e) {
                e.printStackTrace();
            } //finally {
            //pst.close();
            // }

            pst = con.prepareStatement("SELECT SUM(CartSumm) FROM cart");
            rs = pst.executeQuery();
            while (rs.next()) {
                double pri = rs.getDouble(1);

                FieldSummToPay.setText(String.valueOf(pri));
            }

        }
    }

    @FXML
    public void getSelectedCart() {

        index = bd_cart.getSelectionModel().getFocusedIndex();
        if (index <= -1) return;
        Field_RegCartID.setText(Cartid.getCellData(index).toString());
        //Field_reg_Liters_cart.setText(Cart_liters.getCellData(index).toString());
    }

    public void Delete() {
        con = DBconecction.DBaConecction();
        try {
            pst = con.prepareStatement("DELETE FROM CART WHERE id =?");
            pst.setString(1, Field_RegCartID.getText());
            pst.execute();
            UpdateCart();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Noh");
        }
        clear();
    }

    public void UpdateCart() {
        setCellTable_Cart();
        loadDataAZSFromDB_Cart();
    }

    public void loadDataAZSFromDB_Cart() {
        try {
            pst = con.prepareStatement("SELECT * FROM asz.cart");
            rs = pst.executeQuery();
            while (rs.next()) {
                cart.add(new CartStruct(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6)));
            }
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }

    public void setCellTable_Cart() {
        Cartid.setCellValueFactory(new PropertyValueFactory<>("id"));
        Cart_id.setCellValueFactory(new PropertyValueFactory<>("Cartid"));
        Cart_name.setCellValueFactory(new PropertyValueFactory<>("CartOil"));
        Cart_liters.setCellValueFactory(new PropertyValueFactory<>("CartLiters"));
        Cart_price.setCellValueFactory(new PropertyValueFactory<>("CartPrice"));
        Cart_summ.setCellValueFactory(new PropertyValueFactory<>("CartSumm"));

        cart = FXCollections.observableArrayList();
        bd_cart.setItems(cart);
    }

    public void PosCod() throws SQLException {
        pst = con.prepareStatement("SELECT PosCode, name, price_name FROM price where PosCode=?");
        String code = PosCodField.getText();
        pst.setString(1, code);
        rs = pst.executeQuery();
        if (!rs.next()) {
            alertError();
            PosCodField.setText("");
            Label_Oil.setText("");
            fil_price.setText("");
        } else {
            PosCodField.setText(rs.getString("PosCode"));
            Label_Oil.setText(rs.getString("name"));
            fil_price.setText(rs.getString("price_name"));
        }
    }     // test PosCode

    public void clear() {
        fil_price.setText("");
        Label_Oil.setText("");
        Field_sum.setText("");
        Field_Liters_oil.setText("");
        PosCodField.setText("");
        FieldSummToPay.setText("");
    }
}