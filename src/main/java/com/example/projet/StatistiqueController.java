package com.example.projet;

import Donnee.Donnees;
import Donnee.Operation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class StatistiqueController  implements Initializable{

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private PieChart pieChart;

    @FXML
    private PieChart pieSexe;

    @FXML
    private Label priveLabelpr;

    @FXML
    private Label publicLabelpr;

    @FXML
    private Label femininLabelpr;

    @FXML
    private Label masculinLabelpr;

    @FXML
    private Label Btn_Liste;

    @FXML
    private Label Btn_nouveau;

    @FXML
    void menu_btn(MouseEvent event) {
        if(event.getSource()==Btn_Liste) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("Appli.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AppliController ajout = loader.getController();
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Liste");
            Scene scene = new Scene(parent);
            scene.getStylesheets().add(String.valueOf(MainApplication.class.getResource("styleAppli.css")));
            stage.setScene(scene);
            stage.show();
        }else {
            if (event.getSource() == Btn_nouveau) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApplication.class.getResource("Ajout.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                AjoutController ajout = loader.getController();
                //ajout.setTextField(String.valueOf(a),etudiant.getNom(),etudiant.getPrenom(),etudiant.getSexe(), etudiant.getFormation(),etudiant.getEtab(),etudiant.getCommune(),etudiant.getAnnee());
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("Nouveau");
                Scene scene = new Scene(parent);
                scene.getStylesheets().add(String.valueOf(MainApplication.class.getResource("styleAjout.css")));
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    LinkedList<Integer> tailleFormation()
    {
        Donnees don = new Donnees();
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        LinkedList<LinkedList<String>> listeComp = don.listeComplet(chemin);
        LinkedList<String>formation= don.formation(listeComp);
        LinkedList<Integer> retour = new LinkedList<>();
        for(String s : formation)
        {
            LinkedList<LinkedList<String>>com = don.parametreListe(listeComp,s,2 );
            int a = com.size();
            retour.add(a);
        }
        return retour;
    }

    LinkedList<Integer> tailleEtablissement()
    {
        Donnees don = new Donnees();
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        Operation oper = new Operation();
        LinkedList<LinkedList<String>> listeComp = don.listeComplet(chemin);
        LinkedList<String>etablissement= don.etablissement(listeComp);
        LinkedList<Integer> retour = new LinkedList<>();
        for(String s : etablissement)
        {
            LinkedList<LinkedList<String>>com = don.parametreListe(listeComp,s,3 );
            int a = com.size();
            retour.add(a);
        }
        return retour;
    }

    LinkedList<Integer>tailleSexe()
    {
        Donnees don = new Donnees();
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        Operation oper = new Operation();
        LinkedList<LinkedList<String>> listeComp = don.listeComplet(chemin);
        LinkedList<String>sexe= don.sexe(listeComp);
        LinkedList<Integer> retour = new LinkedList<>();
        for(String s : sexe)
        {
            LinkedList<LinkedList<String>>com = don.parametreListe(listeComp,s,4 );
            int a = com.size();
            retour.add(a);
        }
        return retour;
    }

    void ajoutDonneeBarChart()
    {
        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        Donnees don = new Donnees();
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        LinkedList<LinkedList<String>> listeComp = don.listeComplet(chemin);
        LinkedList<String> formation = don.formation(listeComp);
        int i = 0;
        LinkedList<Integer> taille = tailleFormation();

        for (String s : formation) {
            serie.getData().add(new XYChart.Data<>(s, taille.get(i)));
            i++;
        }
        barChart.getData().add(serie);

    }

    void  ajoutDonneePieChart()
    {
        ObservableList<PieChart.Data> liste = FXCollections.observableArrayList();
        Donnees don = new Donnees();
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        LinkedList<LinkedList<String>> listeComp = don.listeComplet(chemin);
        LinkedList<String> etablissement = don.etablissement(listeComp);
        int i = 0;
        LinkedList<Integer> taille = tailleEtablissement();
        for(String s : etablissement)
        {
            liste.add(new PieChart.Data(s,taille.get(i)));
            i++;
        }

        float somme=(taille.get(0)+taille.get(1));
        float priv=Math.round(taille.get(0)*100/somme);
        priveLabelpr.setText(priv+"%");
        float publ=Math.round(taille.get(1)*100/somme);
        publicLabelpr.setText(publ+"%");

        pieChart.setData(liste);

    }
   void piesexeChart()
    {
        ObservableList<PieChart.Data> liste = FXCollections.observableArrayList();
        Donnees don = new Donnees();
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        LinkedList<LinkedList<String>> listeComp = don.listeComplet(chemin);
        LinkedList<String> sexe = don.sexe(listeComp);
        int i = 0;
        LinkedList<Integer> taille = tailleSexe();
        for(String s : sexe)
        {
            liste.add(new PieChart.Data(s,taille.get(i)));
            i++;
        }
        float somme=(taille.get(0)+taille.get(1));
        float fem=Math.round(taille.get(0)*100/somme);
        femininLabelpr.setText(fem+"%");
        float masc=Math.round(taille.get(0)*100/somme);
        masculinLabelpr.setText(masc+"%");
        pieSexe.setData(liste);

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        ajoutDonneeBarChart();
        ajoutDonneePieChart();
        piesexeChart();
    }
}
