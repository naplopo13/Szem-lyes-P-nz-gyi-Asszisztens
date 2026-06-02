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

    public boolean isLimitTullepve(String kategoria) {
        double limit = getLimitKategoriaSzerint(kategoria);
        if (limit == 0) return false;
        return getKoltsegKategoriaSzerint(kategoria) > limit;
    }

    public double getOsszKoltseg() {
        return tranzakciok.stream().mapToDouble(Tranzakcio::getOsszeg).sum();
    }

    public String getAsciiDiagram(String kategoria) {
        double koltes = getKoltsegKategoriaSzerint(kategoria);
        double limit = getLimitKategoriaSzerint(kategoria);

        if (limit == 0) return kategoria + ": " + koltes + " Ft (Nincs limit beállítva)";

        double szazalek = (koltes / limit) * 100;
        int maxKarakter = 10;
        int kiertekeltKarakter = (int) Math.min(maxKarakter, Math.round(szazalek / 10));

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < maxKarakter; i++) {
            if (i < kiertekeltKarakter) sb.append("#");
            else sb.append(".");
        }