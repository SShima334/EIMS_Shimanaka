//作成者（作成日）：保手濱 七海（2023/05/25）
//最終修正者（最終修正日）：保手濱 七海（2023/05/25）
package activate;

import java.util.ArrayList;

import dao.DeleteDAO;
import employee.Employee;
import function.Keyboard;
import function.Logout;

//入力を受け付け、社員情報を回復するクラス
public class ActivateControl {
	//private Scanner scanner; // ユーザーの入力を受け付けるためのスキャナー
	// scannerはKeyboardクラスの入力メソッドを使うため必要なくなりました。2023-05-28-01:45 Hotehama
	private DeleteDAO deleteDAO; // 検索を行うためのDAOクラスのインスタンス
	// String inputはKeyboardクラスの入力メソッドを使うため必要なくなりました。2023-05-28-01:46 Hotehama
	private int activateEmpNumber = 0; //削除するための社員番号を代入する

	/*
	 * 概要：ControlDeleteクラスのコンストラクタ。検索DAOを初期化する。
	 */
	public ActivateControl() {
		//scanner = new Scanner(System.in);
		deleteDAO = new DeleteDAO();
	}

	/*
	 * メソッド名：activate
	 * 概要：キーワードを入力し、検索結果を表示する
	 * 引数：なし
	 * 戻り値：なし
	 */
	public void activate() throws Logout{
		/**/
		// 全機能についてActivateAndDeleteTestMainで期待される実行を確認。2023-05-26-18:25 Shimanaka
		System.out.println("【社員情報の回復を行います。】");
		System.out.print("回復したい社員の社員番号を入力してください> ");
		//input = scanner.nextLine();
		activateEmpNumber = Keyboard.kbInputInt();
		
			
			// kbInputIntに置換したため、入力に対する一連の分岐処理を削除しました 2023-05-28-01:47 Hotehama
			ArrayList<Employee> result = deleteDAO.searchDeletedEmployees(activateEmpNumber);
			printSearchResult(result);

			while(true) {
				System.out.println("この社員を回復しますか？(はい: y, いいえ: n, ホームに戻る: $home)");
				System.out.print("> ");
				String confirmActivate = Keyboard.kbInput();
				if(confirmActivate.equals("y")){
					//削除確認で入力が y(はい) のとき、DAOに論理削除を依頼
					deleteDAO.activateEmployee(activateEmpNumber);
					while (true) {
						// 削除操作の後、次の動作を尋ねる
						System.out.println("*******************************************************");
						System.out.println("引き続き他の社員の回復を行いますか？(はい: y, ホームに戻る: $home)");
						System.out.println("*******************************************************");
						System.out.print("> ");
						String selectNextAction = Keyboard.kbInput();
						if (selectNextAction.equals("y")) {
							activate();
							break;
						} else {
							System.out.println(" y 又は $home を入力してください。");
							continue;
						}
						// ActivateAndDeleteTestMainで失敗、todoなので未実装と認識。2023-05-26-18:22 Shimanaka
					}
					//todo: 入力がyなら回復機能の頭に戻る(break?)、homeなら昨日選択に戻る
					break;
				}else if(confirmActivate.equals("n")){
					//回復確認で入力が　n(いいえ)　のとき、回復機能の頭に戻る
					activate();
					break;
				}else {
					System.out.println(" y 又は n を入力してください。");
					printSearchResult(result);
					continue;
				}
			}

		
	}

	// isNumericメソッドはKeyboardクラスに移行したので、このクラスからは削除しました 2023-05-28-01:55 Hotehama


	/*
	 * メソッド名：printSearchResult
	 * 概要：検索結果を表示する
	 * 引数：result - 検索結果の社員情報リスト
	 * 戻り値：なし
	 */
	private void printSearchResult(ArrayList<Employee> result) throws Logout{
		if (result.isEmpty()) {
			System.out.println("該当する社員はいません。");
			System.out.println("*******************************************************");
			activate();
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
