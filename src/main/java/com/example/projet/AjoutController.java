package com.example.projet;

import Donnee.Donnees;
import Donnee.Operation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AjoutController implements Initializable {

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

    public void setNomText(TextField nomText) {
        this.nomText = nomText;
    }

    public void setPrenomText(TextField prenomText) {
        this.prenomText = prenomText;
    }

    public void setFormationText(TextField formationText) {
        this.formationText = formationText;
    }

    public void setCommuneText(TextField communeText) {
        this.communeText = communeText;
    }

    public void setAnneeText(TextField anneeText) {
        this.anneeText = anneeText;
    }

    @FXML
    private TextField anneeText;

    @FXML
   private Button EnregistrerBt;

    @FXML
   private Button annulerBt;

    @FXML
    void ajouterBt(ActionEvent event) {
        Operation ope = new Operation();
        if(event.getSource()==annulerBt)
        {
            razAnnuler();
        }else
        {
            if(nomText.getText()!="" && prenomText.getText()!="" && sexeCombo.getValue()!="" && formationText.getText()!="" && etabCombo.getValue()!="" && anneeText.getText()!="") {
                    ajouter();
                    razAnnuler();
            }else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Vérification");
                alert.setHeaderText("Vérification des champs");
                alert.setContentText("Tous les champs sont obligatoires?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    alert.hide();
                }

            }
        }
        ((Node)(event.getSource())).getScene().getWindow().hide();

    }
    void razAnnuler()
    {
        if(!nomText.getText().isEmpty())
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
    void ajouter()
    {
        LinkedList<String>ajoutListe = ajout();
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        Operation oper = new Operation();
        oper.ecrireCsvAjout(ajoutListe,chemin);
    }
    LinkedList<String> ajout ()
    {
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        Operation oper = new Operation();
        Donnees don = new Donnees();
        LinkedList<LinkedList<String>> listeComp = don.listeComplet(chemin);
        String nom = nomText.getText().toUpperCase();
        String prenom = prenomText.getText();
        String annee = anneeText.getText();
        String formation = formationText.getText();
        String commune = communeText.getText();
        String sexe = sexeCombo.getValue();
        String etab = etabCombo.getValue();
        LinkedList<String>retour = oper.ajouter(listeComp,nom,prenom,commune,annee,sexe,etab,formation);
        return retour;
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
        sexeCombo.setPromptText("Sexe");
        etabCombo.setPromptText("Etablissement");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combo();


    }
}
