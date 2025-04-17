package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DrinkInfoRoader {
  /**
   * CSVファイルを読み込み、ドリンク情報を返却する
   */
  public static List<Drink> getDrinkInfo() {
    List<Drink> drinks = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(App.class.getClassLoader().getResourceAsStream("drink.csv")))) {

      String line;
      boolean isFirst = true;
      while ((line = reader.readLine()) != null) {
        // 最初の行はヘッダーなのでスキップ
        if (isFirst) {
          isFirst = false;
          continue;
        }

        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        int price = Integer.parseInt(parts[2]);
        int stock = Integer.parseInt(parts[3]);

        drinks.add(new Drink(id, name, price, stock));
      }

      return drinks;
    } catch (Exception e) {
      System.err.println("CSVファイルの取り込みに失敗しました: " + e.getMessage());
    }
    return drinks;
  }
}
