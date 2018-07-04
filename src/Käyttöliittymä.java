import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Käyttöliittymä {
    public static void kysymystenHakija(Connection con) throws SQLException {
        Scanner sc = new Scanner(System.in);
//        PreparedStatement ps = con.prepareStatement("select * from kysymykset");
//        ResultSet rskys = ps.executeQuery();
//        ps = con.prepareStatement("select * from vastaukset");
//        ResultSet rsvas = ps.executeQuery();
        for (int i = 1; i < 11; i++) {
            PreparedStatement ps = con.prepareStatement("select * from kysymykset where kysymysID=?");
            ResultSet rskys = ps.executeQuery();
            ps = con.prepareStatement("select * from vastaukset where kysymysID=?");
            ResultSet rsvas = ps.executeQuery();
            System.out.println(rskys.getString(1));
            System.out.println(rsvas.);
        }
    }
}
