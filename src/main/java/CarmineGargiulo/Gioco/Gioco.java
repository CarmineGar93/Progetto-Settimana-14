package CarmineGargiulo.Gioco;

import java.util.Random;

public abstract class Gioco implements Comparable<Gioco> {
    Random random = new Random();
    protected int id = random.nextInt(1, 1001);
    protected String titolo;
    protected int annoPubblicazione = random.nextInt(1990, 2024);
    protected double prezzo;

    public Gioco(String titolo, double prezzo){
        this.titolo = titolo;
        this.prezzo = prezzo;
    }

    public int getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return "Gioco: " +
                "titolo = " + titolo + ", id = " + id +
                ", annoPubblicazione = " + annoPubblicazione +
                ", prezzo = " + prezzo;
    }

    @Override
    public int compareTo(Gioco o) {
        return Integer.compare(id, o.id);
    }
}
