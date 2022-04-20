package Persistance;

import Models.Containers.*;
import Models.Ship;
import Models.ShipCapacityInfo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ShipPersistance {

    private Ship ship;

    private enum ContainerType {
        HEAVY("Ciężki"),
        TOXIC_LIQUID("Toksyczny płynny"),
        TOXIC_LOOSE("Toksyczny sypki"),
        LIQUID("Płynny"),
        EXPLOSIVE("Wybuchowy"),
        COOLING("Lodówka"),
        BASIC("Zwykły");
        private String name;
        ContainerType(String name){

            this.name = name;
        }

    }
    private String heavyContainerName = "Ciężki";
    private String toxicLiquidContainerName = "Toksyczny płynny";
    private String toxicLooseContainerName = "Toksyczny sypki";
    private String liquidContainerName = "Płynny";
    private String explosiveContainerName = "Wybuchowy";
    private String coolingContainerName = "Lodówka";

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
        sb.append(String.format("Ogólna pojemnoś : %s \n\t", ship.shipCapacityMax.capacity));
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
            sb.append(String.format("\tId Kontenera : %s \n\t\t", container.getId()));
            sb.append(String.format("Waga Kontenera : %s", container.getWeight()));
            if (container instanceof ContCooling) {
                sb.append(GenerateContCoolingString((ContCooling) container));
            }
            if (container instanceof ContExplosive) {
                sb.append(GenerateContExplosiveString((ContExplosive) container));
            }
            if (container instanceof ContHeavy) {
                sb.append(GenerateContHeavyString((ContHeavy) container));
            }
            if (container instanceof ContLiquid) {
                sb.append(GenerateContLiquidString((ContLiquid) container));
            }
            if (container instanceof IContToxic) {
                sb.append(GenerateContToxicString((IContToxic) container));
            }
            if (container instanceof ContToxicLiquid) {
                sb.append(GenerateContToxicLiquidString((ContToxicLiquid) container));
            }
            if (container instanceof ContToxicLoose) {
                sb.append(GenerateContToxicLooseString((ContToxicLoose) container));
            }
            if(!sb.toString().contains("Typ kontenera : ")){
                sb.append(GenerateBasicContString(container));
            }
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
    private Pattern capacityPattern = Pattern.compile("Ogólna pojemnoś : (.*) \\n");
    private Pattern weightPattern = Pattern.compile("Ogolna nośność : (.*) \\n");
    private Pattern electrifiedPattern = Pattern.compile("Kontenery z podłączeniem do prądu : (.*) \\n");
    private Pattern hazardousPattern = Pattern.compile("Kontenery niebezpieczne : (.*) \\n");
    private Pattern heavyPattern = Pattern.compile("Kontenery ciężkie : (.*) \\n");

    // pattern kontenerów
    private Pattern containerPattern = Pattern.compile("Id Kontenera : [.\\s\\S]*?-{3}\\n");

    // patterny propertasów kontenerów
    private Pattern containerIdPattern = Pattern.compile("Id Kontenera : (.*) \\n");
    private Pattern containerTypePattern = Pattern.compile("Typ kontenera : (.*) \\n");
    private Pattern containerCompoundName = Pattern.compile("Nazwa Płynu : (.*) \\n");
    private Pattern containerIsoPattern = Pattern.compile("Iso : (.*)\\n");
    private Pattern containerDensity = Pattern.compile("Gęstość : (.*) \\n");
    private Pattern containerIsSublimating = Pattern.compile("Niebezpieczeństwo trujących oparów : (.*) \\n");
    private Pattern containerPollutionType = Pattern.compile("Rodzaj niebezpieczeństwa : (.*) \\n");
    private Pattern containerBlastRadius = Pattern.compile("Zasięg rażenia : (.*) \\n");
    private Pattern containerAmperagePattern = Pattern.compile("Pobór prundu : (.*)\\n");
    private Pattern containerWeightPattern = Pattern.compile("Waga Kontenera : (.*)\\n");


    public Ship CreateShipFromString(String shipString){

        Ship shipWithProperties = getShipParamiters(shipString);
        ArrayList<ContBasic> shipContainers = new ArrayList<>();

        var matchShip = containerPattern.matcher(shipString);
        while(matchShip.find()){
            String containerString = matchShip.group(0);
            shipContainers.add(getContainerWithParameters(containerString));
        }

        shipWithProperties.containerList = shipContainers;
        return shipWithProperties;
    }

    private ContBasic getContainerWithParameters(String containerString) {

        String containerTypeString = getStringProperty(containerString, containerTypePattern);
        ContBasic container = GetContainerType(containerTypeString, containerString);
        return container;
    }

    private ContBasic GetContainerType(String containerTypeString, String containerString) {
        if(Objects.equals(containerTypeString, ContainerType.COOLING.name))
            return (ContBasic) GenerateCoolingConatiner(containerString);

        else if(Objects.equals(containerTypeString, ContainerType.EXPLOSIVE.name))
            return null;

        else if(Objects.equals(containerTypeString, ContainerType.HEAVY.name))
            return null;

        else if(Objects.equals(containerTypeString, ContainerType.TOXIC_LIQUID.name))
            return  null;

        else if(Objects.equals(containerTypeString, ContainerType.TOXIC_LOOSE.name))
            return  null;

        else
            return  null;
    }

    private ContCooling GenerateCoolingConatiner(String containerString) {
        double weight = getDoubleProperty(containerString, containerWeightPattern);
        int insuranceValue = 2; // dorobić patern trzeba 
        int iso  = getIntProperty(containerString, containerIsoPattern);;
        double amperage = getDoubleProperty(containerString, containerAmperagePattern);
        return  new ContCooling(weight,insuranceValue,iso,amperage);
    }


    public Ship getShipParamiters(String statek){
        String shipId = getStringProperty(statek, shipIdPattern);
        String shipName = getStringProperty(statek, shipNamePattern);
        String homeport = getStringProperty(statek, homePortPattern);
        String destination = getStringProperty(statek, destinationPattern);
        String from = getStringProperty(statek, fromPattern);

        int capacity = getIntProperty(statek, capacityPattern);
        double weight = getDoubleProperty(statek, weightPattern);
        int electrified = getIntProperty(statek, electrifiedPattern);
        int hazardous = getIntProperty(statek, hazardousPattern);
        int heavy = getIntProperty(statek, heavyPattern);

        ShipCapacityInfo capacityInfo = new ShipCapacityInfo(capacity, weight, heavy, electrified, hazardous);

        Ship importShip = new Ship(shipId, shipName, homeport, destination, from, capacityInfo);
        importShip.toString();
        return importShip;
    }

    private String getStringProperty(String input, Pattern pattern){
        Matcher propertyMatcher = pattern.matcher(input);
        propertyMatcher.find();
        return propertyMatcher.group(1);
    }
    private int getIntProperty(String input, Pattern pattern){
        Matcher propertyMatcher = pattern.matcher(input);
        propertyMatcher.find();
        return Integer.parseInt(propertyMatcher.group(1));
    }
    private double getDoubleProperty(String input, Pattern pattern){
        Matcher propertyMatcher = pattern.matcher(input);
        propertyMatcher.find();
        return Double.parseDouble(propertyMatcher.group(1));
    }

    private String GenerateBasicContString(ContBasic container) {
        StringBuffer contDetails = new StringBuffer();
        contDetails.append(String.format("\n\t\tTyp kontenera : %s \n\t\t", ContainerType.BASIC.name));
        return contDetails.toString();
    }

    private String GenerateContToxicLiquidString(ContToxicLiquid container) {
        StringBuffer contDetails = new StringBuffer();
        contDetails.append(String.format("\n\t\tTyp kontenera : %s \n\t\t", ContainerType.TOXIC_LIQUID.name));
        contDetails.append(String.format("Nazwa Płynu : %s", container.getCompoundName()));
        return contDetails.toString();
    }

    private String GenerateContHeavyString(ContHeavy contHeavy) {
        StringBuffer contDetails = new StringBuffer();
        contDetails.append(String.format("\n\t\tTyp kontenera : %s \n\t\t", ContainerType.HEAVY.name));
        contDetails.append(String.format("Iso : %s", contHeavy.getIso()));
        return contDetails.toString();
    }

    private String GenerateContLiquidString(ContLiquid contLiquid) {
        StringBuffer contDetails = new StringBuffer();
        contDetails.append(String.format("\n\t\tTyp kontenera : %s \n\t\t", ContainerType.LIQUID.name));
        contDetails.append(String.format("Gęstość : %d", contLiquid.getDensity()));
        return contDetails.toString();
    }

    private String GenerateContToxicLooseString(ContToxicLoose contToxicLoose) {
        StringBuffer contDetails = new StringBuffer();
        contDetails.append(String.format("\n\t\tTyp kontenera : %s \n\t\t", ContainerType.TOXIC_LOOSE.name));
        contDetails.append(String.format("Niebezpieczeństwo trujących oparów : %s", contToxicLoose.getIsSublimating()?"tak":"nie"));
        return contDetails.toString();
    }

    private String GenerateContToxicString(IContToxic contToxic) {
        StringBuffer contDetails = new StringBuffer();
        contDetails.append(String.format("Rodzaj niebezpieczeństwa : %s", contToxic.getPollutionType()));
        return contDetails.toString();
    }

    private String GenerateContExplosiveString(ContExplosive contExplosive) {
        StringBuffer contDetails = new StringBuffer();
        contDetails.append(String.format("\n\t\tTyp kontenera : %s \n\t\t", ContainerType.EXPLOSIVE.name));
        contDetails.append(String.format("Zasięg rażenia : %s", contExplosive.getBlastRadius()));
        return contDetails.toString();
    }

    private String GenerateContCoolingString(ContCooling contCooling) {
        StringBuffer contDetails = new StringBuffer();
        contDetails.append(String.format("\n\t\tTyp kontenera : %s \n\t\t", ContainerType.COOLING.name));
        contDetails.append(String.format("Pobór prundu : %s", contCooling.getAmperage()));
        return contDetails.toString();
    }

}
