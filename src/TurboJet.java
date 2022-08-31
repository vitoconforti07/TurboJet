public class TurboJet {


    public void turboJet(Aria ariaQuota, int velocità, double rapportoCompressione, int temperaturaTurbina, double area) {


        Fluido fluido = new Fluido(ariaQuota.getPressione(), ariaQuota.getTempeatura(), ariaQuota.getDensita(), velocità);

        PresaDinamica presaDinamica = new PresaDinamica();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("PRESA DINAMICA:");
        System.out.println(fluido);

        Compressore compressore = new Compressore();
        fluido = compressore.compressore(rapportoCompressione, fluido, ariaQuota.getTempeatura());
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("COMPRESSORE:");
        System.out.println(fluido.toString());

        CameraDiCombustione cameraDiCombustione = new CameraDiCombustione();
       fluido = cameraDiCombustione.cameraDiCombustione(temperaturaTurbina, fluido);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("CAMERA DI COMBUSTIONE :");
        System.out.println(fluido);

        Turbina turbina = new Turbina();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("TURBINA:");
        System.out.println(fluido);

        Ugello ugello = new Ugello();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("UGELLO:");
        System.out.println(fluido);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println("La spinta del TurboJet è :");
        System.out.println(spinta(ariaQuota, fluido, velocità, area) + " N");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("il rendimento del TurboJet è : ");
        System.out.println(rendimento(fluido, velocità));

        System.out.println("FINITO");

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
