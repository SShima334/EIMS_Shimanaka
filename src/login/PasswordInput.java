package login;

import java.util.Scanner;

/**
 * ログイン時のUser入力に関するクラス
 * @author Shimanaka 2023-05-26 作成
 * @author Shimanaka 2023-05-26 更新
 *
 */
public class PasswordInput {

	/**
	 * 【未完成】
	 * System.console()を利用して入力中のパスワードを非表示にして読み取るメソッド
	 * @param scanner
	 * @return String型の入力
	 *
	 * 05-26-10:00 Shimanaka 現状consele()が利用できていないので通常のscannerでプログラムは繋がるようにしています
	 *
	 */
	public static String readPassword(Scanner scanner) {
		if (System.console() == null) {
			// コンソールが使用できない場合、通常の文字列入力として処理する
			return scanner.nextLine();
		}

		// コンソールが使用できる場合、パスワードの非表示入力を行う
		char[] passwordArray = System.console().readPassword();
		return new String(passwordArray);
	}
}
