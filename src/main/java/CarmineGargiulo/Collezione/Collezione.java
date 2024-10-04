package CarmineGargiulo.Collezione;

import CarmineGargiulo.Gioco.*;
import com.github.javafaker.Faker;

import java.util.*;

public class Collezione {
    Scanner scanner = new Scanner(System.in);
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
            aggiungi(faker.app().name(), random.nextDouble(10, 250), opzione);
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

    public void ricercaPrezzo(){
        System.out.println("Inserisci un prezzo massimo, ti mostreremo tutti i giochi della collezione con prezzi inferiori al prezzo fornito");
        double price;
        while (true){
            String input = scanner.nextLine();
            try {
                price = Double.parseDouble(input);
                if (price <= 0) System.out.println("Il prezzo deve essere maggiore di zero");
                else break;
            } catch (NumberFormatException e){
                System.out.println("Devi inserire un numero");
            }
        }
        double finalprice = price;
        List<Gioco> listaGiochiFiltrata = listaGiochi.stream().filter(gioco -> gioco.getPrezzo() < finalprice).toList();

        if (listaGiochiFiltrata.isEmpty()) System.out.println("Prezzo troppo basso");
        else {
            System.out.println("Ecco la lista filtrata");
            listaGiochiFiltrata.forEach(System.out::println);
        }
    }

    public void ricercaId(){
        System.out.println("Inserisci un id da cercare (range 1-1000)");
        int ricerca;
        while (true){
            String input = scanner.nextLine();
            try {
                ricerca = Integer.parseInt(input);
                if (ricerca <= 0 || ricerca > 1000) System.out.println("Devi inserire un numero nel range");
                else break;
            } catch (NumberFormatException e){
                System.out.println("Devi inserire un numero");
            }
        }
        int finalRicerca = ricerca;
        Optional <Gioco> giocoCercato = listaGiochi.stream().filter(gioco -> gioco.getId() == finalRicerca).findFirst();
        if(giocoCercato.isPresent()) {
            System.out.println("Ecco il gioco cercato");
            System.out.println(giocoCercato.get());
        } else System.out.println("Nessun gioco corrisponde all' Id fornito");
    }

    public void aggiungiElemento() {
        System.out.println("1 per inserire un Videogioco - 2 per inserire un gioco da tavolo");
        int opzione;
        while (true){
            String scelta = scanner.nextLine();
            try {
                opzione = Integer.parseInt(scelta);

                if(opzione <= 0 || opzione > 2) System.out.println("Devi inserire 1 o 2");
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
