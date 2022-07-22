package com.example.projet;

import Donnee.Donnees;
import Donnee.Operation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;




public class ModifierController implements Initializable {

    @FXML
    private TextField nomText;

    @FXML
    private TextField prenomText;

    @FXML
    private ComboBox<String> sexeCombo;

    @FXML
    private TextField formationText;

    @FXML
    private ComboBox<String> etabCombo;

    @FXML
    private TextField communeText;

    @FXML
    private TextField anneeText;

    @FXML
    private Button modifierBt;

    @FXML
    private Button annulerBt;

    @FXML
    private TextField idTexte;

    void setTextField(String id,String nom,String prenom,String sexe,String formation,String etab, String commune,String annee)
    {
        idTexte.setText(id);
        nomText.setText(nom);
        prenomText.setText(prenom);
        formationText.setText(formation);
        communeText.setText(commune);
        anneeText.setText(annee);
        sexeCombo.setValue(sexe);
        etabCombo.setValue(etab);

    }

    @FXML
    void modifAction(ActionEvent event)
    {
        Operation ope = new Operation();
        ope.modifier(Integer.parseInt(idTexte.getText()),nomText.getText().toUpperCase(),prenomText.getText(),formationText.getText(),anneeText.getText(),communeText.getText(),sexeCombo.getValue(),etabCombo.getValue());
        razAnnuler();
        String temp = idTexte.getText();
        idTexte.setText("");
        ((Node)(event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(MainApplication.class.getResource("Appli.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AppliController ajout = loader.getController();
        ajout.select(Integer.parseInt(temp));
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        scene.getStylesheets().add(String.valueOf(MainApplication.class.getResource("styleAppli.css")));
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void onClickCombo(ActionEvent event)
    {
        String a = sexeCombo.getSelectionModel().getSelectedItem();
        String b = etabCombo.getSelectionModel().getSelectedItem();
    }
    void combo()
    {
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        Donnees don = new Donnees();
        LinkedList<LinkedList<String>> listeComp = don.listeComplet(chemin);
        LinkedList<String> sexe = don.sexe(listeComp);
        LinkedList<String> etab = don.etablissement(listeComp);
        ObservableList<String> obserSexe = FXCollections.observableArrayList();
        ObservableList<String> obserEtab = FXCollections.observableArrayList();
        for(String s : sexe)
        {
            obserSexe.add(s);
        }
        for(String s : etab)
        {
            obserEtab.add(s);
        }
        sexeCombo.setItems(obserSexe);
        etabCombo.setItems(obserEtab);
        sexeCombo.setPromptText("Choisir ici");
        etabCombo.setPromptText("Choisir ici");
    }

    void razAnnuler()
    {
        if(!(nomText.getText().isEmpty()))
        {
            nomText.setText("");
        }
        if(!prenomText.getText().isEmpty())
            prenomText.setText("");
        if(!communeText.getText().isEmpty())
            communeText.setText("");
        if(!formationText.getText().isEmpty())
            formationText.setText("");
        if(!anneeText.getText().isEmpty())
            anneeText.setText("");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combo();
    }
}
