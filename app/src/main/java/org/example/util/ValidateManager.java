package org.example.util;

public class ValidateManager {
  /**
   * 半角数字かどうかのチェック
   * 
   * @param value 入力値
   * @return 判定結果（true: 正, false: 誤）
   */
  public static boolean checkNumber(String value) {
    if (value.matches("\\d+"))
      return true;

    return false;
  }

  /**
   * 期待する値と一致するかどうかのチェック
   * 
   * @param value          入力値
   * @param expectedValues 期待する値
   * @return 判定結果（true: 正, false: 誤）
   */
  public static boolean checkExpected(String value, int[] expectedValues) {
    for (int expectedValue : expectedValues) {
      if (expectedValue == Integer.parseInt(value))
        return true;
    }

    return false;
  }
}
