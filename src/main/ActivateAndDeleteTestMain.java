//作成者（作成日）：保手濱 七海（2023/05/25）
//最終修正者（最終修正日）：保手濱 七海（2023/05/25）

package main;

import activate.ActivateControl;
import function.Logout;

//検索を行うControlSearchクラスを呼び出すMainクラス
public class ActivateAndDeleteTestMain {
	/*
	* メソッド名：Main
	* 概要：検索を行うControlSearchクラスを呼び出すメインメソッド
	* 引数:なし
	* 戻り値:なし
	*/
 public static void main(String[] args) throws Logout {

//     ControlDelete controlDelete = new ControlDelete();
//     controlDelete.delete();

     ActivateControl controlActivate = new ActivateControl();
     controlActivate.activate();
 }

}



