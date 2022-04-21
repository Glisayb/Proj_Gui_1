package Persistance;

import Models.Containers.*;
import Models.Ship;
import Models.ShipCapacityInfo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ShipPersistance{

    public static class Store {

        public static String PrepareListOfShips(ArrayList<Ship> ships) {
            var sortedShips = ships.stream().sorted(Comparator.comparing(s -> s.name, Comparator.naturalOrder())).toList();
            StringBuffer shipsAsString = new StringBuffer();
            for(Ship ship : sortedShips){
                shipsAsString.append(sortedShips.indexOf(ship) + 1 +".\n");
                shipsAsString.append(PrepareToSave(ship));
            }
            return shipsAsString.toString();
        }

        public static ArrayList<Ship> CreateListOfShipsFromString(String ships) {
           var matchShip = shipPattern.matcher(ships);
           ArrayList<Ship> shipList = new ArrayList<>();
            while (matchShip.find()) {
                String shipString = matchShip.group(0);
                shipList.add(CreateShipFromString(shipString));
            }
            return shipList;
        }

        private static String PrepareToSave(Ship ship) {

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
            sb.append("***\n");
            return sb.toString();
        }

        // pattern statków
        private static Pattern shipPattern = Pattern.compile("Id statku : [.\\s\\S]*?\\*{3}");

        // patterny propertasów statków
        private static Pattern shipIdPattern = Pattern.compile("Id statku : (.*) \\n");
        private static Pattern shipNamePattern = Pattern.compile("Nazwa statku : (.*) \\n");
        private static Pattern homePortPattern = Pattern.compile("Port macierzysty : (.*) \\n");
        private static Pattern destinationPattern = Pattern.compile("Port docelowy : (.*) \\n");
        private static Pattern fromPattern = Pattern.compile("Port wyjściowy : (.*) \\n");
        private static Pattern capacityPattern = Pattern.compile("Ogólna pojemność : (.*) \\n");
        private static Pattern weightPattern = Pattern.compile("Ogolna nośność : (.*) \\n");
        private static Pattern electrifiedPattern = Pattern.compile("Kontenery z podłączeniem do prądu : (.*) \\n");
        private static Pattern hazardousPattern = Pattern.compile("Kontenery niebezpieczne : (.*) \\n");
        private static Pattern heavyPattern = Pattern.compile("Kontenery ciężkie : (.*) \\n");

        // pattern kontenerów
        private static Pattern containerPattern = Pattern.compile("Id Kontenera : [.\\s\\S]*?-{3}\\n");

        private static Ship CreateShipFromString(String shipString) {

            Ship shipWithProperties = getShipParameters(shipString);
            ArrayList<ContBasic> shipContainers = new ArrayList<>();

            var matchShip = containerPattern.matcher(shipString);
            while (matchShip.find()) {
                String containerString = matchShip.group(0);
                shipContainers.add(ContainerPerisistance.getContainerWithParameters(containerString));
            }

            shipWithProperties.containerList = shipContainers;
            return shipWithProperties;
        }

        public static Ship getShipParameters(String statek) {
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

}
