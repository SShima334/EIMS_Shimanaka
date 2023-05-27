//作成者（作成日）：保手濱 七海（2023/05/25）
//最終修正者（最終修正日）：保手濱 七海（2023/05/25）
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import employee.Employee;

//社員番号を受け取り社員情報を削除するDAOクラス
public class DeleteDAO {



	public ArrayList<Employee> searchDeletedEmployees(int searchDeleteEmpno) {
		int searchDeletedEmployees = searchDeleteEmpno;
		String sql = "SELECT *"// Employeeを統合するため取得範囲を拡張しました。2023-05-26-18:15 Shimanaka
					+ "FROM employee e "
					+ "INNER JOIN department d ON e.deptno = d.deptno " + "WHERE e.active = 0 AND e.empno = ? ";
		return searchEmployeeActivation(searchDeletedEmployees, sql);
	}

	public ArrayList<Employee> searchActiveEmployees(int searchActiveEmpno) {
		int searchActiveEmployees = searchActiveEmpno;
		String sql = "SELECT *" // Employeeを統合するため取得範囲を拡張しました。2023-05-26-18:16 Shimanaka
					+ "FROM employee e "
					+ "INNER JOIN department d ON e.deptno = d.deptno " + "WHERE e.active = 1 AND e.empno = ? ";
		return searchEmployeeActivation(searchActiveEmployees, sql);
	}


	public ArrayList<Employee> searchEmployeeActivation(int keyEmpno, String sql) {

		ArrayList<Employee> employees = new ArrayList<>();
		try (Connection con = DBConnection.getConnection()) {
			// 入力した社員番号を持ち、かつactiveが1の社員をselect
			String searchActivationSql = sql;

			// select文を実行
			PreparedStatement pStmt = con.prepareStatement(searchActivationSql);
			int empnoParam = keyEmpno;
			pStmt.setInt(1, empnoParam);
			ResultSet rs = pStmt.executeQuery();

			// 取得した社員をEmployeeに入れる
			while (rs.next()) {
				int empno = empnoParam;
				String lname = rs.getString("e.lname");
				String lkana = rs.getString("e.lkana");
				String fname = rs.getString("e.fname");
				String fkana = rs.getString("e.fkana");
				String deptname = rs.getString("d.deptname");

				// Employeeを統合するため変数を追加しました。2023-05-26-17:50 Shimanaka
				String password = rs.getString("password");
				int gender = rs.getInt("gender");
				int deptno = rs.getInt("deptno");

				// Employeeを統合するため変数を追加しました。2023-05-26-17:54 Shimanaka
				Employee employee = new Employee(empno, lname, fname, lkana, fkana, password, gender, deptno, deptname);
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}



	/*
	 * メソッド名：activateEmployee
	 * 概要：引数で得た社員番号を持つ行のactiveを1にするよう、editEmployeeActivationに編集を依頼するメソッド
	 * 引数：activateEmpNumber - 社員番号
	 * 戻り値:なし
	 */
	public void activateEmployee(int activateEmpNo) {
		String sql = "UPDATE employee SET active = 1 WHERE empno = ?";
		int affectedRows = editEmployeeActivation(activateEmpNo, sql);
		if (affectedRows > 0) {
			System.out.println("【社員番号： " + activateEmpNo + "の社員情報を回復しました。】");
		} else {
			System.out.println("回復失敗！");
		}
	}
	/*
	 * メソッド名：deleteEmployee
	 * 概要：引数で得た社員番号を持つ行のactiveを0にするよう、editEmployeeActivationに編集を依頼するメソッド
	 * 引数：deleteEmpNumber - 社員番号
	 * 戻り値:なし
	 */
	// ActivateAndDeleteTestMainで期待される実行を確認。2023-05-26-18:18 Shimanaka
	public void deleteEmployee(int deleteEmpNo) {
		String sql = "UPDATE employee SET active = 0 WHERE empno = ?";
		int affectedRows = editEmployeeActivation(deleteEmpNo, sql);
		if (affectedRows > 0) {
			System.out.println("【社員番号： " + deleteEmpNo + "の社員情報を削除しました。】");
		} else {
			System.out.println("削除失敗！");
		}
	}

	/*
	 * メソッド名：editEmployeeActivation
	 * 概要：activateEmployeeまたはdeleteEmployeeからsql文と社員番号を受け取り、activeを編集するメソッド
	 * 引数：editActivationEmpNumber - 社員番号
	 * 引数:sql - sql文
	 * 戻り値:なし
	 */
	public int editEmployeeActivation(int editActivationEmpNumber, String sql) {
		int affectedRows = 0;
		try (Connection con = DBConnection.getConnection()) {
			// 入力した社員番号を持つ社員のactiveを0又は1にupdate
			String setSql = sql;

			// update文を実行
			PreparedStatement pStmt = con.prepareStatement(setSql);
			int editActivationParam = editActivationEmpNumber;
			pStmt.setInt(1, editActivationParam);
			affectedRows = pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}


}



