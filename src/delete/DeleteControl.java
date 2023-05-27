//作成者（作成日）：保手濱 七海（2023/05/25）
//最終修正者（最終修正日）：保手濱 七海（2023/05/25）
package delete;

import java.util.ArrayList;

import dao.DeleteDAO;
import employee.Employee;
import function.Keyboard;
import function.Logout;

//入力を受け付け、社員情報を論理削除するクラス
public class DeleteControl {
	// private Scanner scanner; // ユーザーの入力を受け付けるためのスキャナー
	// scannerはKeyboardクラスの入力メソッドを使うため必要なくなりました。2023-05-28-01:42 Hotehama
	private DeleteDAO deleteDAO; // 検索を行うためのDAOクラスのインスタンス
	// String inputはKeyboardクラスの入力メソッドを使うため必要なくなりました。2023-05-27-21:27 Hotehama
	private int deleteEmpNumber = 0; // 削除するための社員番号を代入する

	/*
	 * 概要：ControlDeleteクラスのコンストラクタ。検索DAOを初期化する。
	 */
	public DeleteControl() {
		// scanner = new Scanner(System.in);
		deleteDAO = new DeleteDAO();
	}

	/*
	 * メソッド名：delete 概要：キーワードを入力し、検索結果を表示する 引数：なし 戻り値：なし
	 */
	public void delete() throws Logout {
		System.out.println("【削除を行います。】");
		System.out.print("削除したい社員の社員番号を入力してください> ");
		// input = scanner.nextLine();
		deleteEmpNumber = Keyboard.kbInputInt();
		// Keyboardクラスの入力メソッドに置換しました 2023-05-27-17:19 Shimanaka

		// kbInputにthrows Logoutを実装したため不要になりました 2023-05-27 17:20 Shimanaka
//			if(Keyboard.judge(input)){throw new Logout();}
//			// throws Logout と一緒に追加しました 2023-05-26-19:28 Shimanaka
//

		// throws Logoutによりこの分岐には入らなくなりました 2023-05-26-19:35 Shimanaka
		// kbInputIntに置換したため、入力に対する一連の分岐処理を削除しました 2023-05-27-21:32 Hotehama
		ArrayList<Employee> result = deleteDAO.searchActiveEmployees(deleteEmpNumber);
		printSearchResult(result);

		while (true) {
			System.out.println("この社員を削除しますか？(はい: y, いいえ: n, ホームに戻る: $home)");
			System.out.print("> ");
			String confirmDelete = Keyboard.kbInput();
			// kbInputにthrows Logoutを実装したため不要になりました 2023-05-27 17:20 Shimanaka
//				if(Keyboard.judge(judge)){throw new Logout();}
//				// throws Logout と一緒に追加しました 2023-05-26-19:28 Shimanaka
			if (confirmDelete.equals("y")) {
				// 削除確認で入力が y(はい) のとき、DAOに論理削除を依頼
				deleteDAO.deleteEmployee(deleteEmpNumber);
				while (true) {
					// 削除操作の後、次の動作を尋ねる
					System.out.println("*******************************************************");
					System.out.println("引き続き他の社員の削除を行いますか？(はい: y, ホームに戻る: $home)");
					System.out.println("*******************************************************");
					System.out.print("> ");
					String selectNextAction = Keyboard.kbInput();
					if (selectNextAction.equals("y")) {
						delete();
						break;
					} else {
						System.out.println(" y 又は $home を入力してください。");
						continue;
					}
					// ActivateAndDeleteTestMainで失敗、todoなので未実装と認識。2023-05-26-18:22 Shimanaka
				}
				break;
			} else if (confirmDelete.equals("n")) {
				// 削除確認で入力が n(いいえ) のとき、削除機能の頭に戻る
				delete();
				break;
			} else {
				System.out.println(" y 又は n を入力してください。");
				printSearchResult(result);
				continue;
			}
		}

	}

	// isNumericメソッドはKeyboardクラスに移行したので、このクラスからは削除しました 2023-05-27-21:36 Hotehama

	/*
	 * メソッド名：printSearchResult 概要：検索結果を表示する 引数：result - 検索結果の社員情報リスト 戻り値：なし
	 */
	private void printSearchResult(ArrayList<Employee> result) throws Logout {
		if (result.isEmpty()) {
			System.out.println("該当する社員はいません。");
			System.out.println("*******************************************************");
			delete();
		} else {
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
