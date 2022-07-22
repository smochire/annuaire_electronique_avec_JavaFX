package Donnee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.LinkedList;

public class OperationTable
{
    public ObservableList<Etudiant>getListe(LinkedList<LinkedList<String>>listeComp){
        ObservableList<Etudiant> liste = FXCollections.observableArrayList();
        int i = 0;
        while(listeComp.size()>i)
        {
            String a = String.valueOf(i+1);
            String annee = listeComp.get(i).get(0);
            String comm = listeComp.get(i).get(1);
            String form = listeComp.get(i).get(2);
            String etab = listeComp.get(i).get(3);
            String sexe = listeComp.get(i).get(4);
            String nom = listeComp.get(i).get(5);
            String prenom = listeComp.get(i).get(6);
            Etudiant etudiant = new Etudiant(a,nom,prenom,sexe,form,etab,comm,annee);
            liste.add(etudiant);
            i++;
        }

        return liste;
    }

    public void showListe(LinkedList<LinkedList<String>>listeComp , TableView<Etudiant> tablev,TableColumn<Etudiant, String> idTable,TableColumn<Etudiant, String> nomTable,TableColumn<Etudiant, String> prenomTable,TableColumn<Etudiant, String> sexeTable,TableColumn<Etudiant, String> formationTable,TableColumn<Etudiant, String> etabTable,TableColumn<Etudiant, String> communeTable,TableColumn<Etudiant, String> anneeTable)

    {
        ObservableList<Etudiant> liste = getListe(listeComp);
        idTable.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("id"));
        nomTable.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("nom"));
        prenomTable.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("prenom"));
        sexeTable.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("sexe"));
        formationTable.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("formation"));
        etabTable.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("etab"));
        communeTable.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("commune"));
        anneeTable.setCellValueFactory(new PropertyValueFactory<Etudiant,String>("annee"));
        tablev.setItems(liste);
    }
}
