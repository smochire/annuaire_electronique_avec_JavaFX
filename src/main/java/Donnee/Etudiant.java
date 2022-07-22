package Donnee;

public class Etudiant {
    private String id;
    private String nom;
    private String prenom;
    private String annee;
    private String commune;
    private String formation;
    private String sexe;
    private String etab;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getEtab() {
        return etab;
    }

    public void setEtab(String etab) {
        this.etab = etab;
    }

   public Etudiant(String id,String nom,String prenom, String sexe,String formation,String etab, String commune,String annee)
   {
       this.annee = annee;
       this.id = id;
       this.nom = nom;
       this.prenom = prenom;
       this.etab = etab;
       this.sexe = sexe;
       this.formation = formation;
       this.commune = commune;
   }
}
