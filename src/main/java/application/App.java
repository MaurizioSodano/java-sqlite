package application;

import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        int[] ids={0,1,2};
        String[] names ={"Sue","Bob","Jhoan"};

        Class.forName("com.mysql.cj.jdbc.Driver");
        String dbUrl = "jdbc:mysql://localhost:3306/people";
        var connection = DriverManager.getConnection(dbUrl,"root","Mercogli@no2022M");
        connection.setAutoCommit(false);
        var stmt = connection.createStatement();
        var sql = "create table if not exists user (id integer primary key, name text not null)";
        //stmt.execute(sql);

        sql="insert into user (id, name) values (?, ?)";
        var insertStmt=connection.prepareStatement(sql);
        for (int i=0;i<ids.length;i++){
            insertStmt.setInt(1,ids[i]);
            insertStmt.setString(2,names[i]);
            insertStmt.executeUpdate();
        }
        connection.commit();
        insertStmt.close();


        sql="select id, name from user";
        var rs=stmt.executeQuery(sql);

        while(rs.next()){
            int id=rs.getInt("id");
            String name= rs.getString("name");
            System.out.println(id + ":"+name);
        }

        stmt.close();
        connection.close();

    }
}
