package CarmineGargiulo;

import CarmineGargiulo.Collezione.Collezione;

public class Application {
    public static void main(String[] args) {
        Collezione collezione = new Collezione();
        collezione.getListaGiochi().forEach(System.out::println);
       /* collezione.aggiungiElemento();
        collezione.getListaGiochi().forEach(System.out::println);
        collezione.ricercaId();
        collezione.ricercaPrezzo();
        collezione.ricercaGiocatori();
        collezione.rimuoviId();*/
        collezione.aggiornamentoGioco();
    }
}
