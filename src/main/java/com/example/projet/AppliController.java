package com.example.projet;

import Donnee.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class AppliController implements Initializable {

    OperationTable opeTab = new OperationTable();
    Operation ope = new Operation();
    Donnees don = new Donnees();
    @FXML
    private TableView<Etudiant> tableV;

    @FXML
    private Button ajoutBT;

    @FXML
    private TableColumn<Etudiant, String> idTable;

    @FXML
    private TableColumn<Etudiant, String> nomTable;

    @FXML
    private TableColumn<Etudiant, String> prenomTable;

    @FXML
    private TableColumn<Etudiant, String> sexeTable;

    @FXML
    private TableColumn<Etudiant, String> formationTable;

    @FXML
    private TableColumn<Etudiant, String> etabTable;

    @FXML
    private TableColumn<Etudiant, String> communeTable;

    @FXML
    private TableColumn<Etudiant, String> anneeTable;

    @FXML
    private Button supprimerBt;

    @FXML
    private Button modifierBt;

    @FXML
    private TextField nomT;

    @FXML
    private Label labelRecherche;

    @FXML
    private Button actualiserBt;

    @FXML
    private Button rechercheBt;

    String input() {
        String a = this.nomT.getText();
        return a;
    }

    public LinkedList<String> rechercheEntree() {
        String input = input();
        LinkedList<String> retour = new LinkedList<>();
        int j = 0;
        int p = 0;

        int i = 0;
        while (i < input.length()){
            if(input.charAt(i)==' '||input.charAt(i)==';'||input.charAt(i)==','||input.charAt(i)==':'||input.charAt(i)=='.') {
                retour.add(input.substring(p, i));
                p = i + 1;
            }else if((input.charAt(i)==' ' && input.charAt(i+1)==' ')&&(input.charAt(i)==';' && input.charAt(i+1)==';')&&(input.charAt(i)==':' && input.charAt(i+1)==':')&(input.charAt(i)==',' && input.charAt(i+1)==',')&&(input.charAt(i)=='.' && input.charAt(i+1)=='.'))
            {
                p = i+2;
            }else if(i == input.length()-1)
            {
                if(input.charAt(input.length()-1)==' '||input.charAt(input.length()-1)==','||input.charAt(input.length()-1)==':'||input.charAt(input.length()-1)==';'||input.charAt(input.length()-1)==':')
                {
                    retour.add(input.substring(p, i));
                }
                else
                {
                    retour.add(input.substring(p, i+1));
                }
            }
            i++;
        }
        return retour;
    }

    public LinkedList<LinkedList<String>> nomRecher() {
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        LinkedList<LinkedList<String>> listeComp = don.listeComplet(chemin);
        LinkedList<LinkedList<String>> retour;
        LinkedList<String> rechercheEn = rechercheEntree();
        LinkedList<String> nom = don.nom(listeComp);
        LinkedList<LinkedList<String>> retourNom = new LinkedList<>();
        String entree = input();

        int i = 0;
        while (i < rechercheEn.size()) {
            String a = rechercheEn.get(i).toUpperCase();
            retour = don.parametreListe(listeComp, a, 5);

            int j = 0;
            while (j < retour.size()) {
                if (a.charAt(0) == retour.get(j).get(5).charAt(0))
                    retourNom.add(retour.get(j));
                j++;
            }
            i++;
        }
        return retourNom;
    }

    public LinkedList<LinkedList<String>> prenomRecher() {
        LinkedList<LinkedList<String>> retourPrenom = new LinkedList<>();
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        LinkedList<LinkedList<String>> listeComp = don.listeComplet(chemin);
        LinkedList<LinkedList<String>> retour = new LinkedList<>();
        LinkedList<String> prenom = don.prenom(listeComp);
        LinkedList<String> rechercheEn = rechercheEntree();

        int i = 0;
        while (i < rechercheEn.size()) {
            String a = rechercheEn.get(i);
            String b = a.toUpperCase();
            a = b.substring(0, 1) + a.substring(1, a.length() - 1);
            retour = don.parametreListe(listeComp, a, 6);

            int j = 0;
            while (j < retour.size()) {
                //if(a.charAt(0)==retour.get(j).get(6).charAt(0))
                retourPrenom.add(retour.get(j));
                j++;
            }
            i++;
        }
        return retourPrenom;
    }

    public LinkedList<LinkedList<String>> communeRecher() {
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        LinkedList<LinkedList<String>> listeComp = don.listeComplet(chemin);
        LinkedList<LinkedList<String>> retour = new LinkedList<>();
        LinkedList<String> rechercheEn = rechercheEntree();
        LinkedList<String> commune = don.commune(listeComp);
        LinkedList<LinkedList<String>> retourCommune = new LinkedList<>();
        int i = 0;
        while (i < rechercheEn.size()) {
            String a = rechercheEn.get(i);
            retour = don.parametreListe(listeComp, a, 1);
            int j = 0;
            while (j < retour.size()) {
                retourCommune.add(retour.get(j));
                j++;
            }
            i++;
        }

        if (retourCommune.isEmpty()) {
            i = 0;
            while (i < rechercheEn.size()) {
                String a = rechercheEn.get(i);
                String b = a.toUpperCase();
                a = b.substring(0, 1) + a.substring(1, a.length());

                retour = don.parametreListe(listeComp, a, 1);
                int j = 0;
                while (j < retour.size()) {
                    retourCommune.add(retour.get(j));
                    j++;
                }
                i++;
            }

        }
        return retourCommune;
    }

    public LinkedList<LinkedList<String>> formationRecher() {
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        LinkedList<LinkedList<String>> listeComp = don.listeComplet(chemin);
        LinkedList<LinkedList<String>> retour = new LinkedList<>();
        LinkedList<String> rechercheEn = rechercheEntree();
        LinkedList<String> formation = don.formation(listeComp);
        LinkedList<LinkedList<String>> retourFormation = new LinkedList<>();


        if (retourFormation.isEmpty()) {
            int i = 0;
            while (i < rechercheEn.size()) {
                String a = rechercheEn.get(i);
                String b = a.toUpperCase();
                retour = don.parametreListe(listeComp, a, 2);
                int j = 0;
                while (j < retour.size()) {
                    retourFormation.add(retour.get(j));
                    j++;
                }
                i++;
            }
        }

        int i = 0;
        if (retourFormation.isEmpty()) {
            while (i < rechercheEn.size()) {
                String a = rechercheEn.get(i);
                String b = a.toUpperCase();
                a = b.substring(0, 1) + a.substring(1, a.length());
                retour = don.parametreListe(listeComp, a, 2);
                int j = 0;
                while (j < retour.size()) {
                    retourFormation.add(retour.get(j));
                    j++;
                }
                i++;
            }
        }
        return retourFormation;
    }

    public LinkedList<LinkedList<String>> sexeRecher() {
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        LinkedList<LinkedList<String>> listeComp = don.listeComplet(chemin);
        LinkedList<LinkedList<String>> retour = new LinkedList<>();
        LinkedList<String> rechercheEn = rechercheEntree();
        LinkedList<String> sexe = don.sexe(listeComp);
        LinkedList<LinkedList<String>> retourSexe = new LinkedList<>();
        int i = 0;
        while (i < rechercheEn.size()) {
            String a = rechercheEn.get(i);
            String b = a.toUpperCase();
            a = b.substring(0, 1) + a.substring(1, a.length());
            retour = don.parametreListe(listeComp, a, 4);
            int j = 0;
            while (j < retour.size()) {
                if (a.charAt(0) == retour.get(j).get(4).charAt(0))
                    retourSexe.add(retour.get(j));
                j++;
            }
            i++;
        }
        return retourSexe;
    }

    public LinkedList<LinkedList<String>> rentreeRecher() {
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        LinkedList<LinkedList<String>> listeComp = don.listeComplet(chemin);
        LinkedList<LinkedList<String>> retour = new LinkedList<>();
        LinkedList<String> rechercheEn = rechercheEntree();
        LinkedList<String> annee = don.rentree(listeComp);
        LinkedList<LinkedList<String>> retourRentree = new LinkedList<>();
        int i = 0;
        while (i < rechercheEn.size()) {
            String a = rechercheEn.get(i);
            retour = don.parametreListe(listeComp, a, 0);
            int j = 0;
            while (j < retour.size()) {
                retourRentree.add(retour.get(j));
                j++;
            }
            i++;
        }
        return retourRentree;
    }

    public LinkedList<LinkedList<String>> etabRecher() {
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        LinkedList<LinkedList<String>> listeComp = don.listeComplet(chemin);
        LinkedList<LinkedList<String>> retour = new LinkedList<>();
        LinkedList<String> rechercheEn = rechercheEntree();
        LinkedList<String> etab = don.etablissement(listeComp);
        LinkedList<LinkedList<String>> retourEtab = new LinkedList<>();
        int i = 0;
        while (i<rechercheEn.size())
        {
            String a = rechercheEn.get(i);
            retour = don.parametreListe(listeComp, a, 3);
            int j = 0;
            while (j < retour.size()) {
                retourEtab.add(retour.get(j));
                j++;
            }
            i++;

        }
        if(retour.isEmpty()) {
            i = 0;
            while (i < rechercheEn.size()) {
                String a = rechercheEn.get(i);
                if (a.charAt(0) == 'e' || a.charAt(0) == 'é' || a.charAt(0) == 'E') {
                    a = a.replace(a.charAt(0), 'É');
                }

                retour = don.parametreListe(listeComp, a, 3);
                int j = 0;
                while (j < retour.size()) {
                    retourEtab.add(retour.get(j));
                    j++;
                }
                i++;
            }
        }
        System.out.println(retourEtab);
        return retourEtab;
    }

    LinkedList<LinkedList<String>> recherche(int p) {
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        LinkedList<LinkedList<String>> listeComp = don.listeComplet(chemin);
        String entree = input();
        String temp = entree.toUpperCase();
        entree = temp.substring(0, 1) + entree.substring(1, entree.length() );
        LinkedList<LinkedList<String>> retour = don.parametreListe(listeComp, entree, p);
        LinkedList<LinkedList<String>> retourFormation = new LinkedList<>();
        int k = 0;
        while (k < retour.size()) {
            retourFormation.add(retour.get(k));
            k++;
        }
        return retourFormation;
    }

    public LinkedList<LinkedList<String>> rechercheAB(Label recherche) {
        LinkedList<LinkedList<String>> nom = nomRecher();
        System.out.println(nom);
        LinkedList<LinkedList<String>> prenom = prenomRecher();
        System.out.println(nom);
        System.out.println(prenom);
        LinkedList<LinkedList<String>> sexe = sexeRecher();
        LinkedList<LinkedList<String>> etab = etabRecher();
        LinkedList<LinkedList<String>> commune = communeRecher();
        LinkedList<LinkedList<String>> formation = formationRecher();
        LinkedList<LinkedList<String>> rentree = rentreeRecher();
        System.out.println(formation);
        System.out.println(commune);
        LinkedList<LinkedList<String>> sortie = new LinkedList<>();
        LinkedList<LinkedList<String>> sortie1 = recherche1(labelRecherche);
        sortie = sortie1;
        if (sortie.isEmpty()) {
            int nomT = nom.size();
            int prenomT = prenom.size();
            int sexeT = sexe.size();
            int etabT = etab.size();
            int communeT = commune.size();
            int formationT = formation.size();
            int rentreeT = rentree.size();

            if (nomT == 0 && prenomT == 0 && sexeT == 0 && etabT == 0 && communeT == 0 && formationT == 0 && rentreeT == 0) {
                recherche.setText("Il n'y a pas de resultats");
            }
            if (nomT == 0) {
                if (prenomT == 0) {
                    if (sexeT == 0) {
                        if (etabT == 0) {
                            if (communeT == 0) {
                                if (formationT == 0) {
                                    sortie = rentree;
                                } else {
                                    if (rentreeT == 0) {
                                        sortie = formation;
                                    } else {
                                        sortie = don.recherche(formation, rentree, 0);
                                        if(sortie.isEmpty())
                                            sortie = don.recherche(rentree,formation,2);
                                    }
                                }
                            } else if (formationT == 0) {
                                if (rentreeT == 0)
                                    sortie = commune;
                                else
                                {
                                    sortie = don.recherche(commune, rentree, 0);
                                    if(sortie.isEmpty())
                                        sortie = don.recherche(rentree,commune,1);
                                }
                            } else if (rentreeT == 0)
                            {
                                sortie = don.recherche(commune, formation, 2);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(formation,commune,1);
                            }
                            else {
                                LinkedList<LinkedList<String>> temp = don.recherche(formation, rentree, 0);
                                if(temp.isEmpty())
                                    temp = don.recherche(rentree,formation,2);
                                sortie = don.recherche(temp, commune, 1);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(commune,temp,0);
                            }
                        } else if (communeT == 0) {
                            if (formationT == 0) {
                                if (rentreeT == 0)
                                    sortie = etab;
                                else
                                {
                                    sortie = don.recherche(etab, rentree, 0);
                                    if(sortie.isEmpty())
                                        sortie = don.recherche(rentree,etab,3);
                                }
                            } else if (rentreeT == 0) {
                                sortie = don.recherche(etab, formation, 1);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(formation,etab,3);
                            } else {
                                LinkedList<LinkedList<String>> temp = don.recherche(etab, formation, 2);
                                if(temp.isEmpty())
                                    temp = don.recherche(formation,etab,3);
                                sortie = don.recherche(temp, rentree, 0);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(rentree,temp,2);
                            }
                        } else if (formationT == 0) {
                            if (rentreeT == 0) {
                                {
                                    sortie = don.recherche(etab, commune, 1);
                                        if(sortie.isEmpty())
                                            sortie = don.recherche(etab,commune,3);
                                }
                            } else {
                                LinkedList<LinkedList<String>> temp = don.recherche(commune, rentree, 0);
                                if(temp.isEmpty())
                                    temp = don.recherche(rentree,commune,1);
                                sortie = don.recherche(temp, etab, 3);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(etab,temp,0);
                            }
                        } else if (rentreeT == 0) {
                            LinkedList<LinkedList<String>> temp = don.recherche(etab, commune, 1);
                            if(temp.isEmpty())
                                temp = don.recherche(commune,etab,3);
                            sortie = don.recherche(temp, formation, 2);
                            if(sortie.isEmpty())
                                sortie = don.recherche(formation,temp,1);
                        } else {
                            LinkedList<LinkedList<String>> temp = don.recherche(etab, commune, 1);
                            if(temp.isEmpty())
                                temp = don.recherche(commune,etab,3);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, formation, 2);
                            if(temp1.isEmpty())
                                temp1 = don.recherche(formation,temp,1);
                            sortie = don.recherche(temp1, rentree, 0);
                            if(sortie.isEmpty())
                                sortie = don.recherche(rentree,temp1,1);

                        }
                    } else if (etabT == 0) {
                        if (communeT == 0) {
                            if (formationT == 0) {
                                if (rentreeT == 0)
                                    sortie = sexe;
                                else
                                {
                                    sortie = don.recherche(sexe, rentree, 0);
                                    if(sortie.isEmpty())
                                        sortie = don.recherche(rentree,sexe,4);
                                }
                            } else if (rentreeT == 0) {
                                sortie = don.recherche(sexe, formation, 2);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(formation,sexe,4);
                            } else {
                                LinkedList<LinkedList<String>> temp = don.recherche(sexe, formation, 4);
                                if(temp.isEmpty())
                                    temp = don.recherche(formation,sexe,4);
                                sortie = don.recherche(temp, rentree, 0);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(rentree,temp,4);
                            }
                        } else if (formationT == 0) {
                            if (rentreeT == 0) {
                                sortie = don.recherche(sexe, commune, 1);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(commune,sexe,4);
                            } else {
                                LinkedList<LinkedList<String>> temp = don.recherche(sexe, commune, 1);
                                if(temp.isEmpty())
                                    temp = don.recherche(commune,sexe,4);
                                sortie = don.recherche(temp, rentree, 0);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(rentree,temp,1);
                            }
                        } else if (rentreeT == 0) {
                            LinkedList<LinkedList<String>> temp = don.recherche(sexe, commune, 1);
                            if(temp.isEmpty())
                                temp = don.recherche(commune,sexe,4);
                            sortie = don.recherche(temp, formation, 2);
                            if(sortie.isEmpty())
                                sortie = don.recherche(formation,temp,1);
                        } else {
                            LinkedList<LinkedList<String>> temp = don.recherche(sexe, commune, 1);
                            if(temp.isEmpty())
                                temp = don.recherche(commune,sexe,4);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, formation, 2);
                            if(temp1.isEmpty())
                                 temp1 = don.recherche(formation,temp,1);
                            sortie = don.recherche(temp1, rentree, 0);
                            if(sortie.isEmpty())
                                sortie = don.recherche(rentree,temp1,1);
                        }
                    } else if (communeT == 0) {
                        if (formationT == 0) {
                            if (rentreeT == 0) {
                                sortie = don.recherche(sexe, etab, 3);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(etab,sexe,4);
                            } else {
                                LinkedList<LinkedList<String>> temp = don.recherche(sexe, etab, 3);
                                if(temp.isEmpty())
                                    temp = don.recherche(etab,sexe,4);
                                sortie = don.recherche(temp, rentree, 0);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(rentree,temp,3);
                            }
                        } else if (rentreeT == 0) {
                            LinkedList<LinkedList<String>> temp = don.recherche(sexe, etab, 3);
                            if(temp.isEmpty())
                                temp = don.recherche(etab,sexe,4);
                            sortie = don.recherche(temp, formation, 2);
                            if(sortie.isEmpty())
                                sortie = don.recherche(formation,temp,3);
                        } else {
                            LinkedList<LinkedList<String>> temp = don.recherche(sexe, etab, 3);
                            if(temp.isEmpty())
                                temp = don.recherche(etab,sexe,4);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, formation, 2);
                            if(temp1.isEmpty())
                                temp1 = don.recherche(formation,temp,3);
                            sortie = don.recherche(temp1, rentree, 0);
                            if(sortie.isEmpty())
                                sortie = don.recherche(rentree,temp1,3);
                        }
                    } else if (formationT == 0) {
                        if (rentreeT == 0) {
                            LinkedList<LinkedList<String>> temp = don.recherche(sexe, etab, 3);
                            if(temp.isEmpty())
                                temp = don.recherche(etab,sexe,4);
                            sortie = don.recherche(temp, commune, 1);
                            if(sortie.isEmpty())
                                sortie = don.recherche(commune,temp,3);
                        } else {
                            LinkedList<LinkedList<String>> temp = don.recherche(sexe, etab, 3);
                            if(temp.isEmpty())
                                temp = don.recherche(etab,sexe,4);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, commune, 1);
                            if(temp1.isEmpty())
                                temp1 = don.recherche(commune,temp,3);
                            sortie = don.recherche(temp1, rentree, 0);
                            if(sortie.isEmpty())
                                sortie = don.recherche(rentree,temp1,3);
                        }
                    } else if (rentreeT == 0) {
                        LinkedList<LinkedList<String>> temp = don.recherche(sexe, etab, 3);
                        if(temp.isEmpty())
                            temp = don.recherche(etab,sexe,4);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, commune, 1);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(commune,temp,3);
                        sortie = don.recherche(temp1, formation, 2);
                        if(sortie.isEmpty())
                            sortie = don.recherche(formation,temp1,3);
                    } else {
                        LinkedList<LinkedList<String>> temp = don.recherche(sexe, etab, 3);
                        if(temp.isEmpty())
                            temp = don.recherche(etab,sexe,4);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, commune, 1);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(commune,temp,3);
                        LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, formation, 2);
                        if(temp2.isEmpty())
                            temp2 = don.recherche(formation,temp1,3);
                        sortie = don.recherche(temp2, rentree, 0);
                        if(sortie.isEmpty())
                            sortie = don.recherche(rentree,temp2,3);
                    }
                } else if (sexeT == 0) {

                    if (etabT == 0) {
                        if (communeT == 0) {
                            if (formationT == 0) {
                                if (rentreeT == 0)
                                    sortie = prenom;
                                else
                                {
                                    sortie = don.recherche(prenom, rentree, 0);
                                    if(sortie.isEmpty())
                                        sortie = don.recherche(rentree,prenom,6);
                                }
                            } else if (rentreeT == 0) {
                                sortie = don.recherche(prenom, formation, 2);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(formation,prenom,6);
                            } else {
                                LinkedList<LinkedList<String>> temp = don.recherche(prenom, formation, 2);
                                if(temp.isEmpty())
                                    temp = don.recherche(formation,prenom,6);
                                sortie = don.recherche(temp, rentree, 0);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(rentree,temp,2);
                            }
                        } else if (formationT == 0) {
                                     if (rentreeT == 0) {
                                         {
                                             sortie = don.recherche(prenom, commune, 1);
                                             if(sortie.isEmpty())
                                                 sortie = don.recherche(commune,prenom,6);
                                             if(sortie.isEmpty())
                                                 sortie = prenom;
                                         }
                                     } else {
                                        LinkedList<LinkedList<String>> temp = don.recherche(prenom, commune, 1);
                                         if(temp.isEmpty())
                                             temp = don.recherche(commune,prenom,6);
                                        sortie = don.recherche(temp, rentree, 0);
                                        if(sortie.isEmpty())
                                            sortie = don.recherche(rentree,temp,1);
                                     }
                        } else if (rentreeT == 0) {
                            LinkedList<LinkedList<String>> temp = don.recherche(prenom, commune, 1);
                            if(temp.isEmpty())
                                temp = don.recherche(commune,prenom,6);
                            sortie = don.recherche(temp, formation, 2);
                            if(sortie.isEmpty())
                                sortie = don.recherche(formation,temp,1);

                        } else {
                            LinkedList<LinkedList<String>> temp = don.recherche(prenom, commune, 1);
                            if(temp.isEmpty())
                                temp = don.recherche(commune,prenom,6);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, formation, 2);
                            if(temp1.isEmpty())
                                temp1 = don.recherche(formation,temp,1);
                            sortie = don.recherche(temp1, rentree, 0);
                            if(sortie.isEmpty())
                                sortie = don.recherche(rentree,temp1,1);
                        }
                    } else if (communeT == 0) {
                        if (formationT == 0) {
                            if (rentreeT == 0)
                            {
                                sortie = don.recherche(prenom, etab, 3);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(etab,prenom,6);
                            }
                            else {
                                LinkedList<LinkedList<String>> temp = don.recherche(prenom, etab, 3);
                                if(temp.isEmpty())
                                    temp = don.recherche(etab,prenom,6);
                                sortie = don.recherche(temp, rentree, 0);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(rentree,temp,3);
                            }
                        } else {
                            if (rentreeT == 0) {
                                LinkedList<LinkedList<String>> temp = don.recherche(prenom, etab, 3);
                                if(temp.isEmpty())
                                    temp = don.recherche(etab,prenom,6);
                                sortie = don.recherche(temp, formation, 1);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(formation,temp,3);
                            } else {
                                LinkedList<LinkedList<String>> temp = don.recherche(prenom, etab, 3);
                                if(temp.isEmpty())
                                    temp = don.recherche(etab,prenom,6);
                                LinkedList<LinkedList<String>> temp1 = don.recherche(temp, formation, 1);
                                if(temp1.isEmpty())
                                    temp1 = don.recherche(formation,temp,3);
                                sortie = don.recherche(temp1, rentree, 0);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(rentree,temp1,3);
                            }
                        }
                    } else if (formationT == 0) {
                        if (rentreeT == 0) {
                            LinkedList<LinkedList<String>> temp = don.recherche(prenom, etab, 3);
                            if(temp.isEmpty())
                                temp = don.recherche(etab,prenom,6);
                            sortie = don.recherche(temp, commune, 1);
                            if(sortie.isEmpty())
                                sortie = don.recherche(commune,temp,3);
                        } else {
                            LinkedList<LinkedList<String>> temp = don.recherche(prenom, etab, 3);
                            if(temp.isEmpty())
                                temp = don.recherche(etab,prenom,6);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, commune, 1);
                            if(temp1.isEmpty())
                                temp1 = don.recherche(commune,temp,3);
                            sortie = don.recherche(temp1, rentree, 0);
                            if(sortie.isEmpty())
                                sortie = don.recherche(rentree,temp1,3);
                        }
                    } else if (rentreeT == 0) {
                        LinkedList<LinkedList<String>> temp = don.recherche(prenom, etab, 3);
                        if(temp.isEmpty())
                            temp = don.recherche(etab,prenom,6);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, commune, 1);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(commune,temp,3);
                        sortie = don.recherche(temp1, formation, 2);
                        if(sortie.isEmpty())
                            sortie = don.recherche(formation,temp1,3);
                    } else {
                        LinkedList<LinkedList<String>> temp = don.recherche(prenom, etab, 3);
                        if(temp.isEmpty())
                            temp = don.recherche(etab,prenom,6);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, commune, 1);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(commune,temp,3);
                        LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, formation, 2);
                        if(temp2.isEmpty())
                            temp2 = don.recherche(formation,temp1,3);
                        sortie = don.recherche(temp2, rentree, 0);
                    }
                } else if (etabT == 0) {
                    if (communeT == 0) {
                        if (formationT == 0) {
                            if (rentreeT == 0) {
                                sortie = don.recherche(prenom, sexe, 4);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(sexe,prenom,6);
                            } else {
                                LinkedList<LinkedList<String>> temp = don.recherche(prenom, sexe, 4);
                                if(temp.isEmpty())
                                    temp = don.recherche(sexe,prenom,6);
                                sortie = don.recherche(temp, rentree, 0);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(rentree,temp,4);
                            }
                        } else if (rentreeT == 0) {
                            LinkedList<LinkedList<String>> temp = don.recherche(prenom, sexe, 4);
                            if(temp.isEmpty())
                                temp = don.recherche(sexe,prenom,6);
                            sortie = don.recherche(temp, formation, 2);
                            if(sortie.isEmpty())
                                sortie = don.recherche(formation,temp,4);
                        } else {
                            LinkedList<LinkedList<String>> temp = don.recherche(prenom, sexe, 4);
                            if(temp.isEmpty())
                                temp = don.recherche(sexe,prenom,6);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, formation, 2);
                            if(temp1.isEmpty())
                                temp1 = don.recherche(formation,temp,4);
                            sortie = don.recherche(temp1, rentree, 0);
                            if(sortie.isEmpty())
                                sortie = don.recherche(rentree,temp1,4);
                        }
                    } else if (formationT == 0) {
                        if (rentreeT == 0) {
                            LinkedList<LinkedList<String>> temp = don.recherche(prenom, commune, 1);
                            if(temp.isEmpty())
                                temp = don.recherche(commune,prenom,6);
                            sortie = don.recherche(temp, sexe, 4);
                            if(sortie.isEmpty())
                                sortie = don.recherche(sexe,temp,1);
                        } else {
                            LinkedList<LinkedList<String>> temp = don.recherche(prenom, sexe, 4);
                            if(temp.isEmpty())
                                temp = don.recherche(sexe,prenom,6);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, commune, 1);
                            if(temp1.isEmpty())
                                temp1 = don.recherche(commune,temp,4);
                            sortie = don.recherche(temp1, rentree, 0);
                            if(sortie.isEmpty())
                                sortie = don.recherche(rentree,temp1,4);
                        }
                    } else if (rentreeT == 0) {
                        LinkedList<LinkedList<String>> temp = don.recherche(prenom, sexe, 4);
                        if(temp.isEmpty())
                            temp = don.recherche(sexe,prenom,6);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, commune, 1);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(commune,temp,4);
                        sortie = don.recherche(temp, formation, 2);
                        if(sortie.isEmpty())
                            sortie = don.recherche(formation,temp,4);
                    } else {
                        LinkedList<LinkedList<String>> temp = don.recherche(prenom, sexe, 4);
                        if(temp.isEmpty())
                            temp = don.recherche(sexe,prenom,6);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, commune, 1);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(commune,temp,4);
                        LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, formation, 2);
                        if(temp2.isEmpty())
                            temp2 = don.recherche(formation,temp1, 4);
                        sortie = don.recherche(temp2, rentree, 0);
                        if(sortie.isEmpty())
                            sortie = don.recherche(rentree,temp2,4);
                    }
                } else if (communeT == 0) {
                    if (formationT == 0) {
                        if (rentreeT == 0) {
                            LinkedList<LinkedList<String>> temp = don.recherche(prenom, etab, 3);
                            if(temp.isEmpty())
                                temp = don.recherche(etab,prenom,6);
                            sortie = don.recherche(temp, sexe, 4);
                            if(sortie.isEmpty())
                                sortie = don.recherche(sexe,temp,3);
                        } else {
                            LinkedList<LinkedList<String>> temp = don.recherche(prenom, etab, 3);
                            if(temp.isEmpty())
                                temp = don.recherche(etab,prenom,6);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, sexe, 4);
                            if(temp1.isEmpty())
                                temp1 = don.recherche(sexe,temp,3);
                            sortie = don.recherche(temp1, rentree, 0);
                            if(sortie.isEmpty())
                                sortie = don.recherche(rentree,temp1,3);
                        }
                    } else if (rentreeT == 0) {
                        LinkedList<LinkedList<String>> temp = don.recherche(prenom, sexe, 4);
                        if(temp.isEmpty())
                            temp = don.recherche(sexe,prenom,6);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(etab,temp,4);
                        sortie = don.recherche(temp1, formation, 2);
                        if(sortie.isEmpty())
                            sortie = don.recherche(formation,temp1,4);
                    } else {
                        LinkedList<LinkedList<String>> temp = don.recherche(prenom, sexe, 4);
                        if(temp.isEmpty())
                            temp = don.recherche(sexe,prenom,6);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(etab,temp,4);
                        LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, formation, 2);
                        if(temp2.isEmpty())
                            temp2 = don.recherche(formation,temp1,4);
                        sortie = don.recherche(temp2, rentree, 0);
                        if(sortie.isEmpty())
                            sortie = don.recherche(rentree,temp2,4);
                    }
                } else if (formationT == 0) {
                    if (rentreeT == 0) {
                        LinkedList<LinkedList<String>> temp = don.recherche(prenom, sexe, 4);
                        if(temp.isEmpty())
                            temp = don.recherche(sexe,prenom,6);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(etab,temp,4);
                        sortie = don.recherche(temp1, commune, 1);
                        if(sortie.isEmpty())
                            sortie = don.recherche(commune,temp1,4);
                    } else {
                        LinkedList<LinkedList<String>> temp = don.recherche(prenom, sexe, 4);
                        if(temp.isEmpty())
                            temp = don.recherche(sexe,prenom,6);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(etab,temp,4);
                        LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, commune, 1);
                        if(temp2.isEmpty())
                            temp2 = don.recherche(commune,temp1,4);
                        sortie = don.recherche(temp2, rentree, 0);
                        if(sortie.isEmpty())
                            sortie = don.recherche(rentree,temp2,4);
                    }
                } else if (rentreeT == 0) {
                    LinkedList<LinkedList<String>> temp = don.recherche(prenom, sexe, 4);
                    if(temp.isEmpty())
                        temp = don.recherche(sexe,prenom,6);
                    LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                    if(temp1.isEmpty())
                        temp1 = don.recherche(etab,temp,4);
                    LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, commune, 1);
                    if(temp2.isEmpty())
                        temp2 = don.recherche(commune,temp1,4);
                    sortie = don.recherche(temp2, formation, 2);
                    if(sortie.isEmpty())
                        sortie = don.recherche(formation,temp2,4);
                } else {
                    LinkedList<LinkedList<String>> temp = don.recherche(prenom, sexe, 4);
                    if(temp.isEmpty())
                        temp = don.recherche(sexe,prenom,6);
                    LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                    if(temp1.isEmpty())
                        temp1 = don.recherche(etab,temp,4);
                    LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, commune, 1);
                    if(temp2.isEmpty())
                        temp2 = don.recherche(commune,temp1,4);
                    LinkedList<LinkedList<String>> temp3 = don.recherche(temp2, formation, 2);
                    if(temp3.isEmpty())
                        temp3 = don.recherche(formation,temp2,4);
                    sortie = don.recherche(temp3, rentree, 0);
                    if(sortie.isEmpty())
                        sortie = don.recherche(rentree,temp3,4);
                }
            } else if (prenomT == 0) {
                if (sexeT == 0) {
                    if (etabT == 0) {
                        if (communeT == 0) {
                            if (formationT == 0) {
                                if (rentreeT == 0)
                                    sortie = nom;
                                else {
                                    sortie = don.recherche(nom, rentree, 0);
                                    if(sortie.isEmpty())
                                        sortie = don.recherche(rentree,nom,5);
                                }
                            } else {
                                if (rentreeT == 0) {
                                    sortie = don.recherche(nom, formation, 2);
                                    if(sortie.isEmpty())
                                        sortie = don.recherche(formation,nom,5);
                                } else {
                                    LinkedList<LinkedList<String>> temp = don.recherche(nom, formation, 2);
                                    if(temp.isEmpty())
                                        temp = don.recherche(formation,temp,2);
                                    sortie = don.recherche(temp, rentree, 0);
                                    if(sortie.isEmpty())
                                        sortie = don.recherche(rentree,temp,2);
                                }
                            }
                        } else if (formationT == 0) {
                            if (rentreeT == 0)
                            {
                                sortie = don.recherche(nom, commune, 1);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(commune,nom,5);
                                if(sortie.isEmpty())
                                    sortie = nom;
                            }
                            else {
                                LinkedList<LinkedList<String>> temp = don.recherche(nom, commune, 1);
                                if(temp.isEmpty())
                                    temp = don.recherche(commune,nom,5);
                                sortie = don.recherche(temp, rentree, 0);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(rentree,temp,1);
                            }
                        } else if (rentreeT == 0) {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, commune, 1);
                            if(temp.isEmpty())
                                temp = don.recherche(commune,nom,5);
                            sortie = don.recherche(temp, formation, 2);
                            if(sortie.isEmpty())
                                sortie = don.recherche(formation,temp,1);
                        } else {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, commune, 1);
                            if(temp.isEmpty())
                                temp = don.recherche(commune,nom,5);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, formation, 2);
                            if(temp1.isEmpty())
                                temp1 = don.recherche(formation,temp,1);
                            sortie = don.recherche(temp1, rentree, 0);
                            if(sortie.isEmpty())
                                sortie = don.recherche(rentree,temp1,1);
                        }
                    } else if (communeT == 0) {
                        if (formationT == 0) {
                            if (rentreeT == 0)
                            {
                                sortie = don.recherche(nom, etab, 3);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(etab,nom,5);
                            }
                            else {
                                LinkedList<LinkedList<String>> temp = don.recherche(nom, etab, 3);
                                if(temp.isEmpty())
                                    temp = don.recherche(etab,nom,5);
                                sortie = don.recherche(temp, rentree, 0);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(rentree,temp,3);
                            }
                        } else if (rentreeT == 0) {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, etab, 3);
                            if(temp.isEmpty())
                                temp = don.recherche(etab,nom,5);
                            sortie = don.recherche(temp, formation, 1);
                            if(sortie.isEmpty())
                                sortie = don.recherche(formation,temp,3);
                        } else {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, etab, 3);
                            if(temp.isEmpty())
                                temp = don.recherche(etab,nom,5);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, formation, 2);
                            if(temp1.isEmpty())
                                temp1 = don.recherche(formation,temp,3);
                            sortie = don.recherche(temp1, rentree, 0);
                            if(sortie.isEmpty())
                                sortie = don.recherche(rentree,temp1,3);
                        }
                    } else if (formationT == 0) {
                        if (rentreeT == 0) {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, etab, 3);
                            if(temp.isEmpty())
                                temp = don.recherche(etab,nom,5);
                            sortie = don.recherche(temp, commune, 1);
                            if(sortie.isEmpty())
                                sortie = don.recherche(commune,temp,3);
                        } else {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, etab, 3);
                            if(temp.isEmpty())
                                temp = don.recherche(etab,nom,5);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, commune, 1);
                            if(temp1.isEmpty())
                                temp1 = don.recherche(commune,temp,3);
                            sortie = don.recherche(temp1, rentree, 0);
                            if(sortie.isEmpty())
                                sortie = don.recherche(rentree,temp1,3);
                        }
                    } else if (rentreeT == 0) {

                        LinkedList<LinkedList<String>> temp = don.recherche(nom, etab, 3);
                        if(temp.isEmpty())
                            temp = don.recherche(etab,nom,5);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, commune, 1);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(commune,temp,3);
                        sortie = don.recherche(temp1, formation, 2);
                        if(sortie.isEmpty())
                            sortie = don.recherche(formation,temp1,3);
                    } else {
                        LinkedList<LinkedList<String>> temp = don.recherche(nom, etab, 3);
                        if(temp.isEmpty())
                            temp = don.recherche(etab,nom,5);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, commune, 1);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(commune,temp,3);
                        LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, formation, 2);
                        if(temp2.isEmpty())
                            temp2 = don.recherche(formation,temp1,3);
                        sortie = don.recherche(temp1, rentree, 0);
                        if(sortie.isEmpty())
                            sortie = don.recherche(rentree,temp1,3);
                    }
                } else if (etabT == 0) {
                    if (communeT == 0) {
                        if (formationT == 0) {
                            if (rentreeT == 0)
                                sortie = don.recherche(nom, sexe, 4);
                            if(sortie.isEmpty())
                                sortie = don.recherche(sexe,nom,5);
                            else {
                                LinkedList<LinkedList<String>> temp = don.recherche(nom, sexe, 4);
                                if(temp.isEmpty())
                                    temp = don.recherche(sexe,nom,5);
                                sortie = don.recherche(temp, rentree, 0);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(rentree,temp,4);
                            }
                        } else if (rentreeT == 0) {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, sexe, 4);
                            if(temp.isEmpty())
                                temp = don.recherche(sexe,nom,5);
                            sortie = don.recherche(temp, formation, 2);
                            if(sortie.isEmpty())
                                sortie = don.recherche(formation,temp,4);
                        } else {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, sexe, 4);
                            if(temp.isEmpty())
                                temp = don.recherche(sexe,nom,5);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, formation, 4);
                            if(temp1.isEmpty())
                                temp1 = don.recherche(formation,temp,4);
                            sortie = don.recherche(temp1, rentree, 0);
                            if(sortie.isEmpty())
                                sortie = don.recherche(rentree,temp1,4);
                        }
                    } else if (formationT == 0) {
                        if (rentreeT == 0) {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, sexe, 4);
                            if(temp.isEmpty())
                                temp = don.recherche(sexe,nom,5);
                            sortie = don.recherche(temp, commune, 1);
                            if(sortie.isEmpty())
                                sortie = don.recherche(commune,temp,4);
                        } else {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, sexe, 4);
                            if(temp.isEmpty())
                                temp = don.recherche(sexe,nom,5);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, commune, 1);
                            if (temp1.isEmpty())
                                temp1 = don.recherche(commune,temp,4);
                            sortie = don.recherche(temp1, rentree, 0);
                            if(sortie.isEmpty())
                                sortie = don.recherche(temp1,rentree,4);
                        }
                    } else if (rentreeT == 0) {
                        LinkedList<LinkedList<String>> temp = don.recherche(nom, sexe, 4);
                        if(temp.isEmpty())
                            temp = don.recherche(sexe,nom,5);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, commune, 1);
                        if (temp1.isEmpty())
                            temp1 = don.recherche(commune,temp,4);
                        sortie = don.recherche(temp1, formation, 2);
                        if(sortie.isEmpty())
                            sortie = don.recherche(formation,temp1,4);
                    } else {
                        LinkedList<LinkedList<String>> temp = don.recherche(nom, sexe, 4);
                        if(temp.isEmpty())
                            temp = don.recherche(sexe,nom,5);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, commune, 1);
                        if (temp1.isEmpty())
                            temp1 = don.recherche(commune,temp,4);
                        LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, formation, 2);
                        if(temp2.isEmpty())
                            temp2 = don.recherche(formation,temp1,4);
                        sortie = don.recherche(temp2, rentree, 0);
                        if(sortie.isEmpty())
                            sortie = don.recherche(rentree,temp2,4);
                    }
                } else if (communeT == 0) {
                    if (formationT == 0) {
                        if (rentreeT == 0) {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, sexe, 4);
                            if(temp.isEmpty())
                                temp = don.recherche(sexe,nom,5);
                            sortie = don.recherche(temp, etab, 3);
                            if(sortie.isEmpty())
                                sortie = don.recherche(etab,temp,4);
                        } else {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, sexe, 4);
                            if(temp.isEmpty())
                                temp = don.recherche(sexe,nom,5);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                            if(temp1.isEmpty())
                                temp1 = don.recherche(etab,temp,4);
                            sortie = don.recherche(temp1, rentree, 0);
                            if(sortie.isEmpty())
                                sortie = don.recherche(rentree,temp1,4);
                        }
                    } else if (rentreeT == 0) {
                        LinkedList<LinkedList<String>> temp = don.recherche(nom, sexe, 4);
                        if(temp.isEmpty())
                            temp = don.recherche(sexe,nom,6);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(etab,temp,4);
                        sortie = don.recherche(temp1, formation, 2);
                        if(sortie.isEmpty())
                            sortie = don.recherche(formation,temp1,4);
                    } else {
                        LinkedList<LinkedList<String>> temp = don.recherche(nom, sexe, 4);
                        if(temp.isEmpty())
                            temp = don.recherche(sexe,nom,6);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(etab,temp,4);
                        LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, formation, 2);
                        if(temp2.isEmpty())
                            temp2 = don.recherche(formation,temp1,4);
                        sortie = don.recherche(temp2, rentree, 0);
                        if(sortie.isEmpty())
                            sortie = don.recherche(rentree,temp2,4);
                    }
                } else if (formationT == 0) {
                    if (rentreeT == 0) {
                        LinkedList<LinkedList<String>> temp = don.recherche(nom, sexe, 4);
                        if(temp.isEmpty())
                            temp = don.recherche(sexe,nom,5);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                        sortie = don.recherche(temp1, commune, 1);
                    } else {
                        LinkedList<LinkedList<String>> temp = don.recherche(nom, sexe, 4);
                        if(temp.isEmpty())
                            temp = don.recherche(sexe,nom,5);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(etab,temp,4);
                        LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, commune, 1);
                        if(temp2.isEmpty())
                            temp2 = don.recherche(commune,temp1,4);
                        sortie = don.recherche(temp2, rentree, 0);
                        if(sortie.isEmpty())
                            sortie = don.recherche(rentree,temp2,4);
                    }
                } else if (rentreeT == 0) {
                    LinkedList<LinkedList<String>> temp = don.recherche(nom, sexe, 4);
                    if(temp.isEmpty())
                        temp = don.recherche(sexe,nom,5);
                    LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                    if(temp1.isEmpty())
                        temp1 = don.recherche(etab,temp,4);
                    LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, commune, 1);
                    if(temp2.isEmpty())
                        temp2 = don.recherche(commune,temp1,4);
                    sortie = don.recherche(temp2, formation, 2);
                    if(sortie.isEmpty())
                        sortie = don.recherche(formation,temp2,4);
                } else {
                    LinkedList<LinkedList<String>> temp = don.recherche(nom, sexe, 4);
                    if(temp.isEmpty())
                        temp = don.recherche(sexe,nom,5);
                    LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                    if(temp1.isEmpty())
                        temp1 = don.recherche(etab,temp,4);
                    LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, commune, 1);
                    if(temp2.isEmpty())
                        temp2 = don.recherche(commune,temp1,4);
                    LinkedList<LinkedList<String>> temp3 = don.recherche(temp2, formation, 2);
                    if(temp2.isEmpty())
                        temp2 = don.recherche(formation,temp2,4);
                    sortie = don.recherche(temp3, rentree, 0);
                    if(sortie.isEmpty())
                        sortie = don.recherche(rentree,temp3,4);
                }
            } else if (sexeT == 0) {
                if (etabT == 0) {
                    if (communeT == 0) {
                        if (formationT == 0) {
                            if (rentreeT == 0) {
                                System.out.println(1);
                                sortie = don.recherche(nom, prenom, 6);
                                if(sortie.isEmpty())
                                    sortie = don.recherche(prenom,nom,5);
                                if(sortie.isEmpty())
                                    sortie = nom;
                            }
                            else {
                                LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                                if(temp.isEmpty())
                                    temp = don.recherche(prenom,nom,5);
                                sortie = don.recherche(temp, rentree, 0);
                               if(sortie.isEmpty())
                                   sortie = don.recherche(rentree,temp,5);
                            }
                        } else if (rentreeT == 0) {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                            if(temp.isEmpty())
                                temp = don.recherche(prenom,nom,5);
                            sortie = don.recherche(temp, formation, 2);
                            if(sortie.isEmpty())
                                sortie = don.recherche(formation,temp,5);
                        } else {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                            if(temp.isEmpty())
                                temp = don.recherche(prenom,nom,5);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, formation, 2);
                            if(temp1.isEmpty())
                                temp1 = don.recherche(formation,temp,5);
                            sortie = don.recherche(temp1, rentree, 0);
                            if(sortie.isEmpty())
                                sortie = don.recherche(rentree,temp1,5);
                        }
                    } else if (formationT == 0) {
                        if (rentreeT == 0) {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                            if(temp.isEmpty())
                                temp = don.recherche(prenom,nom,5);
                            sortie = don.recherche(temp, commune, 1);
                           if(sortie.isEmpty())
                               sortie = don.recherche(commune,temp,5);
                           if(sortie.isEmpty())
                               sortie = prenom;
                        } else {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                            if(temp.isEmpty())
                                temp = don.recherche(prenom,nom,5);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, commune, 1);
                            if(temp1.isEmpty())
                                temp1 = don.recherche(commune,temp,5);
                            sortie = don.recherche(temp1, rentree, 0);
                            if(sortie.isEmpty())
                                sortie = don.recherche(rentree,temp1,5);

                        }
                    } else if (rentreeT == 0) {
                        LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                        if(temp.isEmpty())
                            temp = don.recherche(prenom,nom,5);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, commune, 1);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(commune,temp,5);
                        sortie = don.recherche(temp1, formation, 2);
                        if(sortie.isEmpty())
                            sortie = don.recherche(formation,temp1,5);
                    } else {
                        LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                        if(temp.isEmpty())
                        {
                            temp = don.recherche(prenom,nom,5);
                        }
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, commune, 1);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(commune,temp,5);
                        LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, formation, 2);
                        if(temp2.isEmpty())
                            temp2 = don.recherche(formation,temp1,5);
                        sortie = don.recherche(temp2, rentree, 0);
                        if(sortie.isEmpty())
                            sortie = don.recherche(rentree,temp2,5);
                    }
                } else if (communeT == 0) {
                    if (formationT == 0) {
                        if (rentreeT == 0) {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                            if(temp.isEmpty())
                                temp = don.recherche(prenom,nom,5);
                            sortie = don.recherche(temp, etab, 3);
                            if(sortie.isEmpty())
                                sortie = don.recherche(etab,temp,5);
                        } else {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                            if(temp.isEmpty())
                                temp = don.recherche(prenom,nom,5);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                            if(temp1.isEmpty())
                                temp1 = don.recherche(etab,temp,5);
                            sortie = don.recherche(temp1, rentree, 0);
                            if(sortie.isEmpty())
                                sortie = don.recherche(rentree,temp1,5);
                        }
                    } else {
                        if (rentreeT == 0) {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                            if(temp.isEmpty())
                                temp = don.recherche(prenom,nom,5);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                            if(temp1.isEmpty())
                                temp1 = don.recherche(etab,temp,5);
                            sortie = don.recherche(temp1, formation, 1);
                            if(sortie.isEmpty())
                                sortie = don.recherche(formation,temp1,5);
                        } else {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                            if(temp.isEmpty())
                                temp = don.recherche(prenom,nom,5);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                            if(temp1.isEmpty())
                                temp1 = don.recherche(etab,temp,5);
                            LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, formation, 1);
                            if(temp2.isEmpty())
                                temp2 = don.recherche(formation,temp1,5);
                            sortie = don.recherche(temp2, rentree, 0);
                            if(sortie.isEmpty())
                                sortie = don.recherche(rentree,temp2,5);
                        }
                    }
                } else if (formationT == 0) {
                    if (rentreeT == 0) {
                        LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                        if(temp.isEmpty())
                            temp = don.recherche(prenom,nom,5);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                        sortie = don.recherche(temp1, commune, 1);
                    } else {
                        LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                        if(temp.isEmpty())
                            temp = don.recherche(prenom,nom,5);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(etab,temp,5);
                        LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, commune, 1);
                        if(temp2.isEmpty())
                            temp2 = don.recherche(commune,temp1,5);
                        sortie = don.recherche(temp2, rentree, 0);
                        if(sortie.isEmpty())
                            sortie = don.recherche(rentree,temp2,5);
                    }
                } else if (rentreeT == 0) {
                    LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                    if(temp.isEmpty())
                        temp = don.recherche(prenom,nom,5);
                    LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                    if(temp1.isEmpty())
                        temp1 = don.recherche(etab,temp,5);
                    LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, commune, 1);
                    if(temp2.isEmpty())
                        temp2 = don.recherche(commune,temp1,5);
                    sortie = don.recherche(temp2, formation, 2);
                    if(sortie.isEmpty())
                        sortie = don.recherche(formation,temp2,5);
                } else {
                    LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                    if(temp.isEmpty())
                        temp = don.recherche(prenom,nom,5);
                    LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                    if(temp1.isEmpty())
                        temp1 = don.recherche(etab,temp,5);
                    LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, commune, 1);
                    if(temp2.isEmpty())
                        temp2 = don.recherche(commune,temp1,5);
                    LinkedList<LinkedList<String>> temp3 = don.recherche(temp2, formation, 2);
                    if(temp3.isEmpty())
                        temp3 = don.recherche(formation,temp2,5);
                    sortie = don.recherche(temp3, rentree, 0);
                    if(sortie.isEmpty())
                        sortie = don.recherche(rentree,temp3,5);
                }
            } else if (etabT == 0) {
                if (communeT == 0) {
                    if (formationT == 0) {
                        if (rentreeT == 0) {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                            if(temp.isEmpty())
                                temp = don.recherche(prenom,nom,5);
                            sortie = don.recherche(temp, sexe, 4);
                            if(sortie.isEmpty())
                                sortie = don.recherche(sexe,temp,5);
                        } else {
                            LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                            if(temp.isEmpty())
                                temp = don.recherche(prenom,nom,5);
                            LinkedList<LinkedList<String>> temp1 = don.recherche(temp, sexe, 4);
                            if(temp1.isEmpty())
                                temp1 = don.recherche(sexe, temp,5);
                            sortie = don.recherche(temp1, rentree, 0);
                            if(sortie.isEmpty())
                                sortie = don.recherche(rentree,temp1,5);
                        }
                    } else if (rentreeT == 0) {
                        LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                        if(temp.isEmpty())
                            temp = don.recherche(prenom,nom,5);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, sexe, 4);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(sexe,temp,5);
                        sortie = don.recherche(temp1, formation, 2);
                        if(sortie.isEmpty())
                            sortie = don.recherche(formation,temp1,5);
                    } else {
                        LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                        if(temp.isEmpty())
                            temp = don.recherche(prenom,nom,5);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, sexe, 4);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(sexe,temp1,5);
                        LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, formation, 2);
                        if(temp2.isEmpty())
                            temp2 = don.recherche(formation,temp2,5);
                        sortie = don.recherche(temp2, rentree, 0);
                        if(sortie.isEmpty())
                            sortie = don.recherche(rentree,temp2,5);
                    }
                } else if (formationT == 0) {
                    if (rentreeT == 0) {
                        LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                        if(temp.isEmpty())
                            temp = don.recherche(prenom,nom,5);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, commune, 1);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(commune,temp,5);
                        sortie = don.recherche(temp1, sexe, 4);
                        if(sortie.isEmpty())
                            sortie = don.recherche(sexe,temp1,5);
                    } else {
                        LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                        if(temp.isEmpty())
                            temp = don.recherche(prenom,nom,5);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, sexe, 4);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(sexe,temp,5);
                        LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, commune, 1);
                        if(temp2.isEmpty())
                            temp2 = don.recherche(commune, temp1, 5);
                        sortie = don.recherche(temp2, rentree, 0);
                        if(sortie.isEmpty())
                            sortie = don.recherche(rentree, temp2,5);
                    }
                } else if (rentreeT == 0) {
                    LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                    if(temp.isEmpty())
                        temp = don.recherche(prenom,nom,5);
                    LinkedList<LinkedList<String>> temp1 = don.recherche(temp, sexe, 4);
                    if(temp1.isEmpty())
                        temp1 = don.recherche(sexe,temp,5);
                    LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, commune, 1);
                    if(temp2.isEmpty())
                        temp2 = don.recherche(commune,temp1,5);
                    sortie = don.recherche(temp2, formation, 2);
                    if(sortie.isEmpty())
                        sortie = don.recherche(formation,temp2,5);
                } else {
                    LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                    if(temp.isEmpty())
                        temp = don.recherche(prenom,nom,5);
                    LinkedList<LinkedList<String>> temp1 = don.recherche(temp, sexe, 4);
                    if(temp1.isEmpty())
                        temp1 = don.recherche(sexe,temp,5);
                    LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, commune, 1);
                    if(temp2.isEmpty())
                        temp2 = don.recherche(commune,temp1,5);
                    LinkedList<LinkedList<String>> temp3 = don.recherche(temp2, formation, 2);
                    if(temp3.isEmpty())
                        temp3 = don.recherche(formation,temp2,5);
                    sortie = don.recherche(temp3, rentree, 0);
                    if(sortie.isEmpty())
                        sortie = don.recherche(rentree,temp3,5);
                }
            } else if (communeT == 0) {
                if (formationT == 0) {
                    if (rentreeT == 0) {
                        LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                        if(temp.isEmpty())
                            temp = don.recherche(prenom,nom,5);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(etab,temp,5);
                        sortie = don.recherche(temp1, sexe, 4);
                        if(sortie.isEmpty())
                            sortie = don.recherche(sexe,temp1,5);
                    } else {
                        LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                        if(temp.isEmpty())
                            temp = don.recherche(prenom,nom,5);
                        LinkedList<LinkedList<String>> temp1 = don.recherche(temp, etab, 3);
                        if(temp1.isEmpty())
                            temp1 = don.recherche(etab,temp,5);
                        LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, sexe, 4);
                        if(temp2.isEmpty())
                            temp2 = don.recherche(sexe,temp1,5);
                        sortie = don.recherche(temp2, rentree, 0);
                        if(sortie.isEmpty())
                            sortie = don.recherche(rentree,temp2,5);
                    }
                } else if (rentreeT == 0) {
                    LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                    if(temp.isEmpty())
                        temp = don.recherche(prenom,nom,5);
                    LinkedList<LinkedList<String>> temp1 = don.recherche(temp, sexe, 5);
                    if(temp1.isEmpty())
                        temp1 = don.recherche(sexe,temp,5);
                    LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, etab, 3);
                    if(temp2.isEmpty())
                        temp2 = don.recherche(etab,temp1,5);
                    sortie = don.recherche(temp2, formation, 2);
                    if(sortie.isEmpty())
                        sortie = don.recherche(formation,temp2,5);
                } else {
                    LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                    if(temp.isEmpty())
                        temp = don.recherche(prenom,nom,5);
                    LinkedList<LinkedList<String>> temp1 = don.recherche(temp, sexe, 4);
                    if(temp1.isEmpty())
                        temp1 = don.recherche(sexe,temp,5);
                    LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, etab, 3);
                    if(temp2.isEmpty())
                        temp2 = don.recherche(etab,temp1,5);
                    LinkedList<LinkedList<String>> temp3 = don.recherche(temp2, formation, 2);
                    if(temp3.isEmpty())
                        temp3 = don.recherche(formation,temp2,5);
                    sortie = don.recherche(temp3, rentree, 0);
                    if(sortie.isEmpty())
                        sortie = don.recherche(rentree,temp3,5);
                }
            } else if (formationT == 0) {
                if (rentreeT == 0) {
                    LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                    if(temp.isEmpty())
                        temp = don.recherche(prenom,nom,5);
                    LinkedList<LinkedList<String>> temp1 = don.recherche(temp, sexe, 4);
                    if(temp1.isEmpty())
                        temp1 = don.recherche(sexe,temp,5);
                    LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, etab, 3);
                    if(temp2.isEmpty())
                        temp2 = don.recherche(etab,temp1,5);
                    sortie = don.recherche(temp2, commune, 1);
                    if(sortie.isEmpty())
                        sortie = don.recherche(commune,temp2,5);
                } else {
                    LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                    if(temp.isEmpty())
                        temp = don.recherche(prenom,nom,5);
                    LinkedList<LinkedList<String>> temp1 = don.recherche(temp, sexe, 4);
                    if(temp1.isEmpty())
                        temp1 = don.recherche(sexe,temp,5);
                    LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, etab, 3);
                    if(temp2.isEmpty())
                        temp2 = don.recherche(etab,temp1,5);
                    LinkedList<LinkedList<String>> temp3 = don.recherche(temp2, commune, 1);
                    if(temp3.isEmpty())
                        temp3 = don.recherche(commune,temp2,5);
                    sortie = don.recherche(temp3, rentree, 0);
                    if(sortie.isEmpty())
                        sortie = don.recherche(rentree,temp3,5);
                }
            } else if (rentreeT == 0) {
                LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                if(temp.isEmpty())
                    temp = don.recherche(prenom,nom,5);
                LinkedList<LinkedList<String>> temp1 = don.recherche(temp, sexe, 4);
                if(temp1.isEmpty())
                    temp1 = don.recherche(sexe,temp,5);
                LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, etab, 3);
                if(temp2.isEmpty())
                    temp2 = don.recherche(etab,temp1,5);
                LinkedList<LinkedList<String>> temp3 = don.recherche(temp2, commune, 1);
                if(temp3.isEmpty())
                    temp3 = don.recherche(commune,temp2,5);
                sortie = don.recherche(temp3, formation, 2);
                if(sortie.isEmpty())
                    sortie = don.recherche(formation,temp3,5);
            } else {
                LinkedList<LinkedList<String>> temp = don.recherche(nom, prenom, 6);
                if(temp.isEmpty())
                    temp = don.recherche(prenom,nom,5);
                LinkedList<LinkedList<String>> temp1 = don.recherche(temp, sexe, 4);
                if(temp1.isEmpty())
                    temp1 = don.recherche(sexe,temp,5);
                LinkedList<LinkedList<String>> temp2 = don.recherche(temp1, etab, 3);
                if(temp2.isEmpty())
                    temp2 = don.recherche(etab,temp1,5);
                LinkedList<LinkedList<String>> temp3 = don.recherche(temp2, commune, 1);
                if(temp3.isEmpty())
                    temp3 = don.recherche(commune,temp2,5);
                LinkedList<LinkedList<String>> temp4 = don.recherche(temp3, formation, 2);
                if(temp4.isEmpty())
                    temp4 = don.recherche(formation,temp3,5);
                sortie = don.recherche(temp4, rentree, 0);
                if(sortie.isEmpty())
                    temp = don.recherche(rentree,temp4,5);
            }
        }
        labelRecherche.setText(nomT.getText() + " a " + String.valueOf(sortie.size()) + " résultats");
        return sortie;
    }

    public LinkedList<LinkedList<String>> recherche1(Label recherche) {
        LinkedList<LinkedList<String>> nom1 = nomRecher();
        LinkedList<LinkedList<String>> prenom1 = recherche(6);
        System.out.println(prenom1);
        LinkedList<LinkedList<String>> sexe1 = sexeRecher();
        LinkedList<LinkedList<String>> etab1 = etabRecher();
        LinkedList<LinkedList<String>> formation1 = formationRecher();
        LinkedList<LinkedList<String>> commune1 = communeRecher();
        LinkedList<LinkedList<String>> rentree1 = rentreeRecher();
        int nomT1 = nom1.size();
        int prenomT1 = prenom1.size();
        int sexeT1 = sexe1.size();
        int etabT1 = etab1.size();
        int communeT1 = commune1.size();
        int formationT1 = formation1.size();
        int rentreeT1 = rentree1.size();
        LinkedList<LinkedList<String>> sortie = new LinkedList<>();

        if (nomT1 == 0) {
            if (prenomT1 == 0) {
                if (sexeT1 == 0) {
                    if (etabT1 == 0) {
                        if (communeT1 == 0) {
                            if (formationT1 == 0)
                            {
                                sortie = rentree1;
                            }
                            else if (rentreeT1 == 0)
                            {
                                sortie = formation1;
                            }
                        } else if (rentreeT1 == 0 && formationT1 == 0) {
                            {
                                sortie = commune1;
                            }
                        }
                    } else if (rentreeT1 == 0 && formationT1 == 0 && communeT1 == 0)
                    {
                        sortie = etab1;
                    }
                } else if (rentreeT1 == 0 && formationT1 == 0 && etabT1 == 0 && communeT1 == 0)
                {
                    sortie = sexe1;
                }
            } else if (rentreeT1 == 0 && formationT1 == 0 && etabT1 == 0 && communeT1 == 0 && sexeT1 == 0)
            {
                sortie = prenom1;
            }
        } else if (rentreeT1 == 0 && formationT1 == 0 && etabT1 == 0 && communeT1 == 0 && sexeT1 == 0 && prenomT1 == 0)
        {
            sortie = nom1;
        }
        return sortie;
    }

    public void actualiser() {
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        LinkedList<LinkedList<String>> listeComp = don.listeComplet(chemin);
        opeTab.showListe(listeComp, tableV, idTable, nomTable, prenomTable, sexeTable, formationTable, etabTable, communeTable, anneeTable);
    }

    @FXML
    void tableAction(ActionEvent event) {
        if (event.getSource() == supprimerBt) {
            int a = tableV.getSelectionModel().selectedIndexProperty().get();
            if(!(tableV.getSelectionModel().isEmpty()))
                ope.supprimer(a);
            Donnees don = new Donnees();
            String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
            LinkedList<LinkedList<String>> listeComp = don.listeComplet(chemin);
            opeTab.showListe(listeComp, tableV, idTable, nomTable, prenomTable, sexeTable, formationTable, etabTable, communeTable, anneeTable);
            tableV.getSelectionModel().select(a);
        }
        if (event.getSource() == modifierBt) {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            int a = tableV.getSelectionModel().selectedIndexProperty().get();
            Etudiant etudiant = tableV.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("Modifier.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ModifierController ajout = loader.getController();
            ajout.setTextField(String.valueOf(a), etudiant.getNom(), etudiant.getPrenom(), etudiant.getSexe(), etudiant.getFormation(), etudiant.getEtab(), etudiant.getCommune(), etudiant.getAnnee());
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Modification ");
            Scene scene = new Scene(parent);
            scene.getStylesheets().add(String.valueOf(MainApplication.class.getResource("styleAjout.css")));
            stage.setScene(scene);
            stage.show();
        }
        if(event.getSource()==actualiserBt)
        {
            actualiser();
        }
        if(event.getSource()==ajoutBT)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("Ajout.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ajout ");

            Scene scene = null;
            try {
                scene = new Scene(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            scene.getStylesheets().add(String.valueOf(MainApplication.class.getResource("styleAjout.css")));
            stage.setScene(scene);
            stage.show();



        }
    }


    @FXML
    void rechercheAction(ActionEvent event) {

        LinkedList<LinkedList<String>> test = rechercheAB(labelRecherche);
        opeTab.showListe(test, tableV, idTable, nomTable, prenomTable, sexeTable, formationTable, etabTable, communeTable, anneeTable);
    }
    public void select(int id)
    {
        tableV.getSelectionModel().select(id);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        LinkedList<LinkedList<String>> listeComp = don.listeComplet(chemin);
        opeTab.showListe(listeComp, tableV, idTable, nomTable, prenomTable, sexeTable, formationTable, etabTable, communeTable, anneeTable);

    }
}