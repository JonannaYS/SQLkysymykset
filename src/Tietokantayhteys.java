import java.sql.*;

public class Tietokantayhteys {
    Connection con;

    public static void yhteydenLuonti() {
        try (Connection con = getConnection()) {
            System.out.println("Yhteys luotu");
        } catch (SQLException kukka) {
            kukka.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/SQLkysymykset?useSSL=false&serverTimezone=UTC", "root", "salasana");
    }

    public Connection getCon() {
        return con;
    }
}
