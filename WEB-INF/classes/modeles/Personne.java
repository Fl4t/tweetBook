package modeles;

public class Personne {

  private int id_personne;
  private String nom;
  private String prenom;
  private String date_naissance;
  private String email;
  private String visibilite;

  public Personne() {}

  public Personne(String nom,
    String prenom, String date_naissance,
    String email, String visibilite)
  {
    this.nom = nom;
    this.prenom = prenom;
    this.date_naissance = date_naissance;
    this.email = email;
    this.visibilite = visibilite;
  }

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

  public void setDate_naissance(String date_naissance) {
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

  public String getDate_naissance() {
    return this.date_naissance;
  }

  public String getEmail() {
    return this.email = email;
  }

  public String getVisibilite() {
    return this.visibilite;
  }
}
