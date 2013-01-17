package modeles;

import java.util.Date;

public class Ami {

  int id_personne1;
  int id_personne2;
  Date date_ajout;

  /*
   * Setter
   */
  public void setId_personne1(int id_personne1) {
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

  public Date getDate_ajout() {
    return this.date_ajout;
  }
}
