package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import employee.Employee;
/**
 * 【統合前】検索のDAOを管理するクラス
 * @author Shimanaka 2023-05-25 作成
 * @author Shimanaka 2023-05-26 更新
 *
 */
public class SearchDAO {

	// SQLの？の数が変わってしまうので仕方なくこれを置いておきます
	private static int searchSQLNum;

	/**
	 * 与えられた引数から検索用のSQL文を作成するメソッド
	 * @param ArrayList<String> input ユーザーの入力
	 * @return String型でSQL文を返す
	 */
	public static String makeSQL(ArrayList<String> input) {
		String sql = null;
		// 部が入力された場合のみ部署名を検索ワードから外します
		// "人事　部"とかで検索されるとめちゃくちゃ困るけどオプションなので今後要相談
		if (input.contains("部")) {
			searchSQLNum = 5;
			System.out.println("*******************************************************");
			System.out.println("「部」が検索ワードとして選択されたため部署名を検索対象から除外しました。");
			sql = "SELECT * FROM employee e "
					+ "JOIN department d ON e.deptno = d.deptno "
					+ "WHERE (e.lname LIKE BINARY ? "
					+ "OR e.fname LIKE BINARY ? "
					+ "OR e.lkana LIKE BINARY ? "
					+ "OR e.fkana LIKE BINARY ? "
					+ "OR e.deptno LIKE BINARY ? ) ";

			for (int i = 1; i < input.size(); i++) {
				//sql += " AND CONCAT(employee.lname, employee.fname, employee.lkana, employee.fkana, department.deptname, employee.deptno) LIKE BINARY ?";
				sql += " AND (e.lname LIKE BINARY ? "
						+ "OR e.fname LIKE BINARY ? "
						+ "OR e.lkana LIKE BINARY ? "
						+ "OR e.fkana LIKE BINARY ? "
						+ "OR e.deptno LIKE BINARY ? )";
			}
		} else {
			searchSQLNum = 6;
			sql = "SELECT * FROM employee e "
					+ "JOIN department d ON e.deptno = d.deptno "
					+ "WHERE (e.lname LIKE BINARY ? "
					+ "OR e.fname LIKE BINARY ? "
					+ "OR e.lkana LIKE BINARY ? "
					+ "OR e.fkana LIKE BINARY ? "
					+ "OR d.deptname LIKE BINARY ? "
					+ "OR e.deptno LIKE BINARY ? ) ";

			for (int i = 1; i < input.size(); i++) {
				//sql += " AND CONCAT(employee.lname, employee.fname, employee.lkana, employee.fkana, department.deptname, employee.deptno) LIKE BINARY ?";
				sql += " AND (e.lname LIKE BINARY ? "
						+ "OR e.fname LIKE BINARY ? "
						+ "OR e.lkana LIKE BINARY ? "
						+ "OR e.fkana LIKE BINARY ? "
						+ "OR d.deptname LIKE BINARY ? "
						+ "OR e.deptno LIKE BINARY ? )";
			}
		}
		return sql;
	}

	/**
	 * 与えられたsql文と入力内容を格納したリストからSQL文の実行を行い、Employeeが格納されたArrayListで返す
	 * @param sql:sql文をString型で
	 * @param input:入力データを格納したString型ArrayList
	 * @return Employee型ArrayList
	 */
	public static ArrayList<Employee> executeSearch(String sql, ArrayList<String> input) {
		ArrayList<Employee> resultList = new ArrayList<>();

		try (Connection con = DBConnection.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);

			for (int i = 0; i < input.size(); i++) {
				for (int j = 1; j <= searchSQLNum; j++) {
					stmt.setString(i*searchSQLNum+j, "%" + input.get(i) + "%");
				}
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int empno = rs.getInt("empno");
				String lname = rs.getString("lname");
				String fname = rs.getString("fname");
				String lkana = rs.getString("lkana");
				String fkana = rs.getString("fkana");
				String password = rs.getString("password");
				int gender = rs.getInt("gender");
				int deptno = rs.getInt("deptno");
				String deptname = rs.getString("deptname");

				Employee employee = new Employee(empno, lname, fname, lkana, fkana, password, gender, deptno, deptname);
				resultList.add(employee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultList;
	}
}
