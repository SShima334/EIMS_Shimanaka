package function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * キーボード入力に関するクラス
 * @author Shimanaka 2023-05-24 作成
 * @author Shimanaka 2023-05-27 更新
 */
public class Keyboard {
	/**
	 * キーボード入力を１行で受け付け、String型で返すメソッド
	 * @return String
	 * @throws Logout
	 */
	public static String kbInput() throws Logout {
		String data = null;
		while (true) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				while (br.ready()) {
					br.readLine();
				}
				data = br.readLine();
				// throws Logout と一緒に追加しました 2023-05-27-17:12 Shimanaka
				if (Keyboard.judge(data)) {
					throw new Logout();
				}
				// 0文字入力はどのメソッドでも不要だったため本メソッド内に記述します 2023-05-27-17:22 Shimanaka
				if (data == null || data.length() == 0) {
					System.out.println("少なくとも一文字を入力してください");
					continue;
				}
				else {
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	/**
	 * キーボード入力を１行で受け付け、int型で返すメソッド
	 * @return String
	 */
	public static int kbInputInt() throws Logout{
		String dataS = null;
	    int data = 0; // デフォルト値（例：0）でdataを初期化します
	    while (true) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				while (br.ready()) {
					br.readLine();
				}
				dataS = br.readLine();
				// throws Logout と一緒に追加しました 2023-05-27-17:12 Shimanaka
				if (Keyboard.judge(dataS)) {
					throw new Logout();
				}
				// 0文字入力はどのメソッドでも不要だったため本メソッド内に記述します 2023-05-27-17:22 Shimanaka
				if (dataS == null || dataS.length() == 0) {
					System.out.println("少なくとも一文字を入力してください");
					continue;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (isNumeric(dataS) == false) {
				System.out.println("数字を入力してください");
				System.out.println();
				System.out.print(" > ");
				continue;
			} else {
				break;
			}
		}
		data = Integer.parseInt(dataS);
	    return data;
	}

	/**
	 * メソッド名：isNumeric
	 * 概要：引数の文字列が数字かどうか判定する
	 * 引数：	String　str コンソールからの入力
	 * 戻り値：boolean
	 * 作成者（作成日）：保手濱 七海（2023/05/25）
	 * 最終修正者（最終修正日）：保手濱 七海（2023/05/25）
	 */
	private static boolean isNumeric(String str) {
	        if (str == null || str.length() == 0) {
	            return false;
	        }
	        for (char c : str.toCharArray()) {
	            if (!Character.isDigit(c)) {
	                return false;
	            }
	        }
	        return true;
	    }

	/**
	 * キーボード入力が"$home"かどうかを判定するメソッド。
	 * @return boolean
	 */
	public static boolean judge(String input) {
		if(input.equals("$home")) {
			return true;
		}
		return false;
	}

}
