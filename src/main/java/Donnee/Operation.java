package Donnee;

import com.example.projet.MainApplication;
import com.example.projet.ModifierController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.LinkedList;

public class Operation {
    public LinkedList<String> ajouter(LinkedList<LinkedList<String>> test, String nom, String prenom, String commune, String rentree, String sexe, String etablissement, String formation) {
        LinkedList<String> retour = new LinkedList<>();
        retour.add(rentree);
        retour.add(commune);
        retour.add(formation);
        retour.add(etablissement);
        retour.add(sexe);
        retour.add(nom);
        retour.add(prenom);
        return retour;
    }

    public void ecrireCsvAjout(LinkedList<String> test, String url)
    {
        try {
            FileWriter file = new FileWriter(url, true);
            BufferedWriter bwr = new BufferedWriter(file);
            PrintWriter ajout = new PrintWriter(bwr);
            int i = 0;
            for (String s : test) {
                ajout.print(s);
                if (i < test.size() - 1)
                    ajout.print(";");

                i++;
            }

            ajout.println();
            ajout.close();
        } catch (FileNotFoundException e) {
            System.out.println("ecrireCsv::FileNotFoundException::" + e.getMessage());
        } catch (IOException e) {
            System.out.println("ecrireCsv::IOException::" + e.getMessage());
        }
    }

    public void ecrireCsv(LinkedList<String> test, String url)
    {
        try {
            FileWriter file = new FileWriter(url);
            BufferedWriter bwr = new BufferedWriter(file);
            PrintWriter ajout = new PrintWriter(bwr);
            int i = 0;
            for (String s : test) {
                ajout.print(s);
                if (i < test.size() - 1)
                    ajout.print(";");
                i++;
            }
            ajout.println();
            ajout.close();
        } catch (FileNotFoundException e) {
            System.out.println("ecrireCsv::FileNotFoundException::" + e.getMessage());
        } catch (IOException e) {
            System.out.println("ecrireCsv::IOException::" + e.getMessage());
        }
    }

    public LinkedList<String> rechercheId(LinkedList<LinkedList<String>> list, String id)
    {
        LinkedList<String> retour = new LinkedList<>();
        int i = 1;
        while (list.size() > i) {
            retour = list.get(Integer.parseInt(id));
            i++;
        }
        return retour;
    }

    public LinkedList<String> recherche(LinkedList<String> test, String nom)
    {
        LinkedList<String> retour = new LinkedList<>();
        do {
            for (String a : test) {
                if (a.contains(nom) && a.charAt(0) == nom.charAt(0) && !(retour.contains(a))) {
                    retour.add(a);

                }
                if (test.indexOf(a) == test.size() - 1) {
                    nom = nom.substring(0, nom.length() - 1);
                }
            }
        } while (nom.length() > 0);
        return retour;
    }

    public LinkedList<LinkedList<String>> rechercheList(LinkedList<LinkedList<String>> test, String nomRecherche) {
        Donnees don = new Donnees();
        LinkedList<LinkedList<String>> retour = new LinkedList<>();
        LinkedList<String> liste = don.nom(test);
        LinkedList<String> liste2 = recherche(liste, nomRecherche);

        int i = 0;
        while (liste2.size() > i) {
            String a = liste2.get(i);
            int j = 0;
            while (test.size() > j) {

                if (test.get(j).get(6).contains(a)) {
                    retour.add(test.get(j));
                    //System.out.println(test.get(j));
                }
                j++;
            }
            i++;
        }
        return retour;
    }

    public void supprimer(int id)
    {
        Donnees don = new Donnees();
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        LinkedList<LinkedList<String>> liste = don.listeComplet(chemin);
        liste.remove(id);

        int j = 0;
        while (j < liste.size()) {
            if (j == 0) {
                ecrireCsv(liste.get(j), chemin);
            } else {
                ecrireCsvAjout(liste.get(j), chemin);
            }

            j++;
        }
    }

    public void modifier(int id, String nom, String prenom, String formation, String annee, String commune, String sexe, String etab)
    {
        Donnees don = new Donnees();
        String chemin = "C:\\Users\\InclusivAcademy6\\Documents\\projet1\\donnee.csv";
        LinkedList<LinkedList<String>> liste = don.listeComplet(chemin);
        liste.remove(id);
        LinkedList<String> temp = new LinkedList<>();
        temp.add(annee);
        temp.add(commune);
        temp.add(formation);
        temp.add(etab);
        temp.add(sexe);
        temp.add(nom);
        temp.add(prenom);
        liste.add(id, temp);
        int j = 0;
        while (j < liste.size()) {
            if (j == 0) {
                ecrireCsv(liste.get(j), chemin);
            } else {
                ecrireCsvAjout(liste.get(j), chemin);
            }

            j++;
        }
    }
    }
