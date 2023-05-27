package search;

import java.util.ArrayList;

import dao.SearchDAO;
import employee.Employee;
import function.Keyboard;
import function.Logout;

/**
 * 社員検索操作に関するクラス
 * ロジックはここで
 * @author Shimanaka 2023-05-24 作成
 * @author Shimanaka 2023-05-26 更新
 *
 */
public class SearchControl{
	/**
	 * 社員検索操作のロジックを実行するメソッド
	 * @throws Logout
	 */
	public static void control() throws Logout {

		ArrayList<Employee> searchData = new ArrayList<>();
		ArrayList<String> searchWords = new ArrayList<>();
		String input = " ";
		int flag = 0;

		while (flag == 0) {
			// 入力フェーズ
			while (true) {
				System.out.println("*******************************************************");
				System.out.println("検索条件をスペースで区切って入力してください");
				System.out.println("*******************************************************");
				System.out.print(" > ");

				String keyinput = convertHalfOrFull(Keyboard.kbInput());
				//System.out.println("検索中");
				if(Keyboard.judge(keyinput)){throw new Logout();}
				//System.out.println("検索中");
				//keyinput = Keyboard.kbInput();
				keyinput = convertHalfOrFull(keyinput);
				//System.out.println("検索中");
				input = input +  " "+ keyinput;

				// 上記inputについて、Enterを押すまでプログラムが進まなくなるバグがあったので分けてデバッグ済
				// 結構めんどくさかったのでこのまま残します

				if (input != null && input.trim().length() > 0) {
					// 前後の連続する空白文字をtrimすることで入力なしと空白文字のみを検出可能
					// ただし区切りの際に連続して空白を入力された場合には未対応。エラーは吐かないが検索に失敗する。
					break;
				} else {
					System.out.println();
					System.out.println("*******************************************************");
					System.out.println("不正な入力が行われました");
				}
			}
			String[] words = input.trim().split(" ");
			for (String value : words) {
				searchWords.add(value);
			}

			String sql = SearchDAO.makeSQL(searchWords);
			searchData = SearchDAO.executeSearch(sql, searchWords);
			// 検索件数の表示
			System.out.println("*******************************************************");
			System.out.println(searchData.size() + "人のデータが該当しました。");

			// 絞り込み検索選択
			while (true) {
				System.out.println("検索結果を表示 [d],追加での絞り込み検索 [a]");
				System.out.println("*******************************************************");
				System.out.print(" > ");

				String judge = Keyboard.kbInput();
				if(Keyboard.judge(judge)){throw new Logout();}
				if (judge.equals("a") || judge.equals("A") || judge.equals("あ")) {
					break;
				} else if (judge.equals("D") || judge.equals("d") || judge.equals("ｄ")) {
					flag = 1;
					break;
				} else {
					System.out.println("*******************************************************");
					System.out.println("不正な入力が行われました");
					System.out.println(judge);
					continue;
				}
			}
		}
		// 検索結果の表示
		System.out.println("*******************************************************");
		System.out.println("検索結果を表示します(" + searchData.size() + "件)");
		if(searchData.size()>0) {
			System.out.println("社員番号 ：氏名(フリガナ)　　　　　　　　　　　：部署名");
			System.out.println("=======================================================");
			for (int i = 0; i < searchData.size(); i++) {
				System.out.println(searchData.get(i).toString());
			}
			System.out.println("=======================================================");
		}
	}

	/**
	 * 半角カタカナを全角カタカナに、全角数字を半角数字に、全角スペースを半角スペースに変換するメソッド
	 * @return String
	 */
	private static String convertHalfOrFull(String input) {
		String converted = input.replace("　", " ")
				.replace("-", "ー") // 以後、電話番号の追加と外国人の追加で競合する可能性。今回はこのままいきます。
				.replace("ｶﾞ", "ガ")
				.replace("ｷﾞ", "ギ")
				.replace("ｸﾞ", "グ")
				.replace("ｹﾞ", "ゲ")
				.replace("ｺﾞ", "ゴ")
				.replace("ｻﾞ", "ザ")
				.replace("ｼﾞ", "ジ")
				.replace("ｽﾞ", "ズ")
				.replace("ｾﾞ", "ゼ")
				.replace("ｿﾞ", "ゾ")
				.replace("ﾀﾞ", "ダ")
				.replace("ﾁﾞ", "ヂ")
				.replace("ﾂﾞ", "ヅ")
				.replace("ﾃﾞ", "デ")
				.replace("ﾄﾞ", "ド")
				.replace("ﾊﾞ", "バ")
				.replace("ﾋﾞ", "ビ")
				.replace("ﾌﾞ", "ブ")
				.replace("ﾍﾞ", "ベ")
				.replace("ﾎﾞ", "ボ")
				.replace("ﾊﾟ", "パ")
				.replace("ﾋﾟ", "ピ")
				.replace("ﾌﾟ", "プ")
				.replace("ﾍﾟ", "ペ")
				.replace("ﾎﾟ", "ポ")
				.replace("ｱ", "ア")
				.replace("ｲ", "イ")
				.replace("ｳ", "ウ")
				.replace("ｴ", "エ")
				.replace("ｵ", "オ")
				.replace("ｶ", "カ")
				.replace("ｷ", "キ")
				.replace("ｸ", "ク")
				.replace("ｹ", "ケ")
				.replace("ｺ", "コ")
				.replace("ｻ", "サ")
				.replace("ｼ", "シ")
				.replace("ｽ", "ス")
				.replace("ｾ", "セ")
				.replace("ｿ", "ソ")
				.replace("ﾀ", "タ")
				.replace("ﾁ", "チ")
				.replace("ﾂ", "ツ")
				.replace("ﾃ", "テ")
				.replace("ﾄ", "ト")
				.replace("ﾅ", "ナ")
				.replace("ﾆ", "ニ")
				.replace("ﾇ", "ヌ")
				.replace("ﾈ", "ネ")
				.replace("ﾉ", "ノ")
				.replace("ﾊ", "ハ")
				.replace("ﾋ", "ヒ")
				.replace("ﾌ", "フ")
				.replace("ﾍ", "ヘ")
				.replace("ﾎ", "ホ")
				.replace("ﾏ", "マ")
				.replace("ﾐ", "ミ")
				.replace("ﾑ", "ム")
				.replace("ﾒ", "メ")
				.replace("ﾓ", "モ")
				.replace("ﾔ", "ヤ")
				.replace("ﾕ", "ユ")
				.replace("ﾖ", "ヨ")
				.replace("ﾗ", "ラ")
				.replace("ﾘ", "リ")
				.replace("ﾙ", "ル")
				.replace("ﾚ", "レ")
				.replace("ﾛ", "ロ")
				.replace("ﾜ", "ワ")
				.replace("ｦ", "ヲ")
				.replace("ﾝ", "ン")
				.replace("ｧ", "ァ")
				.replace("ｨ", "ィ")
				.replace("ｩ", "ゥ")
				.replace("ｪ", "ェ")
				.replace("ｫ", "ォ")
				.replace("ｬ", "ャ")
				.replace("ｭ", "ュ")
				.replace("ｮ", "ョ")
				.replace("ｯ", "ッ")
				.replace("０", "0")
				.replace("１", "1")
				.replace("２", "2")
				.replace("３", "3")
				.replace("４", "4")
				.replace("５", "5")
				.replace("６", "6")
				.replace("７", "7")
				.replace("８", "8")
				.replace("９", "9");
		return converted;
	}

}