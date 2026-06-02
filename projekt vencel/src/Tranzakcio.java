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
}