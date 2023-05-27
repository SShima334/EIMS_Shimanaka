package employee;

/**
 * 社員情報を格納するクラス
 * @author Shimanaka 2023-05-24 作成
 * @author Shimanaka 2023-05-27 更新 張さんのメソッドを統合しました
 *
 */
public class Employee {
	/* Field */
	private int empno;
	private String lname;
	private String fname;
	private String lkana;
	private String fkana;
	private String password;
	private int gender;
	private int deptno;
	private String deptname;

	public Employee(int empno, String lname, String fname, String lkana, String fkana, String password, int gender,
			int deptno, String deptname) {
		super();
		this.empno = empno;
		this.lname = lname;
		this.fname = fname;
		this.lkana = lkana;
		this.fkana = fkana;
		this.password = password;
		this.gender = gender;
		this.deptno = deptno;
		this.deptname = deptname;
	}

	public int getEmpno() {
		return empno;
	}

	public String getLname() {
		return lname;
	}

	public String getFname() {
		return fname;
	}

	public String getLkana() {
		return lkana;
	}

	public String getFkana() {
		return fkana;
	}

	public String getPassword() {
		return password;
	}

	public int getGender() {
		return gender;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getDeptname() {
		return deptname;
	}

	// 各種setterを追加しました 2023-05-27-16:52 Shimanaka

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setLkana(String lkana) {
		this.lkana = lkana;
	}

	public void setFkana(String fkana) {
		this.fkana = fkana;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	/**
	 * 「社員番号 ：氏名(フリガナ)　　　　　　　　　　　：部署名」
	 * @return String
	 */
	public String toString() {
		String fullName = lname + " " + fname;
		String kanaFullName = lkana + " " + fkana;
		String name = fullName + " (" + kanaFullName + ")";
		String format = "%-6s   : %-25s : %s";

		return String.format(format, empno, name, deptname);
	}

	/**
     * 社員情報をフォーマットして取得します。
     * 戻り値：フォーマットされた社員情報
     */

    public String getFormattedInfo() {
    	return String.format("%-8s : %-20s ", empno, lname + " " + fname + " (" + lkana + " " + fkana + ")");
    }

    /**
     * フルネームをフォーマットして返します
     * @return "苗字 名前 ミョウジ ナマエ"
     */
    public String getFullName() {
        return lname + " " + fname+" " + lkana+" " + fkana;
    }

}
