package main;

import function.Logout;
import function.Menu;
import login.Login;
/**
 * 統合用Mainクラス
 * @author Shimanaka 2023-05-26 作成
 * @author Shimanaka 2023-05-26 更新
 *
 */
public class Main {
	/**
	 * mainメソッド
	 * @throws Logout
	 */
	public static void main(String[] args) throws Logout {
		System.out.println("*******************************************************");
		System.out.println("********************** Start EIMS *********************");
		System.out.println("*******************************************************");

		while (true) {
			System.out.println();
			Login.login();
			System.out.println();
			System.out.println("*******************************************************");
			System.out.println("***                                                  **");
			System.out.println("***   操作選択画面へ戻るにはhomeを入力してください   **");
			System.out.println("***    home はシステムの動作中いつでも実行可能です   **");
			System.out.println("***                                                  **");
			System.out.println("*******************************************************");
			Menu.menu();
			System.out.println();
			System.out.println("ログアウト処理を行いました");
		}
	}

}
