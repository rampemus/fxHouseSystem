package houseSystem;

public class Asunto {
    private String kuvaTiedosto;
    private String nimi;
    private String osoite;
    private int rakennusvuosi;
    private float neliömäärä;
    private int vuokra;
    private String muutEhdot;
    private String[] kohteenKuvaus;
    private String sähköposti;

    public String getKuvaTiedosto() {
        return kuvaTiedosto;
    }

    public void setKuvaTiedosto(String kuvaTiedosto) {
        this.kuvaTiedosto = kuvaTiedosto;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public int getRakennusvuosi() {
        return rakennusvuosi;
    }

    public void setRakennusvuosi(int rakennusvuosi) {
        this.rakennusvuosi = rakennusvuosi;
    }

    public float getNeliömäärä() {
        return neliömäärä;
    }

    public void setNeliömäärä(float neliömäärä) {
        this.neliömäärä = neliömäärä;
    }

    public int getVuokra() {
        return vuokra;
    }

    public void setVuokra(int vuokra) {
        this.vuokra = vuokra;
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
                nimi + "\n" +
                "Osoite: " + osoite + "\n" +
                "Rakennettu: " + rakennusvuosi + "\n" +
                "Koko: " + neliömäärä + "m^2\n" +
                "Vuokra: " + vuokra + " eur\n" +
                "Ehdot: " + muutEhdot + "\n" +
                "Sähköposti: " + sähköposti + "\n" +
                "Kuva: " + kuvaTiedosto + "\n";
    }
}