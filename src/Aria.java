import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Aria {


    private double pressione;

    private double tempeatura;
    private double densita;


    public Aria(double pressione, double tempeatura, double densita) {
        this.pressione = pressione;
        this.tempeatura = tempeatura;
        this.densita = densita;
    }

    public Aria() {

    }

    private static HashMap<Integer, Aria> readCSV() {
        String line = "";
        String splitBy = ",";


        HashMap<Integer, Aria> ariaTipoList = new HashMap<>();
        try {

            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\V.Conforti\\Desktop\\progetti\\TurboJet\\src\\Aria_tipo.csv"));
            br.readLine();
            while ((line = br.readLine()) != null)
            {
                String[] riga = line.split(splitBy);
                ariaTipoList.put(Integer.parseInt(riga[0]), new Aria(Double.parseDouble(riga[3]), Double.parseDouble(riga[1]), Double.parseDouble(riga[2])));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ariaTipoList;
    }

    public double getPressione() {
        return pressione;
    }

    public void setPressione(double pressione) {
        this.pressione = pressione;
    }

    public double getTempeatura() {
        return tempeatura;
    }

    public void setTempeatura(double tempeatura) {
        this.tempeatura = tempeatura;
    }

    public double getDensita() {
        return densita;
    }

    public void setDensita(double densita) {
        this.densita = densita;
    }


    public Aria ariaTipo(int quota) {

        HashMap<Integer, Aria> integerAriaHashMap = readCSV();

        Aria aria;

        if (!integerAriaHashMap.containsKey(quota)) {

            aria = inpolazioneAriaTipo(integerAriaHashMap, quota);


        } else {
            aria = integerAriaHashMap.get(quota);
        }

        return aria;

    }

    private Aria inpolazioneAriaTipo(HashMap<Integer, Aria> integerAriaHashMap, Integer quota) {
        int quota1 = (quota / 500) * 500;
        int quota2 = quota1 + 500;

        double T = intepolazioneTemperatura(integerAriaHashMap, quota, quota1, quota2);
        double p = intepolazionePressione(integerAriaHashMap, quota, quota1, quota2);
        double d = intepolazioneDesinta(integerAriaHashMap, quota, quota1, quota2);
        return new Aria(p, T, d);
    }

    private double intepolazioneTemperatura(HashMap<Integer, Aria> integerAriaHashMap, Integer quota, int quota1, int quota2) {

        //temperatura
        double tempeatura1 = integerAriaHashMap.get(quota1).getTempeatura();
        double tempeatura2 = integerAriaHashMap.get(quota2).getTempeatura();

        double k = ((tempeatura2 - tempeatura1) / (quota2 - quota1));
        return k * quota - k * quota1 + tempeatura1;
    }

    private double intepolazionePressione(HashMap<Integer, Aria> integerAriaHashMap, Integer quota, int quota1, int quota2) {

        //pressione
        double pressione1 = integerAriaHashMap.get(quota1).getPressione();
        double pressione2 = integerAriaHashMap.get(quota2).getPressione();

        double k = ((pressione2 - pressione1) / (quota2 - quota1));
        return k * quota - k * quota1 + pressione1;
    }

    private double intepolazioneDesinta(HashMap<Integer, Aria> integerAriaHashMap, Integer quota, int quota1, int quota2) {

        //densità
        double densita1 = integerAriaHashMap.get(quota1).getDensita();
        double densita2 = integerAriaHashMap.get(quota2).getDensita();

        double k = ((densita2 - densita1) / (quota2 - quota1));
        return k * quota - k * quota1 + densita1;
    }


}
