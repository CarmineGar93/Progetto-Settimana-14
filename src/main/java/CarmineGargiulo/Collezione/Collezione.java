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

    public void ricercaGiocatori(){
        System.out.println("Inserisci un numero di giocatori da 1 a 8, ti mostreremo la lista di giochi da tavolo nella nostra collezione con numero di giocatori corrispondente");
        int ricerca;
        while (true){
            String input = scanner.nextLine();
            try {
                ricerca = Integer.parseInt(input);
                if (ricerca <= 0 || ricerca > 8) System.out.println("Devi inserire un numero nel range");
                else break;
            } catch (NumberFormatException e){
                System.out.println("Devi inserire un numero");
            }
        }
        int finalRicerca = ricerca;
        List<Gioco> listaGiocatori = listaGiochi.stream().filter(gioco -> gioco instanceof GiocoDaTavolo && ((GiocoDaTavolo) gioco).getNumeroGiocatori() == finalRicerca).toList();
        if (listaGiocatori.isEmpty()) System.out.println("Nessun gioco nella collezione con il numero di persone inserito");
        else {
            System.out.println("Ecco la lista filtrata");
            listaGiocatori.forEach(System.out::println);
        }
    }

    private int insertId(){
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
        return ricerca;
    }

    public void rimuoviId(){
        int finalRicerca = insertId();
        boolean rimosso = false;
        Iterator<Gioco> iterator = listaGiochi.iterator();
        while (iterator.hasNext()){
            Gioco corrente = iterator.next();
            if(corrente.getId() == finalRicerca){
                iterator.remove();
                rimosso = true;
                break;
            }
        }
        if (rimosso) {
            System.out.println("Rimozione gioco in corso");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Gioco della collezione cancellato con successo");
        }
        else System.out.println("Nessun elemento corrisponde all'id cercato");
    }

    public void aggiornaGioco(){
        int finalRicerca = insertId();
        Optional <Gioco> giocoCercato = listaGiochi.stream().filter(gioco -> gioco.getId() == finalRicerca).findFirst();
        if(giocoCercato.isPresent()){
            System.out.println("Che cosa vuoi modificare?");
            System.out.println("1 per titolo - 2 per prezzo");
            int ricerca;
            while (true){
                String input = scanner.nextLine();
                try {
                    ricerca = Integer.parseInt(input);
                    if (ricerca <= 0 || ricerca > 2) System.out.println("Devi inserire 1 o 2");
                    else break;
                } catch (NumberFormatException e){
                    System.out.println("Devi inserire un numero");
                }
            }
            if (ricerca == 1) {
                System.out.println("Inserisci il nuovo titolo");
                String titolo = scanner.nextLine();
                giocoCercato.get().setTitolo(titolo);
            } else {
                System.out.println("Inserisci il nuovo prezzo");
                double price;
                while (true) {
                    String strPrice = scanner.nextLine();
                    try {
                        price = Double.parseDouble(strPrice);
                        if(price <= 0) System.out.println("Devi inserire un prezzo maggiore di zero");
                        else break;
                    } catch (NumberFormatException e) {
                        System.out.println("Devi inserire un numero");
                    }
                }
                giocoCercato.get().setPrezzo(price);
            }
            System.out.println("Ecco l'elemento dopo la modifica effetuata");
            System.out.println(giocoCercato.get());
        } else {
            System.out.println("Nessun elemento corrisponde all'id cercato");
        }
    }

    public void ricercaId(){
        int finalRicerca = insertId();
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
           String input = scanner.nextLine();
           try {
               opzione = Integer.parseInt(input);
               if (opzione <= 0 || opzione > 2) System.out.println("Devi inserire 1 o 2");
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
                if(price <= 0) System.out.println("Devi inserire un prezzo maggiore di zero");
                else break;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Devi inserire un numero");
            }
        }
        aggiungi(titolo, price, opzione);
        System.out.println((opzione == 1 ? "Videogioco " : "Gioco da tavolo ") + "inserito con successo");
    }

    public void statistiche(){
        System.out.println("Ecco le statistiche della collezione");
        OptionalDouble mediaPrezzi = listaGiochi.stream().mapToDouble(Gioco::getPrezzo).average();
        long nrVideogiochi = listaGiochi.stream().filter(gioco -> gioco instanceof Videogioco).count();
        long nrGiochidaTavolo = listaGiochi.size() - nrVideogiochi;
        Optional<Gioco> mostExpensiveGame = listaGiochi.stream().max(Comparator.comparingDouble(Gioco::getPrezzo));
        System.out.println("Il numero totale dei videogiochi è: " + nrVideogiochi);
        System.out.println("Il numero totale dei giochi da tavolo è: " + nrGiochidaTavolo);
        System.out.println("Il gioco con il prezzo più alto è: " + mostExpensiveGame.get());
        System.out.println("La media prezzi dei giochi è: " + mediaPrezzi.getAsDouble());
    }

    public void mostra(){
        System.out.println("Giochi presenti nella lista: " + listaGiochi.size());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Ecco la tua lista");
        listaGiochi.forEach(System.out::println);
    }

    public void avviaMenu(){
        System.out.println("Benvenuto nella tua collezioni di giochi");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        mostra();
        while (true){
            System.out.println("Che cosa vuoi fare?");
            System.out.println("1 per aggiungere elemento");
            System.out.println("2 per ricercare gioco tramite id");
            System.out.println("3 per ricerca in base ad una fascia di prezzo");
            System.out.println("4 per ricerca giochi da tavolo in base al numero dei giocatori");
            System.out.println("5 per rimozione gioco tramite id");
            System.out.println("6 per aggiornamento gioco tramite id");
            System.out.println("7 per le statistiche della tua collezione");
            System.out.println("8 per mostrare la tua collezione");
            System.out.println("0 per uscire");
            int scelta;
            while (true){
                String input = scanner.nextLine();
                try {
                    scelta = Integer.parseInt(input);
                    if (scelta < 0 || scelta > 8) System.out.println("Devi inserire un numero tra zero e 8");
                    else break;
                } catch (NumberFormatException e){
                    System.out.println("Devi inserire un numero");
                }
            }
            switch (scelta){
                case 0 -> {
                    System.out.println("A presto");
                    return;
                }
                case 1 -> aggiungiElemento();
                case 2 -> ricercaId();
                case 3 -> ricercaPrezzo();
                case 4 -> ricercaGiocatori();
                case 5 -> rimuoviId();
                case 6 -> aggiornaGioco();
                case 7 -> statistiche();
                case 8 -> mostra();
            }
        }
    }
}
