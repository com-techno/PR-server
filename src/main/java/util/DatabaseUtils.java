package util;

import org.sqlite.JDBC;

import java.sql.*;


public class DatabaseUtils {
    public Connection conn;
    //public Statement statement;
    //public static ResultSet resSet;

    public DatabaseUtils() throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        DriverManager.registerDriver(new JDBC());
        conn = DriverManager.getConnection("jdbc:sqlite:C:\\_MyProjects\\Idea\\PR-server\\database.db");
    }

    public void execSqlUpdate(String sql) throws SQLException {
        conn.createStatement().executeUpdate(sql);
    }

    public ResultSet execSqlQuery(String sql) throws SQLException {
        return conn.createStatement().executeQuery(sql);
    }

    // --------Закрытие--------
    public void CloseDB() throws ClassNotFoundException, SQLException {
        conn.close();
        //  statement.close();
    }

}
