package Persistance;

import Models.Containers.ContBasic;
import Models.Warehouse;
import Models.WarehouseItem;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WarehousePersistance {

    public static class Store {

        Warehouse warehouse;
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        public static String PrepareToSave(Warehouse warehouse) {

            StringBuffer sb = new StringBuffer();
            sb.append(String.format("Nazwa magazynu : %s\n", warehouse.name));
            sb.append(String.format("Pojemność magazynu : %s\n", warehouse.capacity));

            var weightComparator = Comparator.comparing(WarehouseItem::getWeight);
            var dateComparator = Comparator.comparing(WarehouseItem::getLoadDate);
            var sortedWarehouseIteams = warehouse.warCollection.stream().
                    sorted(weightComparator).
                    sorted(dateComparator).
                    collect(Collectors.toList());

            sb.append("Kontenery w magazynie : \n");

            for (int i = 0; i < sortedWarehouseIteams.size(); i++) {
                WarehouseItem warehouseItem = sortedWarehouseIteams.get(i);
                ContBasic container = warehouseItem.getContainer();
                sb.append("\t" + (i + 1) + ". ");

                ContainerPerisistance containerPerisistance = new ContainerPerisistance(container);
                String containerString = containerPerisistance.GenerateContainerString();
                sb.append(containerString);
                sb.append(String.format("\n\t\tData załadowania : %s", warehouseItem.getLoadDate().format(formatter)));
                sb.append(String.format("\n\t\tData końca składowania : %s", warehouseItem.getExpirationDate()));
                sb.append("\n\t\t---\n");
            }
            sb.append("***\n\t\t");
            return sb.toString();
        }

        // pattern magazynu
        private static Pattern warehousePattern = Pattern.compile("Nazwa magazynu : [.\\s\\S]*?\\*{3}");

        // patterny propertasów magazyny
        private static Pattern warehouseNamePattern = Pattern.compile("Nazwa magazynu : (.*)\\n");
        private static Pattern capacityPattern = Pattern.compile("Pojemność magazynu : (.*)\\n");

        // pattern kontenerów
        private static Pattern warehouseIteamPattern = Pattern.compile("Id Kontenera : [.\\s\\S]*?-{3}\\n");
        // pattern kontenerów WHiteamów
        private static Pattern loadDataPattern = Pattern.compile("Data załadowania : (.*)\\n");
        private static Pattern expirationDataPattern = Pattern.compile("Data końca składowania : (.*)\\n");


        public static Warehouse getWarehouseParamiters(String statek) {
            String warehouseName = PersistanceStatics.PatternProperties.getStringProperty(statek, warehouseNamePattern);
            int capacity = PersistanceStatics.PatternProperties.getIntProperty(statek, capacityPattern);

            Warehouse importWarehouse = new Warehouse(warehouseName, capacity);
            importWarehouse.toString();
            return importWarehouse;
        }

        public static Warehouse CreateWarehouseFromString(String warehouseString) {

            Warehouse warehouseWithProperties = getWarehouseParamiters(warehouseString);
            ArrayList<WarehouseItem> warehouseItems = new ArrayList<>();

            var matchWarehouse = warehouseIteamPattern.matcher(warehouseString);
            while (matchWarehouse.find()) {
                String wareHouseString = matchWarehouse.group(0);
                String dateString = PersistanceStatics.PatternProperties.getStringProperty(warehouseString, loadDataPattern);
                var date = LocalDate.parse(dateString, formatter);
                String expirationDate = PersistanceStatics.PatternProperties.getStringProperty(warehouseString, expirationDataPattern);
                var container = ContainerPerisistance.getContainerWithParameters(warehouseString);
                warehouseItems.add(new WarehouseItem(container, date, Warehouse.DetrmineHowLongContainerCanBeStored(container)));
            }

            warehouseWithProperties.warCollection = warehouseItems;
            return warehouseWithProperties;
        }
    }
}
