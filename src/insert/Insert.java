package insert;

import function.Logout;

/**
 * 社員情報の削除を実行するクラス
 * @author Shimanaka 2023-05-26 作成
 * @author Shimanaka 2023-05-26 更新
 *
 */
public class Insert {
	/**
	 * 社員情報の削除を実行するメソッド
	 * @author Shimanaka 2023-05-26 作成
	 * @author Shimanaka 2023-05-26 更新
	 */
	public static void insertEmployee() {
		try {
			InsertControl.insertEmployee();
		} catch (Logout e) {
			System.out.println("*******************************************************");
			System.out.println("ホーム画面に戻ります");
		}
	}
}
