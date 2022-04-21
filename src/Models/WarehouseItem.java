package Models;

import Models.Containers.*;
import com.company.StaticClasses;

import java.time.LocalDate;

public class WarehouseItem {
    private ContBasic container;
    private LocalDate expirationDate;
    private LocalDate loadDate;

    public WarehouseItem(ContBasic container, LocalDate loadDate, StorageDaysLimit storageDaysLimit) {
        this.container = container;
        this.loadDate = loadDate;
        if(storageDaysLimit == StorageDaysLimit.None)
            expirationDate = null;
        else
            expirationDate = loadDate.plusDays(storageDaysLimit.maxStorage);
    }

    public String getId() {

        return container.getId();
    }

    public ContBasic getContainer() {
        return container;
    }
    public LocalDate getLoadDate() { return loadDate;}
    public LocalDate getExpirationDate() { return expirationDate;}
    public double getWeight() {return container.getWeight();}
}
