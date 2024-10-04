package CarmineGargiulo.Gioco;

import java.util.Random;

public class GiocoDaTavolo extends Gioco{
    private Random random = new Random();
    private int numeroGiocatori = random.nextInt(0, 8);
    private int durataPartita = random.nextInt(20, 120);

    public GiocoDaTavolo(String titolo, double prezzo) {
        super(titolo, prezzo);
    }

    public int getNumeroGiocatori() {
        return numeroGiocatori;
    }

    public void setNumeroGiocatori(int numeroGiocatori) {
        this.numeroGiocatori = numeroGiocatori;
    }

    public int getDurataPartita() {
        return durataPartita;
    }

    public void setDurataPartita(int durataPartita) {
        this.durataPartita = durataPartita;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", numeroGiocatori = " + numeroGiocatori +
                ", durataPartita = " + durataPartita + " min";
    }
}
