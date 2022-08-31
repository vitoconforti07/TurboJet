import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        TurboJet turboJet = new TurboJet();
        Aria ariaQuota = new Aria();
        Scanner myObj = new Scanner(System.in);
        System.out.println("inserisci la quota");

        int quota = myObj.nextInt();
        System.out.println("la quota è : " + quota);

        System.out.println("inserisci la velocità:");
        int velocita = myObj.nextInt();
        System.out.println("la velocità è : " + velocita);

        System.out.println("inserisci la rapporto Compressione:");
        int rapportoCompressione = myObj.nextInt();
        System.out.println("il rapporto Compressione è : " + rapportoCompressione);

        System.out.println("inserisci la temperatura Turbina:");
        int temperaturaTurbina = myObj.nextInt();
        System.out.println("la temperatura Turbina è : " + rapportoCompressione);

        System.out.println("inserisci l'area di sezione:");
        int area = myObj.nextInt();
        System.out.println("l'area di sezione è : " + area);

        Aria aria = ariaQuota.ariaTipo(quota);
        turboJet.turboJet(aria, velocita, rapportoCompressione, temperaturaTurbina, area);
    }
}
