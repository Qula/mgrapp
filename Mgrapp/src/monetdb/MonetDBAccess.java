package monetdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MonetDBAccess {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;

    private String url ="jdbc:monetdb://127.0.0.1:50000/";
    private String user = "voc";
    private String password = "voc";

    private void connectToDB() throws Exception{
        Class.forName("nl.cwi.monetdb.jdbc.MonetDriver");
        connect = DriverManager.getConnection(url, user, password);
        statement = connect.createStatement();
    }


    public void readDataBase() throws Exception {
        try {
            connectToDB();

            resultSet = statement.executeQuery("SELECT * FROM soldiers;");
            writeResultSet(resultSet);

            dbInfo(resultSet);

        }catch (Exception e){

        }finally {
            close();
        }
    }


    public void insertData() throws SQLException {
        try {
            connectToDB();

            preparedStatement = connect
                    .prepareStatement("insert into  passengers values (125, '' , ?, null ,90, null, null, null, null, null)");

            preparedStatement.setInt(1, 3); // start with 1
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
                    .prepareStatement("delete from passengers where number= ? ; ");
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
            for (int j = 1; j <= resultSet.getMetaData().getColumnCount(); j++) {
                System.out.print(resultSet.getString(j) + "\t");
            }
            System.out.println("");
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
