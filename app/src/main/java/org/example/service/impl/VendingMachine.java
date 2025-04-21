package org.example.service.impl;

public interface VendingMachine {
  /**
   * 商品情報を表示する
   */
  void showDisplay();

  /**
   * 金額の投入
   */
  void putMoney();

  /**
   * 商品を購入する
   */
  void buyProduct();

  /**
   * おつりを返却する
   */
  void returnChange();

  /**
   * 自動販売機の処理を初期化・実行する
   */
  void initialization();
}
