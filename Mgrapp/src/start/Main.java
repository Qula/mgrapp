package start;

import mysqldb.MySQLAccess;

public class Main {
    public static void main(String[] args) throws Exception {
        MySQLAccess dao = new MySQLAccess();
        dao.readDataBase();
//        dao.insertData();
//        dao.deleteRow(5);

    }
}
