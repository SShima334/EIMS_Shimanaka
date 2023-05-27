package function;

import activate.Activate;
import delete.Delete;
import insert.Insert;
import search.Search;

/**
 * メニュー画面に関するクラス（最終メインに直接実装の可能性あり）
 * @author Shimanaka 2023-05-26 作成
 * @author Shimanaka 2023-05-26 更新
 *
 */
public class Menu {
	/**
	 * 機能を提示し、Userの入力により各機能へ遷移させるメソッド
	 * @throws Logout
	 */
	public static void menu() throws Logout {
		int flag = 1;
		while (flag == 1) {

			System.out.println();
			System.out.println("操作を選択してください");
			System.out.println();
			System.out.println("*************************");
			System.out.println("*検索		: search	*");
			System.out.println("*更新		: update	*");
			System.out.println("*削除		: delete	*");
			System.out.println("*追加		: insert   	*");
			System.out.println("*復旧(Test)	: activate  *");
			System.out.println("*ログアウト	: exit		*");
			System.out.println("*************************");
			System.out.println();
			System.out.print(" > ");

			String input = Keyboard.kbInput();

			switch (input) {

			case "search":
				Search.search();
				break;

			case "update":
				//Update.UpdateEmployee();
				System.out.println("更新処理(作成中)");
				break;

			case "delete":
				Delete.deleteEmployee();
				break;

			case "activate":
				Activate.activateEmployee();
				break;

			case "insert":
				Insert.insertEmployee();
				break;

			case "exit":
				System.out.println();
				System.out.println("*******************************************************");
				System.out.println("ログアウト処理を行います");
				System.out.println("別のアカウントへログインする[re], システムを終了する[end], ログアウトをやめる[その他のキー]");
				System.out.println("*******************************************************");
				System.out.println();
				System.out.print(" > ");

				String judge = Keyboard.kbInput();
				if(judge.equals("re")) {
					flag = 0;
				} else if(judge.equals("end")) {
					flag = -1;
				} else {
					continue;
				}
				break;

			default:
				continue;
			}
		}
		if(flag == -1) {
			System.out.println();
			System.out.println("*******************************************************");
			System.out.println("*********************** End EIMS **********************");
			System.out.println("*******************************************************");
			System.exit(0);
		}
	}
}
