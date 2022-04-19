package Persistance;

import Models.Containers.ContBasic;
import Models.Containers.ContHeavy;
import Models.Containers.ContToxicLiquid;
import Models.Ship;
import Models.ShipCapacityInfo;

import java.util.Comparator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ShipPersistance {

    private Ship ship;

    private String HeavyContainerName = "Ciężki";
    private String toxicLiquidContainerName = " Toksyczny płynny";

    public ShipPersistance(Ship ship){

        this.ship = ship;
    }

    public String PrepareToSave(){

        StringBuffer sb = new StringBuffer();
        sb.append(String.format("Id Statku : %s \n", ship.shipId));
        sb.append(String.format("Nazwa : %s \n", ship.name));
        sb.append(String.format("Port macierzysty : %s \n", ship.homeport));
        sb.append(String.format("Do portu : %s \n", ship.destination));
        sb.append(String.format("Z portu : %s \n", ship.from));

        sb.append("Maksymalna Pojemność statku :\n\t");
        sb.append(String.format("Ogólna : %s \n\t", ship.shipCapacityMax.capacity));
        sb.append(String.format("Podłączeniem do prądu  : %s \n\t", ship.shipCapacityMax.electrified));
        sb.append(String.format("Niebezpieczna : %s \n\t", ship.shipCapacityMax.hazardous));
        sb.append(String.format("Maksymalna waga : %s \n\t", ship.shipCapacityMax.weight));
        sb.append(String.format("Ciężkie : %s \n\t", ship.shipCapacityMax.heavy));

        var weightComparator = Comparator.comparing(ContBasic::getWeight);
        var sortedContainers = ship.containerList.stream().sorted(weightComparator).collect(Collectors.toList());

        sb.append("Kontenery :\n\t\t");
        for(int i = 0; i < ship.containerList.size(); i++){
            ContBasic container = ship.containerList.get(i);
            sb.append((i+1)+". ");
            sb.append(String.format("Id Kontenera : %s \n\t\t", container.getId()));
            sb.append(String.format("Waga Kontenera : %s \n\t\t", container.getWeight()));
            if(container instanceof ContHeavy){
                sb.append(GenerateContHeavyString((ContHeavy) container));
            }
            if(container instanceof ContToxicLiquid){
                sb.append(GenerateContToxicLiquidString((ContToxicLiquid) container));
            }
            sb.append("\n\t\t---");
        }
        return sb.toString();
    }

    // patterny propertasów statków
    private Pattern shipIdPattern = Pattern.compile("Id Statku : (.*) \n");
    private Pattern namePattern = Pattern.compile("Nazwa : (.*) \n");
    private Pattern homePortPattern = Pattern.compile("Port macierzysty : (.*) \n");
    private Pattern destinationPattern = Pattern.compile("Do portu : (.*) \n");
    private Pattern fromPattern = Pattern.compile("Z portu : (.*) \n");

    // patterny kontenerów
    Pattern containerPattern = Pattern.compile("[1-9]+. Id Kontenera : [.\\s\\S]*?-{3}");

    // patterny porpertasów kontenerów

    public Ship CreateShipFromString(String stream){
        String huj = "Id Statku : 908fe608-2885-4f12-9b81-9d6a4c4fe539 \n" +
                "Nazwa : latajacyholender \n" +
                "Port macierzysty : Amsterdam \n" +
                "Do portu : Danzig \n" +
                "Z portu : Borholm \n" +
                "Maksymalna Pojemność statku :\n" +
                "\tOgólna : 44 \n" +
                "\tPodłączeniem do prądu  : 7 \n" +
                "\tNiebezpieczna : 13 \n" +
                "\tMaksymalna waga : 2137.0 \n" +
                "\tCiężkie : 10 \n" +
                "\tKontenery :\n" +
                "\t\t1. Id Kontenera : 603e782a-ce17-4986-ac94-6ffed3bd8961 \n" +
                "\t\tWaga Kontenera : 142.0 pizda\n" +
                "\t\t---\n" +
                "\t\t2. Id Kontenera : fc1119c3-9c78-4f8e-9e8a-bd54d3eb16a5 \n" +
                "\t\tWaga Kontenera : 222.0 \n" +
                "\t\tTyp kontenera : Ciężki \n" +
                "\t\tIso : 200 pizda\n" +
                "\t\t---\n" +
                "\t\t3. Id Kontenera : af2a1afa-d2d6-40f0-9375-05e7c923fb81 \n" +
                "\t\tWaga Kontenera : 121.0 \n" +
                "\t\tTyp kontenera : Ciężki \n" +
                "\t\tIso : 90900 \n" +
                "\t\tTyp kontenera :  Toksyczny płynny \n" +
                "\t\tNazwa Płynu : Skittlesowka \n" +
                "\t\t---\n" +
                "\t\t";

        var matcher = shipIdPattern.matcher(huj);
        var shipId = matcher.find();
        var sssss = matcher.group(1);
        System.out.println(shipId);

        var matchers = containerPattern.matcher(huj);
        while(matchers.find()){
            String container = matchers.group(0);
            System.out.println(container);
        }


        return null;
//        String name, String homeport, String from, String destination, , ArrayList<ContBasic> containerList, ShipCapacityInfo shipCapacityMax
//
//        Ship ship = new Ship(s
    }


    private String GenerateContToxicLiquidString(ContToxicLiquid container) {
        StringBuffer contDetails = new StringBuffer();
        contDetails.append(String.format("Typ kontenera : %s \n\t\t", toxicLiquidContainerName));
        contDetails.append(String.format("Nazwa Płynu : %s \n\t\t", container.getCompoundName()));
        return contDetails.toString();
    }

    private String GenerateContHeavyString(ContHeavy contHeavy) {
        StringBuffer contDetails = new StringBuffer();
        contDetails.append(String.format("Typ kontenera : %s \n\t\t", HeavyContainerName));
        contDetails.append(String.format("Iso : %s \n\t\t", contHeavy.getIso()));
        return contDetails.toString();
    }


}
