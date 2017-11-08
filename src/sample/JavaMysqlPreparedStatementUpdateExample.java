package sample;

import java.sql.*;

/**
 * A Java MySQL UPDATE example.
 * Demonstrates the use of a SQL UPDATE statement against a
 * MySQL database, called from a Java program.
 *
 * Created by Alvin Alexander, http://devdaily.com
 *
 */
public class JavaMysqlPreparedStatementUpdateExample
{

    public static void main(String[] args)
    {
        try
        {
            // create a java mysql database connection
            //String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:sqlite:/src/sample/bus.db";
            //Class.forName(myDriver);
            Connection conn = DriverManager.getConnection("myUrl");

            // create the java mysql update preparedstatement
            String query = "update user set num_points = ? where first_name = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt   (1, 6000);
            preparedStmt.setString(2, "Fred");

            // execute the java preparedstatement
            preparedStmt.executeUpdate();

            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}