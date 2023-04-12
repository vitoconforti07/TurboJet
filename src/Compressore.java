import java.util.ArrayList;
import java.util.List;

public class Compressore {

    //dati in input T2
    //dati di sistema rapporto di compressione e gamma

    public Fluido compressore(final double rapportoCompressione, Fluido fluidoIngresso, double temperaturaEsterna) {

        Fluido fluidoUscita = new Fluido();
        fluidoUscita.setTemperatura(temperatureCompressore(rapportoCompressione, Costanti.GAMMA_ARIA, fluidoIngresso.getTemperatura()));
        fluidoUscita.setPressione(pressioneCompressore(rapportoCompressione, fluidoIngresso.getPressione()));
        fluidoUscita.setDensita(densitaCompressore(fluidoUscita.getTemperatura(), fluidoUscita.getPressione()));

        fluidoUscita.setVelocita(velocitaCompressore(temperaturaEsterna));
        return fluidoUscita;
    }

    private double temperatureCompressore(final double rapportoCompressione, final double gamma, double temperaturaIngresso) {

        return temperaturaIngresso * Math.pow(rapportoCompressione, (gamma - 1) / gamma);
    }

    private double pressioneCompressore(final double rapportoCompressione, double pressioneIngresso) {

        return pressioneIngresso * rapportoCompressione;
    }

    private double densitaCompressore(double temperatura, double pressioneIngresso) {

        return pressioneIngresso / (Costanti.R * temperatura);
    }

    private double velocitaCompressore(double temperaturaEsterna) {

        double a = Math.sqrt(Costanti.GAMMA_ARIA * Costanti.R * temperaturaEsterna);
        return Costanti.VELOCITA_CAMERA_COMBUSTIONE * a;

    }

    public List<Aria> compressioneStepByStep(Fluido fluidoIngresso, Fluido fluidoUscita) {

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

