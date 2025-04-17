package org.example;

public class Drink {
  /**
   * ドリンクID
   */
  private int id;
  /**
   * ドリンク名
   */
  private String name;
  /**
   * ドリンク価格
   */
  private int price;
  /**
   * ドリンク在庫数
   */
  private int stock;

  public Drink(int id, String name, int price, int stock) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.stock = stock;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  public int getStock() {
    return stock;
  }
}
