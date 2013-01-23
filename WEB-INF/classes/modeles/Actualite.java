package modeles;

import java.sql.Date;

public class Actualite {

  private int id_actualite;
  private String contenu;
  private Date date_ajout;

  public Actualite() {}

  public Actualite(int id_actualite, String contenu, Date date_ajout) {
    this.id_actualite = id_actualite;
    this.contenu = contenu;
    this.date_ajout = date_ajout;
  }

  /*
   * Setter
   */
  public void setId_actualite(int id_actualite) {
    this.id_actualite = id_actualite;
  }

  public void setContenu(String contenu) {
    this.contenu = contenu;
  }

  public void setDate_ajout(Date date_ajout) {
    this.date_ajout = date_ajout;
  }

  /*
   * Getter
   */
  public int getId_actualite() {
    return this.id_actualite;
  }

  public String getContenu() {
    return this.contenu;
  }

  public Date getDate_ajout() {
    return this.date_ajout;
  }
}
