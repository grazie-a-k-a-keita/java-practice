package org.example.model;

public class Drink extends Product {
  /**
   * あたたかさ（true: あたたかい, false: つめたい）
   */
  private boolean isHot;

  public Drink(int id, String name, int price, int stock, boolean isHot) {
    super(id, name, price, stock);
    this.isHot = isHot;
  }

  public boolean getIsHot() {
    return this.isHot;
  }

  @Override
  public void reduceStock() {
    setStock(getStock() - 1);
  }

  public String isHotConversion() {
    if (this.isHot) {
      return "温";
    } else {
      return "冷";
    }
  }
}
