package modeles;

import java.util.Date;

public class Personne {

  int id_personne;
  String nom;
  String prenom;
  Date date_naissance;
  String email;
  String visibilite;

  /*
   * Setter
   */
  public void setId_personne(int id_personne) {
    this.id_personne = id_personne;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public void setDate_naissance(Date date_naissance) {
    this.date_naissance = date_naissance;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setVisibilite(String visibilite) {
    this.visibilite = visibilite;
  }

  /*
   * Getter
   */
  public int getId_personne() {
    return this.id_personne;
  }

  public String getNom() {
    return this.nom;
  }

  public String getPrenom() {
    return this.prenom;
  }

  public Date getDate_naissance() {
    return this.date_naissance;
  }

  public String getEmail() {
    return this.email = email;
  }

  public String getVisibilite() {
    return this.visibilite;
  }
}
