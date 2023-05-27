package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 【統合前】ログインのDAOに関するクラス
 * @author Shimanaka 2023-05-26 作成
 * @author Shimanaka 2023-05-26 更新
 *
 */
public class LoginDAO {
	public static String makeLoginSQL(String id) {
		String sql = "SELECT password FROM employee WHERE empno = ?";
		return sql;
	}

	/**
	 * 入力された社員番号に紐づけられるパスワードを返すメソッド
	 * @param String id 社員番号
	 * @return String pw パスワード
	 */
	public static String correctPW(String id) {
		String password = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try (Connection con = DBConnection.getConnection()){
			// 接続開始
			stmt = con.prepareStatement(makeLoginSQL(id));
			stmt.setString(1, id); // パラメータをバインド

			rs = stmt.executeQuery();

			while (rs.next()) {
				password = rs.getString("password");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 必要な後処理を行う
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return password;
	}
}
