package activate;

import function.Logout;

/**
 * 削除済みの社員情報の回復を実行するクラス
 * @author Shimanaka 2023-05-26 作成
 * @author Shimanaka 2023-05-26 更新
 *
 */
public class Activate {
	/**
	 * 削除済みの社員情報の回復を実行するメソッド
	 */
	public static void activateEmployee() {
		ActivateControl controlActivate = new ActivateControl();
	    try {
			controlActivate.activate();
		} catch (Logout e) {
			System.out.println("*******************************************************");
			System.out.println("ホーム画面に戻ります");
		}
	}
}
