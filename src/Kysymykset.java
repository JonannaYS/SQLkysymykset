import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Kysymykset {
    public static void kysymystenLukija(Connection con) throws SQLException {
        try (Scanner lukija = new Scanner(new File("kysymykset.txt"))) {
            int kysymysID = 0;
            boolean onkoOikein = false;
            PreparedStatement ps;
            ps=con.prepareStatement("DROP TABLE vastaukset;");
            ps.executeUpdate();
            ps=con.prepareStatement("DROP TABLE kysymykset;");
            ps.executeUpdate();
            ps = con.prepareStatement("CREATE TABLE kysymykset (kysymysID int PRIMARY KEY NOT NULL AUTO_INCREMENT, kysymys varchar(255));");
            ps.executeUpdate();
            ps=con.prepareStatement("CREATE TABLE vastaukset(vastausID int PRIMARY KEY NOT NULL AUTO_INCREMENT, vastaus varchar(255) NOT NULL, onkoOikein boolean, kysymysID int, CONSTRAINT vastaukset_kysymykset_kysymysID_fk FOREIGN KEY (kysymysID) REFERENCES kysymykset (kysymysID));");
            ps.executeUpdate();
            while (lukija.hasNextLine()) {
                String tilap = lukija.nextLine();
                if (tilap.isEmpty()){
                    continue;
                } else if (tilap.substring(0,1).matches("[0-9]")){
                    tilap = tilap.substring(tilap.indexOf(" ")+1);
                    ps = con.prepareStatement("INSERT INTO kysymykset(kysymys) VALUES (?)");
                    kysymysID++;
                    ps.setString(1,tilap);
                } else {
                    if (tilap.lastIndexOf("x".toLowerCase())==tilap.length()-1){
                        tilap=tilap.substring(0,tilap.length()-2);
                        onkoOikein=true;
                    } else {
                        onkoOikein=false;
                    }
                    ps = con.prepareStatement("INSERT INTO vastaukset(vastaus, onkoOikein, kysymysID) VALUES (?,?,?)");
                    ps.setString(1, tilap);
                    ps.setBoolean(2, onkoOikein);
                    ps.setInt(3, kysymysID);
                }

                ps.executeUpdate();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
