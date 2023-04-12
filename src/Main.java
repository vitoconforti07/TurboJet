import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

       try {
           FileWriter myWriter = new FileWriter("TurboJet.txt");

           TurboJet turboJet = new TurboJet();
           Aria ariaQuota = new Aria();
           Scanner myObj = new Scanner(System.in);
           System.out.println("inserisci la quota: ");

           int quota = myObj.nextInt();
           System.out.println("la quota è : " + quota);

           myWriter.write("la quota è : " + quota +"\n");

           System.out.println("inserisci la velocità:");
           int velocita = myObj.nextInt();
           System.out.println("la velocità è : " + velocita);

           myWriter.write("la velocità è : " + velocita +"\n");

           System.out.println("inserisci la rapporto Compressione:");
           double rapportoCompressione = myObj.nextDouble();
           System.out.println("il rapporto Compressione è : " + rapportoCompressione);

           myWriter.write("il rapporto Compressione è : " + rapportoCompressione +"\n");

           System.out.println("inserisci la temperatura Turbina:");
           int temperaturaTurbina = myObj.nextInt();
           System.out.println("la temperatura Turbina è : " + temperaturaTurbina);

           myWriter.write("la temperatura Turbina è : " + temperaturaTurbina +"\n");

           System.out.println("inserisci l'area di sezione:");
           double area = myObj.nextDouble();
           System.out.println("l'area di sezione è : " + area);

           myWriter.write("l'area di sezione è : " + area +"\n");

           System.out.println("inserisci il rapportoFluidificazione:");
           double rapportoFluidificazione = myObj.nextDouble();
           System.out.println("il rapportoFluidificazione è : " + rapportoFluidificazione);

           myWriter.write("il rapportoFluidificazione è : " + rapportoFluidificazione +"\n");

           Aria aria = ariaQuota.ariaTipo(quota);
           List<String> strings = turboJet.turboJet(aria, velocita, rapportoCompressione, temperaturaTurbina, area, rapportoFluidificazione);
           strings.forEach(s -> {
               try {
                   myWriter.write(s);
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }
           });


           myWriter.close();
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
