import java.util.ArrayList;
import java.util.List;

public class Ugello {

    public Fluido ugello(Fluido fluidoEntrata, Fluido fluidoEsterno) {
        Fluido fluidoUscita = new Fluido();
        fluidoUscita.setPressione(fluidoEsterno.getPressione());
        fluidoUscita.setTemperatura(fluidoEntrata.getTemperatura() * Math.pow(fluidoUscita.getPressione() / fluidoEntrata.getPressione(), (Costanti.GAMMA_ARIA - 1) / Costanti.GAMMA_ARIA));
        fluidoUscita.setDensita(fluidoUscita.getPressione() / (Costanti.R * fluidoUscita.getTemperatura()));
        fluidoUscita.setVelocita(Math.sqrt(2 * (7 / 2) * Costanti.R * (fluidoEntrata.getTemperatura() - fluidoUscita.getTemperatura())));

        return fluidoUscita;
    }

    public List<Aria> ugenlloStepByStep(Fluido fluidoIngresso, Fluido fluidoUscita) {

        List<Aria> arias = new ArrayList<>();
        double passoPressione = (fluidoUscita.getPressione()- fluidoIngresso.getPressione()) / 100;
        //double passoPressione = 10;
        double potenza = (Costanti.GAMMA_ARIA - 1) / Costanti.GAMMA_ARIA;
        Aria aria = new Aria();
        aria.setTemperatura(fluidoIngresso.getTemperatura() * Math.pow((1 + (passoPressione / fluidoIngresso.getPressione())), potenza));
        aria.setPressione(fluidoIngresso.getPressione() + passoPressione);
        aria.setDensita(aria.getPressione() / (Costanti.R * aria.getTemperatura()));
        arias.add(aria);
        //!(Math.abs(aria.getPressione()) - fluidoUscita.getPressione() < 5)
        for (int i = 0; i<=100; i++) {
            Aria aria1 = new Aria();
            Aria ariaPrecedente = arias.get(arias.size() - 1);
            aria1.setTemperatura(ariaPrecedente.getTemperatura() * Math.pow(1 + (passoPressione / ariaPrecedente.getPressione()), potenza));
            aria1.setPressione(ariaPrecedente.getPressione() + passoPressione);
            aria1.setDensita(aria1.getPressione() / (Costanti.R * aria1.getTemperatura()));
            arias.add(aria1);
        }

        return arias;
    }

}
