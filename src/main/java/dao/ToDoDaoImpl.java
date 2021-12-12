package dao;

import models.ToDo;
import org.apache.log4j.Logger;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoDaoImpl implements ToDoDao{

    /*URL Syntax: jdbc:postgresql://your endpoint/database to connect to*/
    //String url = "jdbc:postgresql://jwa-db.cybvzaobiuaa.us-east-2.rds.amazonaws.com/tododatabase";
    // Use system variable to protect sensitive information (endpoint, username and password)
    String url;
    String username;
    String password;

    // create the logger object to log events on the file todo.log
    Logger logger = Logger.getLogger(ToDoDaoImpl.class);

    public ToDoDaoImpl(){
        this.url = "jdbc:postgresql://" + System.getenv("AWS_RDS_ENDPOINT") + "/tododatabase";
        this.username = System.getenv("RDS_USERNAME");
        this.password = System.getenv("RDS_PASSWORD");
    }

    public ToDoDaoImpl(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<ToDo> getAllToDos() {
        List<ToDo> todos = new ArrayList<>();

                //generate the connection
        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            // it is a good idea to run the sql statement in dbeaver first, copy and past here when it works.
            String sql = "SELECT * FROM todos;";
            PreparedStatement ps = conn.prepareStatement(sql); // protect the sql statement again attacks like sql injections.

            //execute the sql statement and return the result set (rs)
            ResultSet rs = ps.executeQuery();

            //iterate through the result set
            while(rs.next()){
                // rs objet is like a list of record returned by the sql statement.
                // In this example, we have 3 columns: col1: id(Integer), col2:(String), col3:(Boolean)
                todos.add(new ToDo(rs.getInt(1), rs.getString(2), rs.getBoolean(3)));
            }
            conn.close(); // Not needed because the con obj declaration is given as argument in the try.

        }catch(SQLException e){
           // e.printStackTrace();     replaced by the logging
            logger.error(e); // Implement logging for sql exceptions
        }
        return todos;

    }

    @Override
    public ToDo getOneToDo(Integer toDoId) {
        ToDo todo = null;

        try(Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "SELECT * FROM todos WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            // set the value of the question mark in the sql
            // This line is needed when the sql statement has a parameter (?)
            ps.setInt(1, toDoId);

            //execute the sql statement and return the result set (rs)
            ResultSet rs = ps.executeQuery();

            //iterate through the result set
            while(rs.next()){
                todo = new ToDo(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
            }

            conn.close(); // Not needed because the con obj declaration is put in the try.

        }catch(SQLException e){
            logger.error(e);
        }

        return todo;
    }

    @Override
    public void createToDo(ToDo toDo) {

        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            //generate the connection

            String sql = "INSERT INTO todos VALUES (DEFAULT, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, toDo.getTask()); // for the first ?
            ps.setBoolean(2, toDo.isCompleted()); // for the second ?

            ps.executeUpdate();

        }catch(SQLException e){
            logger.error(e);
        }
    }

    @Override
    public void updateAToDo(Integer toDoId) {

        try(Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "UPDATE todos SET completed = ?  WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1,true);
            ps.setInt(2, toDoId);

            ps.executeUpdate();

        }catch(SQLException e){
            logger.error(e);
        }
    }

    @Override
    public void deleteAToDo(Integer toDoId) {
        try(Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "DELETE FROM todos WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, toDoId);

            ps.executeUpdate();

        }catch(SQLException e){
            logger.error(e);
        }

    }
}
