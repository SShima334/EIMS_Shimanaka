package update;

import java.util.ArrayList;

import dao.TestUpdateDAO;
import employee.Employee;

public class TestUpdateMain {
	public static void main(String args[]) {
		System.out.println("=== first test ===");
		ArrayList<Employee> searchEmpno = new ArrayList<>();

		int SearchEmpno = 10008;
		searchEmpno = TestUpdateDAO.updateSearch(SearchEmpno);

		for (int i = 0; i < searchEmpno.size(); i++) {
			System.out.println(searchEmpno.get(i).toString());
		}
		System.out.println("=== second test ===");

		Employee testEmployee = new Employee(10001, "長嶋", "茂雄", "ナガシマ", "テスト", "password", 1, 100, "人事部");
		TestUpdateDAO.UpdateEmployees(testEmployee);
	}
}
