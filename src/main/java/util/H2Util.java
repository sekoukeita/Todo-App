package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2Util {

    public static String url = "jdbc:h2:./h2/db"; // where the H2 database will be stored(./h2/db)
    public static String username = "sa";
    public static String password = "sa";

    public static void createTable(){
        try(Connection conn = DriverManager.getConnection(url, username, password);){
            String sql = "CREATE TABLE todos(\n" +
                    "    id serial PRIMARY KEY,\n" +
                    "    task varchar(100) NOT NULL,\n" +
                    "    completed boolean DEFAULT false\n" +
                    ");";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }

    public static void dropTable(){
        try(Connection conn = DriverManager.getConnection(url, username, password);){
            String sql = "DROP TABLE todos;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
