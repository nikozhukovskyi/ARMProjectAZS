package com.azs.azsproj;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(com.azs.azsproj.Application.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 765);
        stage.setTitle("AZS");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}