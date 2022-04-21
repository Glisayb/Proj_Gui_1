package Persistance;

import Models.Containers.ContBasic;
import Models.Warehouse;
import Models.WarehouseItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WarehousePersistance {

    private Warehouse warehouse;

    public WarehousePersistance(Warehouse warehouse) {

        this.warehouse = warehouse;
    }

    public String PrepareToSave() {

        StringBuffer sb = new StringBuffer();
        sb.append(String.format("Nazwa magazynu : %s \n", warehouse.name));
        sb.append(String.format("Pojemność magazynu : %s \n\t", warehouse.capacity));

        var weightComparator = Comparator.comparing(WarehouseItem::getWeight);
        var dateComparator = Comparator.comparing(WarehouseItem::getLoadDate);
        var sortedWarehouseIteams = warehouse.warCollection.stream().
                sorted(weightComparator).
                sorted(dateComparator).
                collect(Collectors.toList());

        sb.append("Kontenery w magazynie :\n");

        for (int i = 0; i < sortedWarehouseIteams.size(); i++) {
            WarehouseItem warehouseItem = sortedWarehouseIteams.get(i);
            ContBasic container = warehouseItem.getContainer();
            sb.append("\t" + (i + 1) + ". ");

            ContainerPerisistance containerPerisistance = new ContainerPerisistance(container);
            String containerString = containerPerisistance.GenerateContainerString();
            sb.append(String.format("Data załadowania : %s \n", warehouseItem.getLoadDate()));
            sb.append(String.format("Data końca składowania : %s \n\t", warehouseItem.getExpirationDate()));
            sb.append(containerString);
            sb.append("\n\t\t---\n");
        }
        sb.append("***\n\t\t");
        return sb.toString();
    }

    // pattern magazynu
    private Pattern warehousePattern = Pattern.compile("Nazwa magazynu : [.\\s\\S]*?'*'{3}\\n");

    // patterny propertasów magazyny
    private Pattern warehouseNamePattern = Pattern.compile("Nazwa magazynu : (.*) \\n");
    private Pattern capacityPattern = Pattern.compile("Pojemność magazynu : (.*) \\n");

    // pattern kontenerów
    private Pattern containerPattern = Pattern.compile("Id Kontenera : [.\\s\\S]*?-{3}\\n");
    // pattern kontenerów WHiteamów
    private Pattern loadDataPattern = Pattern.compile("Data załadowania : (.*) \\n");
    private Pattern expirationDataPattern = Pattern.compile("Data końca składowania : (.*) \\n");

    public Warehouse CreateWarehouseFromString(String warehouseString){

        Warehouse warehouseWithProperties = getWarehouseParamiters(warehouseString);
        ArrayList<WarehouseItem> warehouseItems = new ArrayList<>();

        var matchWarehouse = containerPattern.matcher(warehouseString);
        while(matchWarehouse.find()){
            String wareHouseString = matchWarehouse.group(0);
            String loadDate = PersistanceStatics.PatternProperties.getStringProperty(warehouseString, loadDataPattern);
            String expirationDate = PersistanceStatics.PatternProperties.getStringProperty(warehouseString, expirationDataPattern);
            warehouseItems.add(ContainerPerisistance.getContainerWithParameters(warehouseString),loadDate,expirationDate);

        }

        warehouseWithProperties.warCollection = warehouseItems;
        return warehouseWithProperties;
    }

    public Warehouse getWarehouseParamiters(String statek){
        String warehouseName = PersistanceStatics.PatternProperties.getStringProperty(statek, warehouseNamePattern);
        int capacity = PersistanceStatics.PatternProperties.getIntProperty(statek, capacityPattern);

        Warehouse importWarehouse = new Warehouse( warehouseName, capacity);
        importWarehouse.toString();
        return importWarehouse;
    }

}
