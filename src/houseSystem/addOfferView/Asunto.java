package houseSystem.addOfferView;

import houseSystem.Kuntoluokka;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Asunto {
    private String kuvaTiedosto;
    private SimpleStringProperty nimi;
    private SimpleIntegerProperty vuokra;
    private String osoite;
    private SimpleIntegerProperty rakennusvuosi;
    private SimpleStringProperty kuntoluokka;
    private Kuntoluokka kuntoluokkaEnum;
    private SimpleFloatProperty neliömäärä;
    private String muutEhdot;
    private String[] kohteenKuvaus;
    private String sähköposti;

    public Asunto() {
        nimi = new SimpleStringProperty();
        vuokra = new SimpleIntegerProperty();
        rakennusvuosi = new SimpleIntegerProperty();
        neliömäärä = new SimpleFloatProperty();
        kuntoluokka = new SimpleStringProperty();
        setKuntoluokka(Kuntoluokka.EI_KUNTOLUOKKAA);
    }

    public String getKuvaTiedosto() {
        return kuvaTiedosto;
    }

    public void setKuvaTiedosto(String kuvaTiedosto) {
        this.kuvaTiedosto = kuvaTiedosto;
    }

    public String getNimi() {
        return nimi.get();
    }

    public void setNimi(String nimi) {
        this.nimi.setValue(nimi);
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public int getRakennusvuosi() {
        return rakennusvuosi.get();
    }

    public void setRakennusvuosi(int rakennusvuosi) {
        this.rakennusvuosi.setValue(rakennusvuosi);
    }

    public String getKuntoluokka() { return kuntoluokka.get(); }

    public Kuntoluokka getKuntoluokkaEnum() {return kuntoluokkaEnum;}

    public void setKuntoluokka(Kuntoluokka k) {
        kuntoluokka.setValue(k.toString().toLowerCase());
        kuntoluokkaEnum = k;
    }

    public float getNeliömäärä() {
        return neliömäärä.get();
    }

    public void setNeliömäärä(float neliömäärä) {
        this.neliömäärä.set(neliömäärä);
    }

    public int getVuokra() {
        return vuokra.getValue();
    }

    public void setVuokra(int vuokra) {
        this.vuokra.setValue(vuokra);
    }

    public String getMuutEhdot() {
        return muutEhdot;
    }

    public void setMuutEhdot(String muutEhdot) {
        this.muutEhdot = muutEhdot;
    }

    public String[] getKohteenKuvaus() {
        return kohteenKuvaus;
    }

    public void setKohteenKuvaus(String[] kohteenKuvaus) {
        this.kohteenKuvaus = kohteenKuvaus;
    }

    public String getSähköposti() {
        return sähköposti;
    }

    public void setSähköposti(String sähköposti) {
        this.sähköposti = sähköposti;
    }

    public String toString() {
        return
                nimi.getValue() + "\n" +
                "Osoite: " + osoite + "\n" +
                "Rakennettu: " + rakennusvuosi.getValue() + "\n" +
                "Koko: " + neliömäärä.getValue() + "m^2\n" +
                "Kuntoluokka: " + kuntoluokkaEnum.toString() + "\n" +
                "Vuokra: " + vuokra.getValue() + " eur\n" +
                "Ehdot: " + muutEhdot + "\n" +
                "Sähköposti: " + sähköposti + "\n" +
                "Kuva: " + kuvaTiedosto + "\n";
    }
}