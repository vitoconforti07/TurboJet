import java.util.ArrayList;
import java.util.List;

public class CameraDiCombustione {

    public Fluido cameraDiCombustione(int temperaturaTurbina, Fluido fluidoIngresso) {

        //   la temperatura in uscita è di progetto, la pressione è costante
        Fluido fluidoUscita = new Fluido();
        fluidoUscita.setVelocita(fluidoIngresso.getVelocita());
        fluidoUscita.setPressione(fluidoIngresso.getPressione());
        fluidoUscita.setTemperatura(temperaturaTurbina);
        fluidoUscita.setDensita(densitaCompressore(fluidoUscita.getTemperatura(), fluidoIngresso.getPressione()));

        return fluidoUscita;
    }

    private double densitaCompressore(double temperatura, double pressioneIngresso) {

        return pressioneIngresso /(Costanti.R * temperatura);
    }


    public List<Aria> cameraDiCombustioneStepByStep(Fluido fluidoIngresso, Fluido fluidoUscita) {

        List<Aria> arias = new ArrayList<>();
        double passoTemperatura = (fluidoUscita.getTemperatura()- fluidoIngresso.getTemperatura()) / 100;
        //double passoPressione = 10;
        double potenza = (Costanti.GAMMA_ARIA - 1) / Costanti.GAMMA_ARIA;
        Aria aria = new Aria();
        aria.setTemperatura(fluidoIngresso.getTemperatura() + passoTemperatura);
        aria.setPressione(fluidoIngresso.getPressione());
        aria.setDensita(aria.getPressione()/(Costanti.R*aria.getTemperatura()));
        arias.add(aria);
        //!(Math.abs(aria.getPressione()) - fluidoUscita.getPressione() < 5)
        for (int i = 0; i<=100; i++) {
            Aria aria1 = new Aria();
            Aria ariaPrecedente = arias.get(arias.size() - 1);
            aria1.setTemperatura(ariaPrecedente.getTemperatura() + passoTemperatura);
            aria1.setPressione(ariaPrecedente.getPressione());
            aria1.setDensita(ariaPrecedente.getPressione()/(Costanti.R* aria1.getTemperatura()));
            arias.add(aria1);
        }

        return arias;
    }

}
