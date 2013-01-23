package modeles;

import java.sql.Date;

public class Ami {

  private int id_personne1;
  private int id_personne2;
  private Date date_ajout;

  public Ami() {}

  public Ami(int id_personne1, int id_personne2, Date date_ajout) {
    this.id_personne1 = id_personne1;
    this.id_personne2 = id_personne2;
    this.date_ajout = date_ajout;
  }
  /*
   * Setter
   */
  public void setId_personne1(int id_personne1) {
    this.id_personne1 = id_personne1;
  }

  public void setId_personne2(int id_personne2) {
    this.id_personne1 = id_personne1;
  }

  public void setDate_ajout(Date date_ajout) {
    this.date_ajout = date_ajout;
  }

  /*
   * Getter
   */
  public int getId_personne1() {
    return this.id_personne1;
  }

  public int getId_personne2() {
    return this.id_personne2;
  }

  public Date getDate_ajout() {
    return this.date_ajout;
  }
}
