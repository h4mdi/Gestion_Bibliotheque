package Models;

public class Livre {
//    Un livre est
//    caractérisé par un identifiant, un libellé, un type, une catégorie, un auteur, une maison
//    d’édition et un nombre d’exemplaire
    private int id;
    private String nom;
    private String libelle;
    private String type;
    private String categorie;
    private String auteur;
    private String maison_ed;
    private int nb_exemp;

    public Livre(int id, String nom, String libelle, String type, String categorie, String auteur, String maison_ed, int nb_exemp) {
        this.id = id;
        this.nom = nom;
        this.libelle = libelle;
        this.type = type;
        this.categorie = categorie;
        this.auteur = auteur;
        this.maison_ed = maison_ed;
        this.nb_exemp = nb_exemp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategadorie(String categorie) {
        this.categorie = categorie;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getMaison_ed() {
        return maison_ed;
    }

    public void setMaison_ed(String maison_ed) {
        this.maison_ed = maison_ed;
    }

    public int getNb_exemp() {
        return nb_exemp;
    }

    public void setNb_exemp(int nb_exemp) {
        this.nb_exemp = nb_exemp;
    }

    public Livre() {
    }
}
