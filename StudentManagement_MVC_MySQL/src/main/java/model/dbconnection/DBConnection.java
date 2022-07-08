package model.dbconnection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

    //Singleton Design Pattern
    private static DBConnection uniqueInstance = new DBConnection();

    private static final String DATABASE_NAME = "student_management";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String PORT = "3306";
    private static final String HOST = "localhost";

    private DBConnection() {
    }

    public static DBConnection getInstance() {
        return uniqueInstance;
    }

    public Connection open() throws DBConnectionException {
        try {
            String url = String.format("jdbc:mysql://%s:%s/%s", HOST, PORT, DATABASE_NAME);
            Class.forName(DRIVER);
            return DriverManager.getConnection(url, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void close(ResultSet rs, PreparedStatement ps, Connection c) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if (c != null && !c.isClosed()) {
                c.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
