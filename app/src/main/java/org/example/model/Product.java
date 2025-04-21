package org.example.model;

public abstract class Product {
  /**
   * 商品ID
   */
  private int id;
  /**
   * 商品名
   */
  private String name;
  /**
   * 商品価格
   */
  private int price;
  /**
   * 在庫数
   */
  private int stock;

  public Product(int id, String name, int price, int stock) {
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

  public void setStock(int stock) {
    this.stock = stock;
  }

  abstract public void reduceStock();
}
