package org.example.util;

public class OutputManager {
  /**
   * 操作の区切りを表示する
   */
  public static void showInterval() {
    System.out.println("\n------------------------------------------\n");
  }

  /**
   * エラーメッセージを表示する
   * 
   * @param message メッセージ内容
   */
  public static void showErrorMessage(String message) {
    System.out.println("（入力エラー）" + message);
  }

  /**
   * 選択を促すメッセージを表示する
   * 
   * @param start 開始
   * @param end   終了
   */
  public static void showSelected(int start, int end) {
    System.out.print(start + " ~ " + end + "を選択してください: ");
  }

  /**
   * 選択肢を表示する
   * 
   * @param number  番号
   * @param content 内容
   */
  public static void showChoices(int number, String content) {
    System.out.println("【" + number + "】" + content);
  }

  /**
   * 操作内容を表示する
   * 
   * @param content
   */
  public static void showOperation(String content) {
    System.out.println("■ " + content);
  }

  /**
   * 見出しを表示する
   * 
   * @param heading 見出し内容
   */
  public static void showHeading(String heading) {
    System.out.println("＜＜＜＜＜" + heading + "＞＞＞＞＞\n");
  }
}
