package com.example.projet;

import Donnee.Etudiant;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Statistique.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(String.valueOf(MainApplication.class.getResource("styleStatistique.css")));
        stage.setTitle("Accueil");
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}