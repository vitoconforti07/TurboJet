import java.util.ArrayList;
import java.util.List;

public class PresaDinamica {


    public Fluido presaDinamica(Fluido fluidoEsterno, double velocita) {
        double a = Math.sqrt(Costanti.GAMMA_ARIA * Costanti.R * fluidoEsterno.getTemperatura());
        double mach = velocita / a;
        Fluido fluidoUscita = new Fluido();
        fluidoUscita.setTemperatura(fluidoEsterno.getTemperatura() * (1 + ((Costanti.GAMMA_ARIA - 1) / 2) * mach));
        fluidoUscita.setPressione(fluidoEsterno.getPressione() * Math.pow(fluidoUscita.getTemperatura() / fluidoEsterno.getTemperatura(), Costanti.GAMMA_ARIA / (Costanti.GAMMA_ARIA - 1)));
        fluidoUscita.setDensita(fluidoUscita.getPressione() / (Costanti.R * fluidoUscita.getTemperatura()));
        fluidoUscita.setVelocita(fluidoEsterno.getVelocita());

        return fluidoUscita;
    }

    public List<Aria> presaDinamicaStepByStep(Fluido fluidoIngresso, Fluido fluidoUscita) {

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
