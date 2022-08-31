public class Fluido extends Aria {

       private  double velocita;

    public Fluido(double pressione, double tempeatura, double densita, double velocita) {
        super(pressione, tempeatura, densita);
        this.velocita = velocita;
    }

    public Fluido() {

    }


    public double getVelocita() {
        return velocita;
    }

    public void setVelocita(double velocita) {
        this.velocita = velocita;
    }

    @Override
    public String toString() {

        return "Fluido { " +
                "velocita = " + velocita + " m/s" +
                ", pressione = " + getPressione() +" Pa"  +
                ", tempeatura = " + getTempeatura() + " K" +
                ", densita = " + getDensita() + " Kg/m3" +
                '}';
    }
}
