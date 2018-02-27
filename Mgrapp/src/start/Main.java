package start;

import mysqldb.MySQLAccess;
import monetdb.MonetDBAccess;
import infobrightdb.InfobrightDBAccess;

public class Main {
    public static void main(String[] args) throws Exception {

        String[] dbNames = {
                "mgr.bazaa",
                "mysql.bazaa",
                "bazaa",
                "mgr.bazad",
                "mysql.bazad",
                "bazad"
        };
        String[] queryTab = {
                "SELECT count(*) FROM X;",
                "SELECT count(*) FROM X WHERE ffa>0.3;",
                "SELECT avg(mach), avg(tat), avg(gm) FROM X;",
                "SELECT min(mach), min(tat), min(gm) FROM X;",
                "SELECT max(mach), max(tat), max(gm) FROM X",
                "SELECT mach FROM X;",
                "SELECT * FROM X;",
                "SELECT a.mach, b.tat FROM X a INNER JOIN Y b ON a.ffa=b.ffa LIMIT 100;",
                "SELECT a.mach, b.tat FROM X a INNER JOIN Y b ON a.ffa=b.ffa WHERE b.gm = 60000;",
                "INSERT INTO X VALUES (10950.5, 0.8, 251.10, 60234.0, 0.299);",
                "UPDATE X SET alt_std = alt_std * 0.2;",
                "DELETE FROM X WHERE gm = 59320.0;"

        };
        int idx = 0;

        long start = System.currentTimeMillis();

        MySQLAccess mysql = new MySQLAccess();
        mysql.readDataBase(queryTab[idx].replaceAll("X", dbNames[0]).replaceAll("Y", dbNames[3]));
        //mysql.insertData(queryTab[idx].replaceAll("X", dbNames[0]).replaceAll("Y", dbNames[3]));
        //mysql.deleteRow(queryTab[idx].replaceAll("X", dbNames[0]).replaceAll("Y", dbNames[3]));
        //mysql.updateRow(queryTab[idx].replaceAll("X", dbNames[0]).replaceAll("Y", dbNames[3]));

        long elapsedTimeMillis = System.currentTimeMillis()-start;
        float elapsedTimeSec = elapsedTimeMillis/1000F;
        System.out.println("MySQL: " + elapsedTimeSec + "s");


        start = System.currentTimeMillis();

        MonetDBAccess monetdb = new MonetDBAccess();
        monetdb.readDataBase(queryTab[idx].replaceAll("X", dbNames[2]).replaceAll("Y", dbNames[5]));
        //monetdb.insertData(queryTab[idx].replaceAll("X", dbNames[2]).replaceAll("Y", dbNames[5]));
        //monetdb.deleteRow(queryTab[idx].replaceAll("X", dbNames[2]).replaceAll("Y", dbNames[5]));
        //monetdb.updateRow(queryTab[idx].replaceAll("X", dbNames[2]).replaceAll("Y", dbNames[5]));

        elapsedTimeMillis = System.currentTimeMillis()-start;
        elapsedTimeSec = elapsedTimeMillis/1000F;
        System.out.println("MonetDB: " + elapsedTimeSec + "s");


        start = System.currentTimeMillis();

        InfobrightDBAccess infobrightdb  = new InfobrightDBAccess();
        infobrightdb.readDatabase(queryTab[idx].replaceAll("X", dbNames[1]).replaceAll("Y", dbNames[4]));

        elapsedTimeMillis = System.currentTimeMillis()-start;
        elapsedTimeSec = elapsedTimeMillis/1000F;
        System.out.println("Infobright: " + elapsedTimeSec + "s");
    }
}
