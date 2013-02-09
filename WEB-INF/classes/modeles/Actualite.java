package modeles;

import java.util.Date;

public class Actualite {

  private int id_personne;
  private int id_actualite;
  private int nbLike;
  private int nbUnlike;
  private int notation;
  private String nom;
  private String type_actu;
  private String prenom;
  private Date date_ajout;
  private String contenu;

  public Actualite() {}

  public Actualite(
      int id_actualite,
      int id_personne,
      String type_actu,
      String nom,
      String prenom,
      Date date_ajout,
      String contenu)
  {
    this.id_actualite = id_actualite;
    this.id_personne = id_personne;
    this.type_actu = type_actu;
    this.nom = nom;
    this.prenom = prenom;
    this.date_ajout = date_ajout;
    this.contenu = contenu;
  }

  /*
   * Setter
   */
  public void setId_actualite(int id_actualite) {
    this.id_actualite = id_actualite;
  }

  public void setId_personne(int id_personne) {
    this.id_personne = id_personne;
  }

  public void setNbLike(int nbLike) {
    this.nbLike = nbLike;
  }

  public void setNbUnlike(int nbUnlike) {
    this.nbUnlike = nbUnlike;
  }

  public void setNotation(int notation) {
    this.notation = notation;
  }

  public void setType_actu(String type_actu) {
    this.type_actu = type_actu;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public void setDate_ajout(Date date_ajout) {
    this.date_ajout = date_ajout;
  }

  public void setContenu(String contenu) {
    this.contenu = contenu;
  }

  /*
   * Getter
   */
  public int getId_actualite() {
    return this.id_actualite;
  }

  public int getId_personne() {
    return this.id_personne;
  }

  public int getNbLike() {
    return this.nbLike;
  }

  public int getNbUnlike() {
    return this.nbUnlike;
  }

  public int getNotation() {
    return this.notation;
  }

  public String getType_actu() {
    return this.type_actu;
  }

  public String getNom() {
    return this.nom;
  }

  public String getPrenom() {
    return this.prenom;
  }

  public Date getDate_ajout() {
    return this.date_ajout;
  }

  public String getContenu() {
    return this.contenu;
  }
}
