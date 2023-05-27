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
 */package update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import employee.Employee;

public class TestUpdateDAO {

	//接続情報

	public static final String jdbcUrl = "jdbc:mysql://localhost/eimsdb?serverTimezone=Asia/Tokyo";
	public static final String userId = "embuser";
	public static final String password = "password";

	//社員番号に該当する社員情報を取得、送信
	/*メソッド名: updateSearch
	 * 引数: SearchEmpno 入力された社員番号
	 * 戻り値: ArrayList<Employee> 該当する社員データを格納して返す
	 */

//public static ArrayList<Employee>updateSearch(String SearchEmpno){// intに変更
public static ArrayList<Employee>updateSearch(int SearchEmpno){
	//static
	ArrayList<Employee> searchEmpno = new ArrayList<>();
	try (Connection con = DriverManager.getConnection(jdbcUrl, userId, password)) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT e.empno, e.lname, e.fname, e.lkana, e.fkana, e.password, e.gender, e.deptno, d.deptname "
				+ "FROM EMPLOYEE e JOIN DEPARTMENT d ON e.deptno = d.deptno "
				+ "WHERE e.empno =  ? ";

		pstmt = con.prepareStatement(sql);

		//String word =  SearchEmpno ;
		//pstmt.setString(1, word);// intに変更
		int word = SearchEmpno;
		pstmt.setInt(1, word);
		rs = pstmt.executeQuery();

		while (rs.next()) {

			int empno = rs.getInt("e.empno");
			String lname = rs.getString("e.lname");
			String fname = rs.getString("e.fname");
			String lkana = rs.getString("e.lkana");
			String fkana = rs.getString("e.fkana");
			String password = rs.getString("e.password");
			int gender = rs.getInt("gender");
			int deptno = rs.getInt("e.deptno");
			String deptname = rs.getString("d.deptname");
			Employee employee = new Employee(empno, lname, fname, lkana, fkana, password,gender, deptno, deptname);

			searchEmpno.add(employee);

		}
} catch (SQLException e) {

	e.printStackTrace();
}
	return 	searchEmpno;
}

//受け取った社員情報を元にデータベースの情報を更新

/*メソッド名: UpdateEmployees
 * 引数:なし
 * 戻り値: なし
 */

public static void UpdateEmployees(Employee employee){
	// static
	try (Connection con = DriverManager.getConnection(jdbcUrl, userId, password)) {

		PreparedStatement pstmt = null;


//		String sql = "UPDATE Employee e JOIN department d ON e.deptno = d.deptno "
//						 +"SET  WHERE e.empno = e.empno = ?, e.lname = ?, e.fname = ?, e.lkana = ?, e.fkana = ?"
//						 +"e.password = ?, e.gender = ?, e.deptno = ?, d.deptname = ?";
		String sql = "UPDATE Employee e JOIN department d ON e.deptno = d.deptno "
		           + "SET e.lname = ?, e.fname = ?, e.lkana = ?, e.fkana = ?, "
		           + "e.password = ?, e.gender = ?, e.deptno = ?, d.deptname = ? "
		           + "WHERE e.empno = ?";
		// e.empno = が多い.SETとWHEREの書き方が多分違う

		pstmt = con.prepareStatement(sql);


			pstmt.setString(1,employee.getLname());
			pstmt.setString(2,employee.getFname());
			pstmt.setString(3,employee.getLkana());
			pstmt.setString(4,employee.getFkana());
			pstmt.setString(5,employee.getPassword());
			//pstmt.setInt(7,employee.getGender());
			pstmt.setInt(6, employee.getGender());//これは僕のgetGender()がStringで取得してるだけ、後で変更あるかも
			pstmt.setInt(7,employee.getDeptno());
			pstmt.setString(8,employee.getDeptname());
			pstmt.setInt(9,employee.getEmpno());
			//sql文を変更したので番号も変わってます

			int num = pstmt.executeUpdate();

} catch (SQLException e) {

	e.printStackTrace();
}
}
}
