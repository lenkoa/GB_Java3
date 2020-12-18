package lesson2;

import java.sql.*;

public class Main {

    public static final String TEST_GOOD_NAME = "test good";

    private static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn =
                         DriverManager.getConnection(
                                 "jdbc:sqlite:src/main/resources/mainDB.db")
            ) {

                createTable(conn);
                fillTableByRandomGoods(conn);
                checkGood(conn, TEST_GOOD_NAME);
                changePrice(conn, TEST_GOOD_NAME, 10);
                printGoods(conn, 0, 100);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("CREATE TABLE IF NOT EXISTS goods(\n" +
                "good_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "good_name TEXT NOT NULL,\n" +
                "good_price INTEGER NOT NULL\n" +
                ")");
    }

    private static void fillTableByRandomGoods(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("DELETE FROM goods WHERE id > 0;");

        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO goods (" +
                        "good_id, good_name, good_price" +
                        ") VALUES (?, ?, ?);")) {
            for (int i = 1; i <= 10000; i++) {
                ps.setInt(1, i);
                ps.setString(2, TEST_GOOD_NAME + " " + i);
                ps.setInt(3, (int) (Math.random() * 1000000));
                ps.addBatch();
            }
            int[] result = ps.executeBatch();
        }
    }

    private static void checkGood(Connection conn, String goodName) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT good_price FROM goods WHERE good_name LIKE '?'"
        )) {
            ps.setString(1, goodName);
            ResultSet rs = ps.executeQuery();

            boolean nullResult = true;
            while (rs.next()) {
                nullResult = false;
                System.out.println("good price for good " + goodName + " is " + rs.getInt("good_price"));
            }

            if (nullResult) System.out.println("Good " + goodName + " is not found in db.");
        }
    }

    private static void changePrice(Connection conn, String goodName, int newPrice) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE goods SET good_price = ? WHERE good_name LIKE '?'"
        )) {
            ps.setInt(1, newPrice);
            ps.setString(2, goodName);
            ps.executeQuery();
        }
    }

    private static void printGoods(Connection conn, int minPrice, int maxPrice) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT good_name, good_price FROM goods WHERE good_price BETWEEN ? AND ?"
        )) {
            ps.setInt(1, minPrice);
            ps.setInt(2, maxPrice);
            ResultSet rs = ps.executeQuery();

            boolean nullResult = true;
            while (rs.next()) {
                nullResult = false;
                System.out.println(rs.getString("good_name") + "\t\t\t" + rs.getInt("good_price"));
            }

            if (nullResult)
                System.out.println("Goods with price " + minPrice + " and " + maxPrice + " between are not found");
        }
    }
}
