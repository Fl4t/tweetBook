package modeles;

import java.util.Date;

public class Actualite {

  private String nom;
  private String prenom;
  private Date date_ajout;
  private String contenu;

  public Actualite() {}

  public Actualite(String nom, String prenom, Date date_ajout, String contenu) {
    this.nom = nom;
    this.prenom = prenom;
    this.date_ajout = date_ajout;
    this.contenu = contenu;
  }

  /*
   * Setter
   */
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
