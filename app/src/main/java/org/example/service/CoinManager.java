package org.example.service;

public class CoinManager {
  /**
   * 使用可能なお金
   */
  private final int[] availableCoins = { 1000, 500, 100, 50, 10 };

  public int[] getAvailableCoins() {
    return availableCoins;
  }

  /**
   * 使用可能なコインのインデックスを取得する
   * 
   * @return コインのインデックス
   */
  public int[] getCoinsIndex() {
    int[] coinsIndex = new int[availableCoins.length];
    for (int i = 0; i < availableCoins.length; i++) {
      coinsIndex[i] = i + 1;
    }
    return coinsIndex;
  }

  /**
   * おつりを計算する
   * 
   * @param change おつりの金額
   */
  public void calculateChange(int change) {
    if (change <= 0) {
      System.out.println("おつりは0円です");
      return;
    }

    int[] changeCoins = { 500, 100, 50, 10 };
    int amount = change;

    for (int changeCoin : changeCoins) {
      int coinCount = amount / changeCoin;
      amount -= changeCoin * coinCount;
      if (coinCount > 0) {
        System.out.println(changeCoin + "円: " + coinCount + "枚");
      }
    }
  }
}
