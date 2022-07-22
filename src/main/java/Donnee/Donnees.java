package Donnee;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;


public class Donnees
{
     public LinkedList<LinkedList<String>> listeComplet(String url)
    {

        LinkedList<LinkedList<String>> listeRetour = new LinkedList<>();
        try {
            InputStream ips = new FileInputStream(url);
            InputStreamReader ipsr = new InputStreamReader(ips, StandardCharsets.ISO_8859_1);
            BufferedReader br = new BufferedReader(ipsr);
            String str;
            int j =0;
            while ((str = br.readLine()) != null)
            {
                int i = 0;
                String a = "";
                int k = 0;

                LinkedList<String>list = new LinkedList<>();

                while (str.length()>i)
                {

                    if(str.charAt(i)==';')
                    {

                        a = str.substring(k,i);
                        k = i+1;
                        list.add(a);

                    }
                    if(i==str.length()-1) {
                        a = str.substring(k, i + 1);
                        list.add(a);
                    }
                    i++;

                }
                listeRetour.add(list);

            }
        } catch (FileNotFoundException e) {
            System.out.println("Apps::FileNotFoundException::"+e.getMessage());
        } catch (IOException e) {
            System.out.println("Apps::IOException::"+e.getMessage());
        }
        return listeRetour;
    }

    public LinkedList<String> rentree(LinkedList<LinkedList<String>> liste)
    {
        LinkedList<String>rentreeRetour = new LinkedList<>();
        int p = 1;
        while(liste.size()>p)
        {
            String temp = liste.get(p).get(0);
            String temp2 = liste.get(p-1).get(0);
            if(temp.charAt(0)==' ')
            {
                temp = temp.substring(1,temp.length());
            }
            if(temp2.charAt(0)==' ')
            {
                temp2 = temp2.substring(1,temp2.length());
            }
                rentreeRetour.add(liste.get(p).get(0));
            p++;
        }
        return rentreeRetour;
    }

    static LinkedList<String> supprimerDoublon(LinkedList<String>test)
    {
        Object[] st = test.toArray();
        for (Object s : st) {
            if (test.indexOf(s) != test.lastIndexOf(s)) {
                test.remove(test.lastIndexOf(s));
            }
        }
        return test;
    }

    public LinkedList<String> commune(LinkedList<LinkedList<String>>liste)
    {
        LinkedList<String>communeRetour = new LinkedList<>();
        int p = 1;
        while(liste.size()>p)
        {
            String temp = liste.get(p).get(1);
            String temp2 = liste.get(p-1).get(1);
            boolean bool = temp.equals(temp2);
            if(temp.charAt(0)==' ')
            {
                temp = temp.substring(1,temp.length());
            }
            if(temp2.charAt(0)==' ')
            {
                temp2 = temp2.substring(1,temp2.length());
            }
            if(!bool) {

                communeRetour.add(liste.get(p).get(1));

            }
            p++;
        }
        communeRetour = supprimerDoublon(communeRetour);
        return communeRetour;
    }

    public LinkedList<String> formation(LinkedList<LinkedList<String>>liste)
    {
        LinkedList<String>formationRetour = new LinkedList<>();
        int p = 1;
        while(liste.size()>p)
        {
            String temp = liste.get(p).get(2);
            String temp2 = liste.get(p-1).get(2);

            if(temp.charAt(0)==' ')
            {
                temp = temp.substring(1,temp.length());
            }
            if(temp2.charAt(0)==' ')
            {
                temp2 = temp2.substring(1,temp2.length());
            }
                formationRetour.add(liste.get(p).get(2));
            p++;
        }
        supprimerDoublon(formationRetour);
        return formationRetour;
    }

    public LinkedList<String> nom (LinkedList<LinkedList<String>>liste)
    {
        LinkedList<String>nomRetour = new LinkedList<>();
        int p = 1;
        while(liste.size()>p)
        {
            nomRetour.add(liste.get(p).get(5));
            p++;
        }
        return nomRetour;
    }

    public LinkedList<String> prenom (LinkedList<LinkedList<String>>liste)
    {
        LinkedList<String>prenomRetour = new LinkedList<>();
        int p = 1;
        while(liste.size()>p)
        {
            prenomRetour.add(liste.get(p).get(6));
            p++;
        }
        return prenomRetour;
    }

    public LinkedList<String> sexe (LinkedList<LinkedList<String>>liste)
    {
        LinkedList<String>sexeRetour = new LinkedList<>();
        int p = 1;
        while(liste.size()>p)
        {
            String temp = liste.get(p).get(4);
            String temp2 = liste.get(p-1).get(4);

            if(temp.charAt(0)==' ')
            {
                temp = temp.substring(1,temp.length());
            }
            if(temp2.charAt(0)==' ')
            {
                temp2 = temp2.substring(1,temp2.length());
            }
            sexeRetour.add(liste.get(p).get(4));
            p++;
        }
        supprimerDoublon(sexeRetour);
        return sexeRetour;
    }

    public LinkedList<String> etablissement (LinkedList<LinkedList<String>>liste)
    {
        LinkedList<String>etabRetour = new LinkedList<>();
        int p = 1;
        while(liste.size()>p)
        {
            String temp = liste.get(p).get(3);
            String temp2 = liste.get(p-1).get(3);

            if(temp.charAt(0)==' ')
            {
                temp = temp.substring(1,temp.length());
            }
            if(temp2.charAt(0)==' ')
            {
                temp2 = temp2.substring(1,temp2.length());
            }
            etabRetour.add(liste.get(p).get(3));
            p++;
        }
        supprimerDoublon(etabRetour);
        return etabRetour;
    }
    public LinkedList<LinkedList<String>> parametreListe(LinkedList<LinkedList<String>>test, String nom,int colonne)
    {
        LinkedList<LinkedList<String>>retour = new LinkedList<>();

        int i = 0;
        while(test.size()>i)
        {

            if(test.get(i).get(colonne).contains(nom))
            {
                retour.add(test.get(i));
            }
            i++;
        }
        return retour;
    }

    public LinkedList<LinkedList<String>> recherche (LinkedList<LinkedList<String>> test, LinkedList<LinkedList<String>> test2, int col2)
    {
        LinkedList<LinkedList<String>> sortie = new LinkedList<>();
        Donnees don = new Donnees();
            int j=0;
            while(j<test2.size())
            {
                LinkedList<LinkedList<String>> retour = don.parametreListe(test,test2.get(j).get(col2),col2);
                sortie = retour;
               j++;
            }
        return sortie;
    }

}
