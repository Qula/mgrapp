package mysqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private String url = "jdbc:mysql://localhost:3306/";
    private String user = "root";
    private String password = "";


    private void connectToDB() throws Exception{
        //Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection(url, user, password);
        statement = connect.createStatement();
    }


    public void readDataBase() throws Exception {
        try {
            connectToDB();

            resultSet = statement
                    .executeQuery("select * from mgr.test");
            writeResultSet(resultSet);

            resultSet = statement
                    .executeQuery("select * from mgr.test");
            dbInfo(resultSet);

        } catch (Exception e) {

        } finally {
            close();
        }
    }


    public void insertData() throws SQLException {
        try {
            connectToDB();

            preparedStatement = connect
                    .prepareStatement("insert into  mgr.test values (default, ?, ?)");

            preparedStatement.setString(1, "Test"); // start with 1
            preparedStatement.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }


    public void deleteRow(int i) throws SQLException {
        try {
            connectToDB();

            preparedStatement = connect
                    .prepareStatement("delete from mgr.test where id= ? ; ");
            preparedStatement.setInt(1, i);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }


    private void dbInfo(ResultSet resultSet) throws SQLException {

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " + i + ": " + resultSet.getMetaData().getColumnName(i));
        }
    }


    private void writeResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String text = resultSet.getString("text");
            Date date = resultSet.getDate("date");
            System.out.println("ID: " + id);
            System.out.println("Text: " + text);
            System.out.println("Date: " + date);
            System.out.println("- - - - -");
        }
    }


    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
