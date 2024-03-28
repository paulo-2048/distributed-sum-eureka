package br.ucsal.app.util;

public class OperationIdManager {

  public static int generateId() {
    return (int) (Math.random() * 900000) + 100000;
  }

  public static int checkId(int id) {
    if (id < 100000 || id > 999999) {
      return generateId();
    }
    return id;
  }

}
