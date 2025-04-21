package org.example.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

import org.example.App;

public class CsvUtil {
  /**
   * CSVファイルを読み込み、配列として返却する
   */
  public static String[][] importCsv(String fileName) {
    List<String[]> CsvDataList = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(App.class.getClassLoader().getResourceAsStream(fileName)))) {

      String line;
      boolean isFirst = true;
      while ((line = reader.readLine()) != null) {
        // 最初の行はヘッダーなのでスキップ
        if (isFirst) {
          isFirst = false;
          continue;
        }

        String[] parts = line.split(",");
        CsvDataList.add(parts);
      }

      String[][] array = new String[CsvDataList.size()][];
      return CsvDataList.toArray(array);
    } catch (Exception e) {
      System.err.println("CSVファイルの取り込みに失敗しました: " + e.getMessage());
      return new String[0][0]; // 空の2次元配列を返却
    }
  }
}
