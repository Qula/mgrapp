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
        try {
            Class.forName("nl.cwi.monetdb.jdbc.MonetDriver");
            connect = DriverManager.getConnection(url, user, password);
            statement = connect.createStatement();
        }catch(SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void readDataBase(String query) throws Exception {
        try {
            connectToDB();

            System.out.println(query);

            resultSet = statement.
                    executeQuery(query);

        }catch(SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            close();
        }
    }


    public void insertData(String query) throws SQLException {
        try {
            connectToDB();

            System.out.println(query);

            for(int i=0; i<1000; i++){
                statement.executeUpdate(query);
            }

        }catch(SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }


    public void deleteRow(String query) throws SQLException {
        try {
            connectToDB();

            System.out.println(query);

            statement.executeUpdate(query);

        }catch(SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }


    public void updateRow(String query) throws SQLException {
        try {
            connectToDB();

            System.out.println(query);

            statement.executeUpdate(query);

        }catch(SQLException se){
            se.printStackTrace();
        }catch (Exception e){
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
