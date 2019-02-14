package houseSystem;

import java.util.*;

public class AsuntoGeneraattori {
    private static final ArrayList<Asunto> asuntoLista = new ArrayList<>();
    private static int tehtäviäAsuntoja = 0;
    private static int tehtyjäAsuntoja = 0;
    private static final Object lock = new Object();

    public static int tehtäviäAsuntoja() {
        synchronized (lock) {
            return tehtäviäAsuntoja;
        }
    }

    public static int tehtyjäAsuntoja() {
        synchronized (lock) {
            return tehtyjäAsuntoja;
        }
    }

    public static List<Asunto> annaAsunnot() {
        synchronized (lock) {
            return asuntoLista;
        }
    }

    public static Asunto luo() {
        Asunto a = new Asunto();
        a.setKuvaTiedosto("house" + (1+new Random().nextInt(6)) + ".svg");

        var etuetu = List.of("Villa ", "", "", "", "", "");
        var etu = List.of("Metsä", "Kallio", "Nummi", "Lehto", "Maa", "Joki");
        var taka = List.of("Laakso", "Hovi", "Rinne", "Ranta", "Koski", "Pirtti", "Linna", "Suo", "Niitty");
        var kaikki = new ArrayList<String>();
        kaikki.addAll(etu);
        kaikki.addAll(taka);
        var tie = List.of("tie", "kuja", "katu", "polku");
        var ehdot = List.of("Kissat", "Koirat", "Lapset", "Mölyäminen", "Suihku ja imurointi ennen klo 7", "Kaupustelu", "Kerjääminen");
        var nimi = List.of("Matti", "Mervi", "Olli", "Johanna", "Ilpo", "Jenni");
        var rappu = List.of("A", "B", "Ö", "F", "N", "R");


        var n1 = etuetu.get(new Random().nextInt(etuetu.size()));
        var n2 = etu.get(new Random().nextInt(etu.size()));
        var n3 = taka.get(new Random().nextInt(taka.size())).toLowerCase();
        var nr = rappu.get(new Random().nextInt(rappu.size()));
        a.setNimi(n1 + n2 + n3+" "+nr+new Random().nextInt(100));

        var n4 = kaikki.get(new Random().nextInt(kaikki.size()));
        var n5 = tie.get(new Random().nextInt(tie.size()));
        a.setOsoite(n4+n5+" "+(1+new Random().nextInt(200)));

        a.setRakennusvuosi(1850+new Random().nextInt(170));
        a.setNeliömäärä(8+3*new Random().nextInt(30));
        a.setVuokra((int)(a.getNeliömäärä()*(7f+(a.getRakennusvuosi()-1850)/20f+new Random().nextDouble()*9)));

        var ehto = ehdot.get(new Random().nextInt(ehdot.size()));
        if (new Random().nextBoolean())
            a.setMuutEhdot(ehto + " kielletty");
        else
            a.setMuutEhdot("-");

        var n6 = nimi.get(new Random().nextInt(nimi.size())).toLowerCase();
        var n7 = kaikki.get(new Random().nextInt(kaikki.size())).toLowerCase();
        a.setSähköposti(n6 + new Random().nextInt(10000) + "@" + n7 + "mail.com");
        a.setKohteenKuvaus(new String[] { "Hyvä asunto keskeisellä paikalla kaikkien palveluiden äärellä."});
        return a;
    }

    public static void luoAsuntoja(int kpl) {
        // keskeytetään jos vanha työ kesken
        if (tehtäviäAsuntoja() > tehtyjäAsuntoja()) return;

        synchronized (lock) {
            tehtäviäAsuntoja = kpl;
            tehtyjäAsuntoja = 0;
        }

        new Thread(() -> {
            while (tehtyjäAsuntoja() < tehtäviäAsuntoja()) {
                synchronized (lock) {
                    Asunto a = luo();
                    asuntoLista.add(a);
                    System.out.println("Luotu "+a);
                    tehtyjäAsuntoja++;
                }
                try {
                    Thread.sleep(2000);
                }
                catch(Exception e) { }

            }
        }).start();
    }
}
