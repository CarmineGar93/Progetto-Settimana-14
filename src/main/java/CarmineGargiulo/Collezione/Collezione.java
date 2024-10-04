package CarmineGargiulo.Collezione;

import CarmineGargiulo.Gioco.*;
import com.github.javafaker.Faker;

import java.util.*;

public class Collezione {
    private Faker faker = new Faker(Locale.ITALY);
    private Random random = new Random();
    private Collection<Gioco> listaGiochi = new TreeSet<>();

    public Collection<Gioco> getListaGiochi() {
        return listaGiochi;
    }

    public Collezione() {
        generaRandom();
    }

    private void generaRandom(){
        for (int i = 0; i < 50; i++) {
            int opzione = random.nextInt(1, 3);
            aggiungi(faker.app().name(), Double.parseDouble(faker.commerce().price()), opzione);
        }
    }

    private void aggiungi(String titolo, double price, int opzione){
        if (opzione == 1){
            List<Genere> generi = Arrays.stream(Genere.values()).toList();
            Genere genereRandom = generi.get(random.nextInt(0, generi.size() - 1));
            List<Piattaforma> piattaforme = Arrays.stream(Piattaforma.values()).toList();
            Piattaforma piattaformaRandom = piattaforme.get(random.nextInt(0, piattaforme.size() - 1));
            while (true){
                Videogioco videogioco = new Videogioco(titolo, price, piattaformaRandom, genereRandom);
                if(listaGiochi.add(videogioco)) break;
            }
        } else {
            while (true){
                GiocoDaTavolo giocoDaTavolo = new GiocoDaTavolo(titolo, price);
                if(listaGiochi.add(giocoDaTavolo)) break;
            }
        }
    }

    public void aggiungiElemento() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 per inserire un Videogioco - 2 per inserire un gioco da tavolo");
        int opzione;
        while (true){
            String scelta = scanner.nextLine();
            try {
                opzione = Integer.parseInt(scelta);

                if(opzione < 0 || opzione > 2) System.out.println("Devi inserire 1 o 2");
                else break;
            } catch (NumberFormatException e){
                System.out.println("Devi inserire un numero");
            }
        }
        System.out.println("Inserisci il titolo del gioco");
        String titolo = scanner.nextLine();
        System.out.println("Adesso inserisci il prezzo");
        double price;
        while (true) {
            String strPrice = scanner.nextLine();
            try {
                price = Double.parseDouble(strPrice);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Devi inserire un numero");
            }
        }
        aggiungi(titolo, price, opzione);
        System.out.println((opzione == 1 ? "Videogioco " : "Gioco da tavolo ") + "inserito con successo");
    }
}
