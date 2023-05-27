//作成者（作成日）：保手濱 七海（2023/05/25）
//最終修正者（最終修正日）：保手濱 七海（2023/05/25）
package activate;

import java.util.ArrayList;
import java.util.Scanner;

import dao.DeleteDAO;
import employee.Employee;
import function.Keyboard;
import function.Logout;

//入力を受け付け、社員情報を回復するクラス
public class ActivateControl {
	private Scanner scanner; // ユーザーの入力を受け付けるためのスキャナー
	private DeleteDAO deleteDAO; // 検索を行うためのDAOクラスのインスタンス
	private String input; //コンソールからの入力を代入する
	private int activateEmpNumber = 0; //削除するための社員番号を代入する

	/*
	 * 概要：ControlDeleteクラスのコンストラクタ。スキャナーと検索DAOを初期化する。
	 */
	public ActivateControl() {
		scanner = new Scanner(System.in);
		deleteDAO = new DeleteDAO();
	}

	/*
	 * メソッド名：activate
	 * 概要：キーワードを入力し、検索結果を表示する
	 * 引数：なし
	 * 戻り値：なし
	 */
	public void activate() throws Logout{
		// 全機能についてActivateAndDeleteTestMainで期待される実行を確認。2023-05-26-18:25 Shimanaka
		boolean exit = false;
		System.out.println("【社員情報の回復を行います。】");
		while (!exit) {
			System.out.print("回復したい社員の社員番号を入力してください> ");
			//input = scanner.nextLine();
			input = Keyboard.kbInput();
			if (input.equalsIgnoreCase("home")) {
				//本来、操作選択に戻る
				System.out.println("終了しました");
				exit = true;
			} else if(input.length() == 0 || input == null){
				System.out.println("少なくとも一文字を入力してください");
			} else if(isNumeric(input)){
				activateEmpNumber = Integer.parseInt(input);
				ArrayList<Employee> result = deleteDAO.searchDeletedEmployees(activateEmpNumber);
				printSearchResult(result);

				while(true) {
					System.out.println("この社員を回復しますか？(はい: y, いいえ: n)> ");
					String confirmActivate = scanner.nextLine();
					if(confirmActivate.equals("y")){
						//削除確認で入力が y(はい) のとき、DAOに論理削除を依頼
						deleteDAO.activateEmployee(activateEmpNumber);
						System.out.println("サンプル：引き続き他の社員の回復を行いますか？(はい: y, ホームに戻る: home)");
						//todo: 入力がyなら回復機能の頭に戻る(break?)、homeなら昨日選択に戻る
						break;
					}else if(confirmActivate.equals("n")){
						//回復確認で入力が　n(いいえ)　のとき、回復機能の頭に戻る
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
