package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo {

    static final String URL = "jdbc:mysql://localhost:3306/student_management";
    static final String USER = "root";
    static final String PASSWORD = "123456";


    public static void main(String[] args) {
        Connection connection = null;
        Statement insert = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            insert = connection.createStatement();
            int update = insert.executeUpdate("insert into student(id, fullname, birthday, gender, address, phone, email) values ('SE190000', 'Nguyen Van A', '2005-01-01', 1, 'Ho Chi Minh', '0123456789', 'anvse190000@fpt.edu.vn')");
            if (update > 0) {
                System.out.println("Successfully");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                insert.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
