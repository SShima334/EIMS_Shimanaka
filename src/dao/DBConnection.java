//作成者（作成日）：保手濱 七海（2023/05/26）
//最終修正者（最終修正日）：保手濱 七海（2023/05/26）
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 【統合版】
 * DBへ接続するクラス
 */
public class DBConnection {
	private static final String JDBC_URL = "jdbc:mysql://localhost/eimsdb?sereverTimezone=Asia/Tokyo"; // データベースへの接続URLの文字列
	private static final String USERNAME = "embuser"; // データベースの認証情報：ユーザー名
	private static final String PASSWORD = "password"; // データベースの認証情報：パスワード

	/*
	 * メソッド名：getConnection
	 * 概要：データベースへの接続を取得する
	 * 引数：なし
	 * 戻り値：データベースへの接続
	 * 例外：SQLException - データベースへの接続に失敗した場合にスローされる
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	}
}
