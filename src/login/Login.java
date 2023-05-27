package login;

import function.Logout;

/**
 * ログインに関する操作を呼び出すクラス
 * @author Shimanaka 2023-05-26 作成
 * @author Shimanaka 2023-05-26 更新
 *
 */
public class Login {
	/**
	 * ログインに関する操作を呼び出すメソッド
	 * @throws Logout
	 */
	public static void login() throws Logout {
		LoginControl.loginToEIMS();
	}
}
