import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TurboJet {


    public List<String> turboJet(Aria ariaQuota, int velocità, double rapportoCompressione, int temperaturaTurbina, double area, final double rapportoFluidificazione) {
        List<String> fluidoList = new ArrayList<>();
        try {
            FileWriter output = new FileWriter("TurboJet1.csv");
            output.write("pressione,temperatura,densità" + "\n");
            Fluido fluidoEsterno = new Fluido(ariaQuota.getPressione(), ariaQuota.getTemperatura(), ariaQuota.getDensita(), velocità);
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("FLUIDO ESTERNO:");
            System.out.println(fluidoEsterno);

            fluidoList.add("-------------------------------------------------------------------------------------------------------------------------------------------------" + "\n");
            fluidoList.add("FLUIDO ESTERNO:" + "\n");
            fluidoList.add(fluidoEsterno + "\n");


            PresaDinamica presaDinamica = new PresaDinamica();
            Fluido fluidoPresaDinamica = presaDinamica.presaDinamica(fluidoEsterno, velocità);
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("PRESA DINAMICA:");
            System.out.println(fluidoPresaDinamica.toString());
            List<Aria> arias = presaDinamica.presaDinamicaStepByStep(fluidoEsterno, fluidoPresaDinamica);
            arias.forEach(aria -> {
                try {
                    output.write(aria.getPressione()+","+aria.getTemperatura()+","+ aria.getDensita()  + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            fluidoList.add("-------------------------------------------------------------------------------------------------------------------------------------------------" + "\n");
            fluidoList.add("PRESA DINAMICA:" + "\n");
            fluidoList.add(fluidoPresaDinamica + "\n");

            Compressore compressore = new Compressore();

            Fluido fluidoCompressore = compressore.compressore(rapportoCompressione, fluidoEsterno, ariaQuota.getTemperatura());
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("COMPRESSORE:");
            System.out.println(fluidoCompressore.toString());
            fluidoList.add("-------------------------------------------------------------------------------------------------------------------------------------------------" + "\n");
            fluidoList.add("COMPRESSORE:" + "\n");
            fluidoList.add(fluidoCompressore + "\n");

            List<Aria> arias1 = compressore.compressioneStepByStep(fluidoPresaDinamica, fluidoCompressore);
            arias1.forEach(aria -> {
                try {
                    output.write(aria.getPressione()+","+aria.getTemperatura()+","+ aria.getDensita() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            CameraDiCombustione cameraDiCombustione = new CameraDiCombustione();
            Fluido fluidoCameraCombustione = cameraDiCombustione.cameraDiCombustione(temperaturaTurbina, fluidoCompressore);
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("CAMERA DI COMBUSTIONE :");
            System.out.println(fluidoCameraCombustione.toString());

            fluidoList.add("-------------------------------------------------------------------------------------------------------------------------------------------------" + "\n");
            fluidoList.add("CAMERA DI COMBUSTIONE :" + "\n");
            fluidoList.add(fluidoCameraCombustione + "\n");

            List<Aria> arias4 = cameraDiCombustione.cameraDiCombustioneStepByStep(fluidoCompressore, fluidoCameraCombustione);
            arias4.forEach(aria -> {
                try {
                    output.write(aria.getPressione()+","+aria.getTemperatura()+","+ aria.getDensita() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            Turbina turbina = new Turbina();
            Fluido fluidoTurbina = turbina.turbina(rapportoFluidificazione, fluidoCameraCombustione, fluidoCompressore, fluidoEsterno);
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("TURBINA:");
            System.out.println(fluidoTurbina.toString());

            fluidoList.add("-------------------------------------------------------------------------------------------------------------------------------------------------" + "\n");
            fluidoList.add("TURBINA:" + "\n");
            fluidoList.add(fluidoTurbina + "\n");

            List<Aria> arias2 = turbina.turbinaStepByStep(fluidoCameraCombustione, fluidoTurbina);
            arias2.forEach(aria -> {
                try {
                    output.write(aria.getPressione()+","+aria.getTemperatura()+","+ aria.getDensita() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });



            Ugello ugello = new Ugello();
            Fluido fluidoUgello = ugello.ugello(fluidoTurbina, fluidoEsterno);
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("UGELLO:");
            System.out.println(fluidoUgello.toString());

            fluidoList.add("-------------------------------------------------------------------------------------------------------------------------------------------------" + "\n");
            fluidoList.add("UGELLO:" + "\n");
            fluidoList.add(fluidoUgello + "\n");

            List<Aria> arias3 = ugello.ugenlloStepByStep(fluidoTurbina, fluidoUgello);
            arias3.forEach(aria -> {
                try {
                    output.write(aria.getPressione()+","+aria.getTemperatura()+","+ aria.getDensita() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------" + "\n");
            System.out.println("La spinta del TurboJet è :");
            System.out.println(spinta(ariaQuota, fluidoUgello, velocità, area) + " N");

            fluidoList.add("-------------------------------------------------------------------------------------------------------------------------------------------------" + "\n");
            fluidoList.add("La spinta del TurboJet è :" + "\n");
            fluidoList.add(spinta(ariaQuota, fluidoUgello, velocità, area) + " N" + "\n");

            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("il rendimento del TurboJet è : ");
            System.out.println(rendimento(fluidoUgello, velocità));

            fluidoList.add("-------------------------------------------------------------------------------------------------------------------------------------------------" + "\n");
            fluidoList.add("il rendimento del TurboJet è : " + "\n");
            fluidoList.add(rendimento(fluidoUgello, velocità) + "\n");

            System.out.println("FINITO");
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fluidoList;
    }


    private double spinta(Aria ariaQuota, Fluido fluido, int velocita, double area) {

        //formula per spinta S = m *(Vf-Va)

        double portata = ariaQuota.getDensita() * area * velocita;
        return portata * (fluido.getVelocita() - velocita);

    }

    private double rendimento(Fluido fluido, int velocita) {

        double rapportoVelocita = fluido.getVelocita() / velocita;

        return 2 / (1 + rapportoVelocita);

    }

}
