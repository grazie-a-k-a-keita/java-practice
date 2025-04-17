package org.example;

import java.util.List;
import java.util.Scanner;

public class VendingMachine {
  /**
   * ドリンク情報
   */
  private List<Drink> drinks;
  /**
   * 投入金額
   */
  private int amountInvested;

  /**
   * ドリンク情報を表示する
   */
  private void showDisplay() {
    System.out.println("===== 自動販売機 =====");
    for (Drink drink : this.drinks) {
      if (drink.getStock() == 0) {
        System.out.println("【" + drink.getId() + "】○（売り切れ）: " + "---円 " + drink.getName());
      } else if (amountInvested < drink.getPrice()) {
        System.out.println("【" + drink.getId() + "】○（購入不可）: " + drink.getPrice() + "円" + " " + drink.getName());
      } else {
        System.out.println("【" + drink.getId() + "】●（購入可能）: " + drink.getPrice() + "円" + " " + drink.getName());
      }
    }
    System.out.println("=====================");
  }

  /**
   * 金額の投入
   */
  private void putMoney() {
    System.out.println("投入するお金を選択してください");

    int[] availableMoney = AvailableMoney.getAvailableMoney();
    for (int i = 0; i < availableMoney.length; i++) {
      System.out.println("【" + (i + 1) + "】" + availableMoney[i] + "円");
    }

    System.out.print("0 ～ " + availableMoney.length + "を選択してください: ");

    Scanner scan = new Scanner(System.in);
    String inputString = scan.next();

    // 数字チェック
    if (inputString.matches("\\d+")) {
      int money = Integer.parseInt(inputString);
      System.out.println(money + "円を投入しました。");
      this.amountInvested += money;
    } else {
      System.out.println("数字を入力してください！");
    }
  }

  /**
   * 自動販売機の処理を初期化・実行する
   */
  public void execute() {
    // ドリンク情報を初期化
    this.drinks = DrinkInfoRoader.getDrinkInfo();
    showDisplay();
    System.out.println();
    putMoney();
  }
}
