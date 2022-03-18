package application;

import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String dbUrl = "jdbc:sqlite:people.db";
        var connection = DriverManager.getConnection(dbUrl);

        var stmt = connection.createStatement();
        var sql = "create table if not exists user (id integer primary key, name text not null)";

        stmt.execute(sql);

        sql="drop table user";
        stmt.execute(sql);
        stmt.close();
        connection.close();

    }
}
