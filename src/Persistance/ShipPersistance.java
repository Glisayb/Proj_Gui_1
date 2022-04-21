package Persistance;

import Models.Containers.*;
import Models.Ship;
import Models.ShipCapacityInfo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ShipPersistance {

    private Ship ship;

    public ShipPersistance(Ship ship) {

        this.ship = ship;
    }

    public String PrepareToSave() {

        StringBuffer sb = new StringBuffer();
        sb.append(String.format("Id statku : %s \n", ship.shipId));
        sb.append(String.format("Nazwa statku : %s \n", ship.name));
        sb.append(String.format("Port macierzysty : %s \n", ship.homeport));
        sb.append(String.format("Port docelowy : %s \n", ship.destination));
        sb.append(String.format("Port wyjściowy : %s \n", ship.from));

        sb.append("Maksymalna Pojemność statku :\n\t");
        sb.append(String.format("Ogólna pojemność : %s \n\t", ship.shipCapacityMax.capacity));
        sb.append(String.format("Ogolna nośność : %s \n\t", ship.shipCapacityMax.weight));
        sb.append(String.format("Kontenery z podłączeniem do prądu : %s \n\t", ship.shipCapacityMax.electrified));
        sb.append(String.format("Kontenery niebezpieczne : %s \n\t", ship.shipCapacityMax.hazardous));
        sb.append(String.format("Kontenery ciężkie : %s \n\t", ship.shipCapacityMax.heavy));

        var weightComparator = Comparator.comparing(ContBasic::getWeight);
        var sortedContainers = ship.containerList.stream().sorted(weightComparator).collect(Collectors.toList());

        sb.append("Kontenery na statku :\n");

        for (int i = 0; i < sortedContainers.size(); i++) {
            ContBasic container = sortedContainers.get(i);
            sb.append("\t" + (i + 1) + ". ");

            ContainerPerisistance containerPerisistance = new ContainerPerisistance(container);
            String containerString = containerPerisistance.GenerateContainerString();
            sb.append(containerString);
            sb.append("\n\t\t---\n");
        }
        sb.append("***\n\t\t");
        return sb.toString();
    }

    // pattern statków
    private Pattern shipPattern = Pattern.compile("Id statku : [.\\s\\S]*?'*'{3}\\n");

    // patterny propertasów statków
    private Pattern shipIdPattern = Pattern.compile("Id statku : (.*) \\n");
    private Pattern shipNamePattern = Pattern.compile("Nazwa statku : (.*) \\n");
    private Pattern homePortPattern = Pattern.compile("Port macierzysty : (.*) \\n");
    private Pattern destinationPattern = Pattern.compile("Port docelowy : (.*) \\n");
    private Pattern fromPattern = Pattern.compile("Port wyjściowy : (.*) \\n");
    private Pattern capacityPattern = Pattern.compile("Ogólna pojemność : (.*) \\n");
    private Pattern weightPattern = Pattern.compile("Ogolna nośność : (.*) \\n");
    private Pattern electrifiedPattern = Pattern.compile("Kontenery z podłączeniem do prądu : (.*) \\n");
    private Pattern hazardousPattern = Pattern.compile("Kontenery niebezpieczne : (.*) \\n");
    private Pattern heavyPattern = Pattern.compile("Kontenery ciężkie : (.*) \\n");

    // pattern kontenerów
    private Pattern containerPattern = Pattern.compile("Id Kontenera : [.\\s\\S]*?-{3}\\n");

    public Ship CreateShipFromString(String shipString){

        Ship shipWithProperties = getShipParamiters(shipString);
        ArrayList<ContBasic> shipContainers = new ArrayList<>();

        var matchShip = containerPattern.matcher(shipString);
        while(matchShip.find()){
            String containerString = matchShip.group(0);
            shipContainers.add(ContainerPerisistance.getContainerWithParameters(containerString));
        }

        shipWithProperties.containerList = shipContainers;
        return shipWithProperties;
    }

    public Ship getShipParamiters(String statek){
        String shipId = PersistanceStatics.PatternProperties.getStringProperty(statek, shipIdPattern);
        String shipName = PersistanceStatics.PatternProperties.getStringProperty(statek, shipNamePattern);
        String homeport = PersistanceStatics.PatternProperties.getStringProperty(statek, homePortPattern);
        String destination = PersistanceStatics.PatternProperties.getStringProperty(statek, destinationPattern);
        String from = PersistanceStatics.PatternProperties.getStringProperty(statek, fromPattern);

        int capacity = PersistanceStatics.PatternProperties.getIntProperty(statek, capacityPattern);
        double weight = PersistanceStatics.PatternProperties.getDoubleProperty(statek, weightPattern);
        int electrified = PersistanceStatics.PatternProperties.getIntProperty(statek, electrifiedPattern);
        int hazardous = PersistanceStatics.PatternProperties.getIntProperty(statek, hazardousPattern);
        int heavy = PersistanceStatics.PatternProperties.getIntProperty(statek, heavyPattern);

        ShipCapacityInfo capacityInfo = new ShipCapacityInfo(capacity, weight, heavy, electrified, hazardous);

        Ship importShip = new Ship(shipId, shipName, homeport, destination, from, capacityInfo);
        importShip.toString();
        return importShip;
    }



}
