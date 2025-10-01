package src;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class DBHandler {

    public static LinkedList selectColony(String name, int moreThan, int lessThan, int governmentID, int purposeID, int typeID, int CBID) {
        //metod izpulnyavasht selektirashta zayavka spryamo poletata za zapisi ot Colony
        String query = "SELECT Colony.Colony_Name, Colony.Population, Colony.Government_ID, Colony.Purpose_ID, Colony.Type_ID, Colony.CB_ID FROM Colony";
        int columnCount = 0;
        boolean[] emptyField = new boolean[7];
        emptyField[0] = name.equals("");
        emptyField[1] = moreThan == 0;
        emptyField[2] = lessThan == 0;
        emptyField[3] = governmentID == 0;
        emptyField[4] = purposeID == 0;
        emptyField[5] = typeID == 0;
        emptyField[6] = CBID == 0;
        for (boolean i : emptyField) {
            if (i == false) {
                ++columnCount;
            }
        }
        if (columnCount > 0) {
            query += " WHERE (";
            boolean notFirst = false;
            if (!emptyField[0]) {
                query += "((Colony.Colony_Name) Like \"" + name + "\")";
                notFirst = true;
            }
            if (!emptyField[1]) {
                if (notFirst) {
                    query += "AND";
                }
                query += "((Colony.Population)>" + moreThan + ")";
                notFirst = true;
            }
            if (!emptyField[2]) {
                if (notFirst) {
                    query += "AND";
                }
                query += "((Colony.Population)<" + lessThan + ")";
                notFirst = true;
            }
            if (!emptyField[3]) {
                if (notFirst) {
                    query += "AND";
                }
                query += "((Colony.Government_ID)=" + governmentID + ")";
                notFirst = true;
            }
            if (!emptyField[4]) {
                if (notFirst) {
                    query += "AND";
                }
                query += "((Colony.Purpose_ID)=" + purposeID + ")";
                notFirst = true;
            }
            if (!emptyField[5]) {
                if (notFirst) {
                    query += "AND";
                }
                query += "((Colony.Type_ID)=" + typeID + ")";
                notFirst = true;
            }
            if (!emptyField[6]) {
                if (notFirst) {
                    query += "AND";
                }
                query += "((Colony.CB_ID)=" + CBID + ")";
            }
            query += ");";
        }

        return selectingQuery(query, 6);
    }

    public static void updateColony(String name, int population, int governmentID, int purposeID, int typeID, int CBID) {
        //metod izpulnayavasht update-vashta zayavka s poletata kato novite danni
        updatingQuery("UPDATE Colony SET Colony.Population = " + population + ", Colony.Government_ID = " + governmentID + ", Colony.Purpose_ID = " + purposeID + ", Colony.Type_ID = " + typeID + ", Colony.CB_ID = " + CBID + " WHERE (((Colony.Colony_Name) Like \"" + name + "\"));");
    }

    public static void addColony(String name, int population, int governmentID, int purposeID, int typeID, int CBID) {
        //metod za dobavyashta zayavka, kato poletata shte sa novite danni
        updatingQuery("INSERT INTO Colony (`Colony_Name`,`Population`,`Government_ID`,`Purpose_ID`,`Type_ID`,`CB_ID`) VALUES (\"" + name + "\", " + population + ", " + governmentID + ", " + purposeID + ", " + typeID + ", " + CBID + ")");
    }

    public static void deleteColony(String name) {
        //iztrivashta zayavka, kato iztriva po ime
        updatingQuery("DELETE FROM Colony WHERE (`Colony_Name`) Like (\"" + name + "\")");
    }

    public static String denormalize(int table, int index) {
        //selektirashta zayavka, no samo za chetirite stranichni tablici, kato vrushta stoynostta suotvetstvashta na daden index
        String tableName = "";
        switch (table) {
            case 1:
                tableName = "Government";
                break;
            case 2:
                tableName = "Purpose";
                break;
            case 3:
                tableName = "Type";
                break;
            case 4:
                tableName = "CelestialBody";
                break;
        }
        String query = "SELECT " + tableName + ".Description FROM " + tableName + " WHERE (((" + tableName + ".ID)=" + index + "));";

        if(!selectingQuery(query, 1).isEmpty()){
            return selectingQuery(query, 1).get(0) + "";
        } else {
            return "";
        }
    }

    private static LinkedList selectingQuery(String query, int columns) {
        //pravi direktnata vruzka za selektivna zayavka
        LinkedList resultArr = new LinkedList();
        try {
            Connection conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/spacecolonies", "root", "");
            Statement statement = conn.createStatement();
            ResultSet rawData = statement.executeQuery(query);
            while (rawData.next()) {
                for (int column = 1; column <= columns; ++column) {
                    resultArr.add(rawData.getString(column));
                }
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return resultArr;
    }

    private static void updatingQuery(String query) {
        //pravi direktna vruzka za vsichki vidove zayavki osven selektivna
        try {
            Connection conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/spacecolonies", "root", "");
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
