package mysqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private String url = "jdbc:mysql://localhost:3306/";
    private String user = "root";
    private String password = "";


    private void connectToDB() throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, password);
            statement = connect.createStatement();
        }catch(SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void readDataBase() throws Exception {
        try {
            connectToDB();

            resultSet = statement.
                    executeQuery("select a.mach, b.tat " +
                            "from mgr.dane a inner join mgr.back b " +
                            "on a.ffa=b.ffa " +
                            "where b.gm = 60000;");

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

            for(int i=0; i<10000; i++){
                statement.executeUpdate("INSERT INTO mgr.baza " +
                                "VALUES (10950.5, 0.8, 251.10, 60234.0, 0.299);");
            }

        }catch(SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }


    public void deleteRow(double mach) throws SQLException {
        try {
            connectToDB();

            preparedStatement = connect
                    .prepareStatement("DELETE FROM mgr.baza WHERE mach= ? ;");
            preparedStatement.setDouble(1, mach);
            preparedStatement.execute();

        }catch(SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }


    public void updateRow() throws SQLException {
        try {
            connectToDB();

            statement.executeUpdate("UPDATE mgr.baza SET alt_std = alt_std + 0.5, tat = tat + 0.3, ffa = ffa + 0.01 " +
                    "WHERE mach between 0.77 AND 0.85;");

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
