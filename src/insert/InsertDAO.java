package insert;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import employee.Employee;



public class InsertDAO {
	   String jdbcUrl = "jdbc:mysql://localhost/eimsdb?serverTimezone=Asia/Tokyo";
       String userId = "embuser";
       String Password = "password";

    public boolean insertEmployee(Employee employee, String password, int gender) {
    	// 使用していなさそうなのでコメントアウトしています。 2023-05-27-16:57 Shimanaka
    	// int active=0;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(jdbcUrl, userId, Password);



            String query = "INSERT INTO employee (empno, lname, fname, lKana, fKana, deptno, password, gender, active) "
            	    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            // gettterを統一しました 2023-05-26-20:14 Shimanaka
            statement.setInt(1, employee.getEmpno()); // Stringをintに変更しました 2023-05-26-20:14 Shimanaka
            statement.setString(2, employee.getLname());
            statement.setString(3, employee.getFname());
            statement.setString(4, employee.getLkana());
            statement.setString(5, employee.getFkana());
            statement.setInt(6, employee.getDeptno()); // Stringをintに変更しました 2023-05-26-20:14 Shimanaka
            statement.setString(7, password);
            statement.setInt(8, gender);
            statement.setInt(9, 1);



            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public String getDepartmentName(int departmentCode) { // 引数をintに変更しました 2023-05-26-20:24 Shimanaka
        String departmentName = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String jdbcUrl = "jdbc:mysql://localhost/eimsdb?serverTimezone=Asia/Tokyo";
        String userId = "embuser";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, userId, password)){
            // 准备 SQL 查询语句
            String query = "SELECT department_name FROM department WHERE department_code = ?";
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, departmentCode); // setStringをsetIntに変更しました 2023-05-26-20:25 Shimanaka

            // 执行查询
            resultSet = preparedStatement.executeQuery();

            // 处理结果
            if (resultSet.next()) {
                departmentName = resultSet.getString("department_name");
            } else {
                System.out.println("未找到与部门代码对应的部门名称。");
            }
        } catch (SQLException e) {
            // 异常处理...
        } finally {
            // 关闭资源...
        }

        return departmentName;
    }
}
