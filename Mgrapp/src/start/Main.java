package start;

import mysqldb.MySQLAccess;
import monetdb.MonetDBAccess;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws Exception {
        MySQLAccess dao = new MySQLAccess();
//        dao.readDataBase();
//        dao.insertData();
//        dao.deleteRow(5);

        MonetDBAccess monetdb = new MonetDBAccess();
        monetdb.readDataBase();
//        monetdb.insertData();
//        monetdb.deleteRow(124);



    }
}
