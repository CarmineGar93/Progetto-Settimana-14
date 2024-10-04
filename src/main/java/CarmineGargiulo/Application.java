package CarmineGargiulo;

import CarmineGargiulo.Collezione.Collezione;

public class Application {
    public static void main(String[] args) {
        Collezione collezione = new Collezione();
        collezione.getListaGiochi().forEach(System.out::println);
    }
}
