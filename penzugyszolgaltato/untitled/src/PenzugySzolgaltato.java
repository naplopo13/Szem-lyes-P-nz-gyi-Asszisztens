import java.util.*;

public class PenzugySzolgaltato {
    private List<Tranzakcio> tranzakciok;
    private List<Limit> limitek;
    private DataManager dataManager;

    public PenzugySzolgaltato() {
        this.dataManager = new DataManager();
        this.tranzakciok = dataManager.betoltTranzakciok();
        this.limitek = dataManager.betoltLimitek();
    }

    public void ujTranzakcio(double osszeg, String datum, String kategoria, String megjegyzes) {
        tranzakciok.add(new Tranzakcio(osszeg, datum, kategoria, megjegyzes));
        dataManager.mentTranzakciok(tranzakciok);
    }

    public void ujLimit(String kategoria, double maxOsszeg) {
        limitek.removeIf(l -> l.getKategoria().equalsIgnoreCase(kategoria));
        limitek.add(new Limit(kategoria, maxOsszeg));
        dataManager.mentLimitek(limitek);
    }

    public double getKoltsegKategoriaSzerint(String kategoria) {
        return tranzakciok.stream()
                .filter(t -> t.getKategoria().equalsIgnoreCase(kategoria))
                .mapToDouble(Tranzakcio::getOsszeg)
                .sum();
    }

    public double getLimitKategoriaSzerint(String kategoria) {
        return limitek.stream()
                .filter(l -> l.getKategoria().equalsIgnoreCase(kategoria))
                .mapToDouble(Limit::getMaxOsszeg)
                .findFirst()
                .orElse(0.0);
    }