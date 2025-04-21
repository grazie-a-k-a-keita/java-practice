package org.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.example.model.Drink;
import org.example.service.impl.VendingMachine;
import org.example.util.OperationType;
import org.example.util.OutputManager;
import org.example.util.ValidateManager;
import org.example.util.CsvUtil;

public class DrinkVendingMachine implements VendingMachine {
  /**
   * ドリンク情報
   */
  private List<Drink> drinks;
  /**
   * 投入金額
   */
  private int amountInvested;
  /**
   * 入力スキャナー
   */
  Scanner scanner = new Scanner(System.in);

  @Override
  public void showDisplay() {
    System.out.println("=============== 自動販売機 ===============");
    System.out.println("＜投入金額: " + this.amountInvested + "円＞\n");
    for (Drink drink : this.drinks) {
      if (drink.getStock() == 0) {
        OutputManager.showChoices(
            drink.getId(), "○（売り切れ）: " + "---円 " + drink.getName() + "(" + drink.isHotConversion() + ")");
      } else if (amountInvested < drink.getPrice()) {
        OutputManager.showChoices(
            drink.getId(), "○（購入不可）: " + drink.getPrice() + "円 " + drink.getName() + "(" + drink.isHotConversion()
                + ")");
      } else {
        OutputManager.showChoices(
            drink.getId(),
            "●（購入可能）: " + drink.getPrice() + "円 " + drink.getName() + "(" + drink.isHotConversion() + ")");
      }
    }
    System.out.println("==========================================");
  }

  @Override
  public void putMoney() {
    OutputManager.showOperation("投入するお金を選択してください（キャンセルする場合は「0」を選択）");

    CoinManager coinManager = new CoinManager();
    int[] availableCoins = coinManager.getAvailableCoins();

    for (int i = 0; i < availableCoins.length; i++) {
      OutputManager.showChoices(i + 1, availableCoins[i] + "円");
    }

    while (true) {
      OutputManager.showSelected(1, availableCoins.length);
      String inputValue = scanner.nextLine();

      if (!ValidateManager.checkNumber(inputValue)) {
        OutputManager.showErrorMessage("半角数字を入力してください");
        continue;
      } else if (Integer.parseInt(inputValue) == 0) {
        System.out.println("お金の投入をキャンセルしました");
        break;
      } else if (!ValidateManager.checkExpected(inputValue, coinManager.getCoinsIndex())) {
        OutputManager.showErrorMessage("対象外の数字が入力されました");
        continue;
      } else {
        int puttedMoney = availableCoins[Integer.parseInt(inputValue) - 1];
        System.out.println(puttedMoney + "円を投入しました");
        this.amountInvested += puttedMoney;
        break;
      }
    }
  }

  @Override
  public void buyProduct() {
    showDisplay();
    OutputManager.showInterval();

    OutputManager.showOperation("購入するドリンクを選択してください（キャンセルする場合は「0」を選択）");

    while (true) {
      OutputManager.showSelected(1, drinks.size());
      String inputValue = scanner.nextLine();

      int[] expectedValues = new int[drinks.size()];
      for (int i = 0; i < drinks.size(); i++) {
        expectedValues[i] = drinks.get(i).getId();
      }

      if (!ValidateManager.checkNumber(inputValue)) {
        OutputManager.showErrorMessage("半角数字を入力してください");
        continue;
      } else if (Integer.parseInt(inputValue) == 0) {
        System.out.println("購入をキャンセルしました");
        break;
      } else if (!ValidateManager.checkExpected(inputValue, expectedValues)) {
        OutputManager.showErrorMessage("存在しないドリンクが選択されました");
        continue;
      } else {
        Drink selectedDrink = drinks.get(Integer.parseInt(inputValue) - 1);

        if (selectedDrink.getStock() == 0) {
          OutputManager.showErrorMessage("売り切れのため購入できません");
          continue;
        } else if (amountInvested < selectedDrink.getPrice()) {
          OutputManager.showErrorMessage("投入金額が不足しています");
          continue;
        } else {
          System.out.println("\n☆ " + selectedDrink.getName() + "を購入しました！！！");
          this.amountInvested -= selectedDrink.getPrice();
          selectedDrink.reduceStock();
          break;
        }
      }
    }
  }

  @Override
  public void returnChange() {
    OutputManager.showOperation("おつりを返却します");

    CoinManager coinManager = new CoinManager();
    coinManager.calculateChange(this.amountInvested);
    this.amountInvested = 0;
  }

  public int selectOperation() {
    OutputManager.showOperation("操作を選択してください");
    OutputManager.showChoices(OperationType.INSERT_MONEY, "お金を投入する");
    OutputManager.showChoices(OperationType.BUY_DRINK, "ドリンクを購入する");
    OutputManager.showChoices(OperationType.RETURN_CHANGE, "おつりを返却する");

    while (true) {
      OutputManager.showSelected(1, 3);
      String inputValue = scanner.nextLine();

      if (!ValidateManager.checkNumber(inputValue)) {
        OutputManager.showErrorMessage("半角数字を入力してください");
        continue;
      } else if (!ValidateManager.checkExpected(inputValue, new int[] {
          OperationType.INSERT_MONEY,
          OperationType.BUY_DRINK,
          OperationType.RETURN_CHANGE
      })) {
        OutputManager.showErrorMessage("対象外の数字が入力されました");
        continue;
      } else {
        return Integer.parseInt(inputValue);
      }
    }
  }

  @Override
  public void initialization() {
    this.drinks = new ArrayList<>();

    String[][] drinkArray = CsvUtil.importCsv("drink.csv");

    for (String[] drinkInfo : drinkArray) {
      Drink drink = new Drink(Integer.parseInt(
          drinkInfo[0]), drinkInfo[1], Integer.parseInt(drinkInfo[2]), Integer.parseInt(drinkInfo[3]),
          Boolean.parseBoolean(drinkInfo[4]));
      this.drinks.add(drink);
    }

    System.out.println("\n");
    OutputManager.showHeading("自販機が起動しました");

    boolean operationEnd = true;

    while (operationEnd) {
      showDisplay();
      OutputManager.showInterval();

      int operation = selectOperation();
      OutputManager.showInterval();

      switch (operation) {
        case OperationType.INSERT_MONEY:
          OutputManager.showHeading("お金を投入する");
          putMoney();
          break;
        case OperationType.BUY_DRINK:
          OutputManager.showHeading("ドリンクを購入する");
          buyProduct();
          break;
        case OperationType.RETURN_CHANGE:
          OutputManager.showHeading("おつりを返却する");
          returnChange();
          operationEnd = false;
          break;
        default:
          break;
      }
      OutputManager.showInterval();
    }

    System.out.println("自動販売機を終了します");
    System.out.println("ご利用ありがとうございました");
  }
}
