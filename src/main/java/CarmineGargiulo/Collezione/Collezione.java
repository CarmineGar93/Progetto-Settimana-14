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
            int opzione = random.nextInt(0, 2);
            if (opzione == 0){
                List<Genere> generi = Arrays.stream(Genere.values()).toList();
                Genere genereRandom = generi.get(random.nextInt(0, generi.size() - 1));
                List<Piattaforma> piattaforme = Arrays.stream(Piattaforma.values()).toList();
                Piattaforma piattaformaRandom = piattaforme.get(random.nextInt(0, piattaforme.size() - 1));
                while (true){
                    Videogioco videogioco = new Videogioco(faker.app().name(), Double.parseDouble(faker.commerce().price()), piattaformaRandom, genereRandom);
                    if(listaGiochi.add(videogioco)) break;
                }
            } else {
                while (true){
                    GiocoDaTavolo giocoDaTavolo = new GiocoDaTavolo(faker.app().name(), Double.parseDouble(faker.commerce().price()));
                    if(listaGiochi.add(giocoDaTavolo)) break;
                }
            }
        }
    }
}
