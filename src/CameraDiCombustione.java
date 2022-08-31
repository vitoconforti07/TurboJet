public class CameraDiCombustione {

    public Fluido cameraDiCombustione(int temperaturaTurbina, Fluido fluidoCompressore) {

        //   la temperatura in uscita è di progetto, la pressione è costante
        Fluido fluidoUscita = new Fluido();
        fluidoUscita.setVelocita(fluidoCompressore.getVelocita());
        fluidoUscita.setPressione(fluidoCompressore.getPressione());
        fluidoUscita.setTempeatura(temperaturaTurbina);
        fluidoUscita.setDensita(densitaCompressore(fluidoUscita.getTempeatura(), fluidoCompressore.getPressione()));

        return fluidoUscita;
    }

    private double densitaCompressore(double temperatura, double pressioneIngresso) {

        return pressioneIngresso /(Costanti.R * temperatura);
    }

}
