package model.dao;

import model.dao.exception.*;
import model.dbconnection.DBConnection;
import model.dbconnection.DBConnectionException;
import model.entity.Student;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import model.entity.Clazz;
import model.entity.Gender;

public class StudentDAO implements IDAO<Student, String> {

    private Map<String, Clazz> classrooms;
    private static final String sqlInsert = "insert into student(idstudent, idclassroom, fullname, birthday, gender ) values (?, ?, ?, ?, ?)";
    private static final String sqlUpdate = "update student set idclassroom=?, fullname=?, birthday=?, gender=? where idstudent=?";
    private static final String sqlDeleteById = "delete from student where idstudent=?";
    private static final String sqlSelectAll = "SELECT * FROM student_management.student;";
    private static final String sqlFindById = "select * from student where idstudent=?";
    private static final String sqlFindByClassroom = "SELECT * FROM student_management.student where idclassroom=?;";

    public StudentDAO() {
        try {
            classrooms = new ClassroomDAO().getAll();
        } catch (DBConnectionException | GetAllExceptions ex) {
            System.err.println("Failure this here.");
        }
    }

    @Override
    public Student insert(Student student) throws DBConnectionException, InsertExceptions {
        Connection c = DBConnection.getInstance().open();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(sqlInsert);
            ps.setString(1, student.getId());
            ps.setString(2, student.getClassroom().getId());
            ps.setString(3, student.getFullName());
            ps.setDate(4, new Date(student.getBirthday().getTime()));
            ps.setInt(5, student.getGender().getCode());
            int row = ps.executeUpdate();
            if (row > 0) {
                return student;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().close(null, ps, c);
        }
        return null;
    }

    @Override
    public Student updateById(Student student) throws DBConnectionException, UpdateExceptions {
        Connection c = DBConnection.getInstance().open();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(sqlUpdate);
            ps.setString(1, student.getClassroom().getId());
            ps.setString(2, student.getFullName());
            ps.setDate(3, new Date(student.getBirthday().getTime()));
            ps.setInt(4, student.getGender().getCode());
            ps.setString(5, student.getId());
            int row = ps.executeUpdate();
            if (row > 0) {
                return student;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().close(null, ps, c);
        }
        return null;
    }

    @Override
    public boolean deleteById(String id) throws DBConnectionException, DeleteExceptions {
        Connection c = DBConnection.getInstance().open();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(sqlDeleteById);
            ps.setString(1, id);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().close(null, ps, c);
        }
        return false;
    }

    @Override
    public Map<String, Student> getAll() throws DBConnectionException, GetAllExceptions {
        Connection c = DBConnection.getInstance().open();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String, Student> list = null;
        try {
            ps = c.prepareStatement(sqlSelectAll);
            rs = ps.executeQuery();
            list = new HashMap<>();

            while (rs.next()) {
                Student st = new Student();
                st.setClassroom(classrooms.get(rs.getString("idclassroom")));
                st.setId(rs.getString("idstudent"));
                st.setFullName(rs.getString("fullname"));
                st.setBirthday(rs.getDate("birthday"));
                st.setGender(Gender.getGender(rs.getInt("gender")));
                list.put(st.getId(), st);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().close(rs, ps, c);
        }
        return list;
    }

    @Override
    public Student findById(String s) throws DBConnectionException, FindByIDExceptions {
        Connection c = DBConnection.getInstance().open();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student st = null;
        try {
            st = new Student();
            ps = c.prepareStatement(sqlFindById);
            ps.setString(1, s);
            rs = ps.executeQuery();
            while (rs.next()) {
                st.setClassroom(classrooms.get(rs.getString("idclassroom")));
                st.setId(rs.getString("idstudent"));
                st.setFullName(rs.getString("fullname"));
                st.setBirthday(rs.getDate("birthday"));
                st.setGender(Gender.getGender(rs.getInt("gender")));
                return st;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().close(rs, ps, c);
        }
        return null;
    }

    public Map<String, Student> findByClassroom(String idclassroom) throws DBConnectionException {
        Connection c = DBConnection.getInstance().open();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student st = null;
        Map<String, Student> list = new HashMap<>();
        try {
            st = new Student();
            ps = c.prepareStatement(sqlFindByClassroom);
            ps.setString(1, idclassroom);
            rs = ps.executeQuery();
            while (rs.next()) {
                st.setClassroom(classrooms.get(rs.getString("idclassroom")));
                st.setId(rs.getString("idstudent"));
                st.setFullName(rs.getString("fullname"));
                st.setBirthday(rs.getDate("birthday"));
                st.setGender(Gender.getGender(rs.getInt("gender")));
                list.put(st.getId(), st);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().close(rs, ps, c);
        }
        return list;
    }
}
