package delete;

import function.Logout;

/**
 * 社員情報の削除を実行するクラス
 * @author Shimanaka 2023-05-26 作成
 * @author Shimanaka 2023-05-26 更新
 *
 */
public class Delete {
	/**
	 * 社員情報の削除を実行するメソッド
	 * @author Shimanaka 2023-05-26 作成
	 * @author Shimanaka 2023-05-26 更新
	 */
	public static void deleteEmployee() {
		try {
			DeleteControl controlDelete = new DeleteControl();
			controlDelete.delete();
		} catch (Logout e) {
			System.out.println("*******************************************************");
			System.out.println("ホーム画面に戻ります");
		}
	}
}
