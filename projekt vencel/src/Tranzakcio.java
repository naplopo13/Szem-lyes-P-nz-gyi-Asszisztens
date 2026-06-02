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

}