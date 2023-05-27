package search;

import function.Keyboard;
import function.Logout;

/**
 * 検索に関する操作を呼び出すクラス
 * @author Shimanaka 2023-05-23 作成
 * @author Shimanaka 2023-05-26 更新
 *
 */
public class Search {
	/**
	 * 検索に関する操作を呼び出すメソッド
	 */
	public static void search() {
		try {
			int flag = 0;
			while (flag == 0) {
				SearchControl.control();
				while (true) {
					System.out.println("もう一度はじめから検索を行う[Y], ホームに戻る[home]");
					System.out.println("*******************************************************");
					System.out.print(" > ");
					String judge = Keyboard.kbInput();
					if (Keyboard.judge(judge)) {
						throw new Logout();
					}
					if (judge.equals("Y") || judge.equals("y") || judge.equals("ｙ")) {
						System.out.println("*******************************************************");
						System.out.println("新たな検索を開始します");
						flag = 0;
						break;
					} else if (judge.equals("home") || judge.equals("Ｈｏｍｅ") || judge.equals("Home")
							|| judge.equals("ほめ")) {
						System.out.println("*******************************************************");
						System.out.println("検索を終了します");
						flag = 1;
						break;
					} else {
						System.out.println("*******************************************************");
						System.out.println("不正な入力です");
						continue;
					}
				}
			}
		} catch (Logout e) {
			System.out.println("*******************************************************");
			System.out.println("ホーム画面に戻ります");
		}
	}
}
