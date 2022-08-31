public class Compressore {


    //dati in input T2
    //dati di sistema rapporto di compressione e gamma

    public Fluido compressore(final double rapportoCompressione,  Fluido fluidoIngresso, double temperaturaEsterna) {

        Fluido fluidoUscita = new Fluido();
        fluidoUscita.setTempeatura(temperatureCompressore(rapportoCompressione, Costanti.GAMMA_ARIA, fluidoIngresso.getTempeatura()));
        fluidoUscita.setPressione(pressioneCompressore(rapportoCompressione, fluidoIngresso.getPressione()));
        fluidoUscita.setDensita(densitaCompressore(fluidoUscita.getTempeatura(), fluidoUscita.getPressione()));

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

        return pressioneIngresso /(Costanti.R * temperatura);
    }

    private double velocitaCompressore(double temperaturaEsterna) {

        double a = Math.sqrt(Costanti.GAMMA_ARIA * Costanti.R * temperaturaEsterna);
        return Costanti.VELOCITA_03_MACH * a;

    }
}
