package login;

import java.util.Scanner;

import dao.LoginDAO;
import function.Keyboard;
import function.Logout;

/**
 * ログインに関する操作を行うクラス
 * @author Shimanaka 2023-05-26 作成
 * @author Shimanaka 2023-05-26 更新
 */
public class LoginControl {
    /**
     * ログインを確認するメソッド
     * @throws Logout
     */
    public static void loginToEIMS() throws Logout {
        while (true) {
            System.out.println("社員番号とパスワードを用いてログインしてください");
            System.out.println();
            System.out.print("社員番号 > ");

            String input_empno = Keyboard.kbInput();
            if (input_empno != null && input_empno.trim().length() > 0) {

            } else {
                System.out.println();
                System.out.println("*******************************************************");
                System.out.println("不正な入力が行われました");
                continue;
            }
            System.out.println();
            System.out.print("パスワード > ");

            Scanner scanner = new Scanner(System.in);
            String input_pw = PasswordInput.readPassword(scanner);

//            String input_pw = Keyboard.kbInput();
//            System.out.println();

            if (input_pw != null && input_pw.trim().length() > 0) {

            } else {
                System.out.println();
                System.out.println("*******************************************************");
                System.out.println("不正な入力が行われました");
                continue;
            }

            String correctPW = LoginDAO.correctPW(input_empno);

            if (correctPW != null && correctPW.equals(input_pw)) {
                System.out.println();
                System.out.println("*******************************************************");
                System.out.println("ログインが完了しました");
                break;
            } else {
            	System.out.println();
                System.out.println("*******************************************************");
                System.out.println("パスワードまたは社員番号が異なります");
                System.out.println();
            }
        }
    }
}
