package model.dao;

import model.dao.exception.*;
import model.dbconnection.DBConnection;
import model.dbconnection.DBConnectionException;
import model.entity.Clazz;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ClassroomDAO implements IDAO<Clazz, String> {

    private static String sqlInsert = "insert into classroom(idclassroom, name) values (?, ?)";
    private static String sqlSelectAll = "SELECT * FROM student_management.classroom;";
    private static String sqlDeleteById = "delete from classroom where idclassroom=?";
    private static String sqlUpdate = "update classroom set name=? where idclassroom=?";
    private static String sqlFindById = "select * from classroom where idclassroom=?";

    @Override
    public Clazz insert(Clazz classroom) throws DBConnectionException, InsertExceptions {
        Connection c = DBConnection.getInstance().open();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(sqlInsert);
            ps.setString(1, classroom.getId());
            ps.setString(2, classroom.getName());
            int row = ps.executeUpdate();
            if (row > 0) {
                return classroom;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().close(null, ps, c);
        }
        return null;
    }

    @Override
    public Clazz updateById(Clazz classroom) throws DBConnectionException, UpdateExceptions {
        Connection c = DBConnection.getInstance().open();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(sqlUpdate);
            ps.setString(1, classroom.getName());
            ps.setString(2, classroom.getId());
            int row = ps.executeUpdate();
            if (row > 0) {
                return classroom;
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

    //can not set list students to classroom
    @Override
    public Map<String, Clazz> getAll() throws DBConnectionException, GetAllExceptions {
        Connection c = DBConnection.getInstance().open();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String, Clazz> list = new HashMap<>();
        try {
            ps = c.prepareStatement(sqlSelectAll);
            rs = ps.executeQuery();
            while (rs.next()) {
                Clazz classroom = new Clazz();
                classroom.setId(rs.getString("idclassroom"));
                classroom.setName(rs.getString("name"));
                list.put(classroom.getId(), classroom);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().close(rs, ps, c);
        }
    }

    //can not set list students to classroom
    @Override
    public Clazz findById(String s) throws DBConnectionException, FindByIDExceptions {
        Connection c = DBConnection.getInstance().open();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement(sqlFindById);
            ps.setString(1, s);
            rs = ps.executeQuery();
            while (rs.next()) {
                Clazz classroom = new Clazz();
                classroom.setId(rs.getString("idclassroom"));
                classroom.setName(rs.getString("name"));
                return classroom;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().close(rs, ps, c);
        }
        return null;
    }
}
