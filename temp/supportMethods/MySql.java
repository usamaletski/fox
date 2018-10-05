package supportMethods;

import testRunner.TestRunner;

import java.sql.*;

public class MySql {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet resultSet = null;

    private static void SetUpConnection() throws ClassNotFoundException, SQLException {
        String dbUrl = TestRunner.mysql.get("dbUrl");
        String username = TestRunner.mysql.get("dbUname");
        String password = TestRunner.mysql.get("dbPwd");
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(dbUrl, username, password);
    }

    public static void QueryExecution(String query) throws ClassNotFoundException, SQLException {
        SetUpConnection();
        stmt = conn.createStatement();
        stmt.execute(query);
        CloseConnection();
    }

    private static void CloseConnection() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
    }
}