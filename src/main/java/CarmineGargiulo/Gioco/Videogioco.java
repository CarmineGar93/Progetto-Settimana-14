package CarmineGargiulo.Gioco;

import java.util.Random;

public class Videogioco extends Gioco {
    private Random random = new Random();
    private Piattaforma piattaforma;
    private int durata = random.nextInt(40,120);
    private Genere genere;

    public Videogioco(String titolo, double prezzo, Piattaforma piattaforma, Genere genere) {
        super(titolo, prezzo);
        this.piattaforma = piattaforma;
        this.genere = genere;
    }

    public Piattaforma getPiattaforma() {
        return piattaforma;
    }

    public void setPiattaforma(Piattaforma piattaforma) {
        this.piattaforma = piattaforma;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", piattaforma = " + piattaforma +
                ", durata = " + durata +
                " ore, genere = " + genere;
    }

}
