package infobrightdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InfobrightDBAccess {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;

    private String url = "jdbc:mysql://localhost:5029/";
    private String user = "root";
    private String password = "";



    private void connectToDB() throws Exception{
        try {
            System.out.println("Connecting to database...");
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, password);
            statement = connect.createStatement();
        }catch(SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void readDatabase() throws Exception{
        try{
            connectToDB();
            resultSet = statement.executeQuery("SELECT * FROM mysql.user");

            writeResultSet(resultSet);

//            dbInfo(resultSet);

        }catch(SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            close();
        }
    }


    public void insertData() throws SQLException {
        try {
            connectToDB();


        }catch(SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
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

        while(resultSet.next()){
            //Retrieve by column name
            String id  = resultSet.getString("host");
            //Display values
            System.out.print(" ID: " + id);
        }

//        while (resultSet.next()) {
//            for (int j = 1; j <= resultSet.getMetaData().getColumnCount(); j++) {
//                System.out.print(resultSet.getString(j) + "\t");
//            }
//            System.out.println("");
//        }
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
        }catch(SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
