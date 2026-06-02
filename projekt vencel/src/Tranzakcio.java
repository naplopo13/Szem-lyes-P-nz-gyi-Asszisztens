import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

class Tranzakcio{
    private double osszeg;
    private String datum;
    private String kategoria;
    private String megjegyzes;

    public Tranzakcio(double osszeg, String datum, String kategoria, String megjegyzes) {
        this.osszeg = osszeg;
        this.datum = datum;
        this.kategoria = kategoria.toLowerCase();
        this.megjegyzes = megjegyzes;
    }

    public double getOsszeg() {
        return osszeg;
    }
    public String getDatum() {
        return datum;
    }
    public String getKategoria() {
        return kategoria;
    }
    public String getMegjegyzes() {
        return megjegyzes;
    }

    @Override
    public String toString(){
        return osszeg+";"+datum+";"+kategoria+";"+megjegyzes;
    }

    public static Tranzakcio fromString(String sor){
        String[] darabok=sor.split(";");
        if (darabok.length<4)return null;
        return new Tranzakcio(Double.parseDouble(darabok[0]),darabok[1],darabok[2],darabok[3] );
    }
}
class Limit{
    private String kategoria;
    private double maxOsszeg;

    public Limit(String kategoria, double maxOsszeg){
        this.kategoria=kategoria.toLowerCase();
        this.maxOsszeg=maxOsszeg;
    }
    @Override
    public String toString() {
        return kategoria + ";" + maxOsszeg;
    }
    public static Limit fromString(String sor){
        String[] darabok = sor.split(";");
        if (darabok.length < 2) return null;
        return new Limit(darabok[0], Double.parseDouble(darabok[1]));
    }
}

public class DataManager{
    private final String TRANZAKCIO_FAJL = "tranzakciok.txt";
    private final String LIMIT_FAJL = "limitek.txt";
    public void mentTranzakciok(List<Tranzakcio> list) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(TRANZAKCIO_FAJL))) {
            for (Tranzakcio t : list) pw.println(t.toString());
        } catch (IOException e) {
            System.err.println("Hiba a tranzakciók mentésekor: " + e.getMessage());
        }
    }
}