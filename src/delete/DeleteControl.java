//作成者（作成日）：保手濱 七海（2023/05/25）
//最終修正者（最終修正日）：保手濱 七海（2023/05/25）
package delete;

import java.util.ArrayList;
import java.util.Scanner;

import dao.DeleteDAO;
import employee.Employee;
import function.Keyboard;
import function.Logout;

//入力を受け付け、社員情報を論理削除するクラス
public class DeleteControl {
	private Scanner scanner; // ユーザーの入力を受け付けるためのスキャナー
	private DeleteDAO deleteDAO; // 検索を行うためのDAOクラスのインスタンス
	private String input; //コンソールからの入力を代入する
	private int deleteEmpNumber = 0; //削除するための社員番号を代入する

	/*
	 * 概要：ControlDeleteクラスのコンストラクタ。スキャナーと検索DAOを初期化する。
	 */
	public DeleteControl() {
		scanner = new Scanner(System.in);
		deleteDAO = new DeleteDAO();
	}

	/*
	 * メソッド名：delete
	 * 概要：キーワードを入力し、検索結果を表示する
	 * 引数：なし
	 * 戻り値：なし
	 */
	public void delete()  throws Logout {
		boolean exit = false;
		System.out.println("【削除を行います。】");
		while (!exit) {
			System.out.print("削除したい社員の社員番号を入力してください> ");
			//input = scanner.nextLine();
			input = Keyboard.kbInput();
			// Keyboardクラスの入力メソッドに置換しました 2023-05-27-17:19 Shimanaka

			// kbInputにthrows Logoutを実装したため不要になりました 2023-05-27 17:20 Shimanaka
//			if(Keyboard.judge(input)){throw new Logout();}
//			// throws Logout と一緒に追加しました 2023-05-26-19:28 Shimanaka
//

			// throws Logoutによりこの分岐には入らなくなりました 2023-05-26-19:35 Shimanaka
			if (input.equalsIgnoreCase("home")) {
				//本来、操作選択に戻る
				System.out.println("終了しました");
				exit = true;
			} else if(input.length() == 0 || input == null){
				System.out.println("少なくとも一文字を入力してください");
			} else if(isNumeric(input)){
				deleteEmpNumber = Integer.parseInt(input);
				ArrayList<Employee> result = deleteDAO.searchActiveEmployees(deleteEmpNumber);
				printSearchResult(result);

				while(true) {
					System.out.println("この社員を削除しますか？(はい: y, いいえ: n)> ");
					String confirmDelete = scanner.nextLine();
					// kbInputにthrows Logoutを実装したため不要になりました 2023-05-27 17:20 Shimanaka
//					if(Keyboard.judge(input)){throw new Logout();}
//					// throws Logout と一緒に追加しました 2023-05-26-19:28 Shimanaka
					if(confirmDelete.equals("y")){
						//削除確認で入力が y(はい) のとき、DAOに論理削除を依頼
						deleteDAO.deleteEmployee(deleteEmpNumber);
						System.out.println("サンプル：引き続き他の社員の削除を行いますか？(はい: y, ホームに戻る: home)");
						//todo: 入力がyなら削除機能の頭に戻る(break?)、homeなら昨日選択に戻る
						// ActivateAndDeleteTestMainで失敗、todoなので未実装と認識。2023-05-26-18:22 Shimanaka
						break;
					}else if(confirmDelete.equals("n")){
						//削除確認で入力が　n(いいえ)　のとき、削除機能の頭に戻る
						break;
					}else {
						System.out.println(" y 又は n を入力してください。");
						printSearchResult(result);
						continue;
					}
				}

			}else {
				System.out.println("入力は数字で行ってください");
			}
		}
	}

	/*
	 * メソッド名：isNumeric
	 * 概要：引数の文字列が数字かどうか判定する
	 * 引数：	String　str コンソールからの入力
	 * 戻り値：boolean
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

	/*
	 * メソッド名：printSearchResult
	 * 概要：検索結果を表示する
	 * 引数：result - 検索結果の社員情報リスト
	 * 戻り値：なし
	 */
	private void printSearchResult(ArrayList<Employee> result)  throws Logout {
		if (result.isEmpty()) {
			System.out.println("該当する社員はいません。");
			System.out.println("*******************************************************");
			delete();
		}
		else {
			for (Employee employee : result) {
				System.out.println("*******************************************************");
				System.out.print(employee.getEmpno());
				System.out.print("　: " + employee.getLname());
				System.out.print(" " + employee.getFname());
				System.out.print(" (" + employee.getLkana());
				System.out.print(" " + employee.getFkana() + ")");
				System.out.println("　: " + employee.getDeptname());
				System.out.println("*******************************************************");

			}
		}
	}

}
