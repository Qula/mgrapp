package start;

import mysqldb.MySQLAccess;
import monetdb.MonetDBAccess;
import infobrightdb.InfobrightDBAccess;

public class Main {
    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        MySQLAccess mysql = new MySQLAccess();
        mysql.readDataBase();
//        mysql.insertData();
//        mysql.deleteRow(0.8);
//        mysql.updateRow();

        long elapsedTimeMillis = System.currentTimeMillis()-start;
        float elapsedTimeSec = elapsedTimeMillis/1000F;
        System.out.println("MySQL: " + elapsedTimeSec + "s");


        start = System.currentTimeMillis();

        MonetDBAccess monetdb = new MonetDBAccess();
        monetdb.readDataBase();
//        monetdb.insertData();
//        monetdb.deleteRow(0.8);
//        monetdb.updateRow();

        elapsedTimeMillis = System.currentTimeMillis()-start;
        elapsedTimeSec = elapsedTimeMillis/1000F;
        System.out.println("MonetDB: " + elapsedTimeSec + "s");


        start = System.currentTimeMillis();

        InfobrightDBAccess infobrightdb  = new InfobrightDBAccess();
        infobrightdb.readDatabase();

        elapsedTimeMillis = System.currentTimeMillis()-start;
        elapsedTimeSec = elapsedTimeMillis/1000F;
        System.out.println("Infobright: " + elapsedTimeSec + "s");
    }
}
