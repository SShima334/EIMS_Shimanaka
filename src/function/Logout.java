package function;

/**
 * ログアウト管理を行う独自の例外クラス。
 * 入力を持つ全メソッドに対して
 * throws Logout を、
 * また、Userの入力の受け付け直後に
 * if(Keyboard.judge($input)){throw new Logout()};
 * を必ず配置するように。($inputは適宜編集)
 * 各最終機能クラスではtry-catchでexception処理を行う
 *
 * @author Shimanaka 2023-05-26 作成
 * @author Shimanaka 2023-05-26 更新
 *
 */
public class Logout extends Exception {

}
