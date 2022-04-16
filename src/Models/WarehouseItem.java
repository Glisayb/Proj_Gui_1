package Models;

import Models.Containers.*;
import java.time.LocalDate;

public class WarehouseItem {
    private ContBasic container;
    private LocalDate expirationDate;

    public WarehouseItem(ContBasic container, LocalDate loadDate) {
        this.container = container;
        expirationDate = null;
    }

    public WarehouseItem(ContToxicLoose container, LocalDate loadDate) {
        this.container = container;
        expirationDate = loadDate.plusDays
                (Days.ContToxicLoose.maxStorage);
    }

    public WarehouseItem(ContToxicLiquid container, LocalDate loadDate) {
        this.container = container;
        expirationDate = loadDate.plusDays
                (Days.ContToxicLiquid.maxStorage);
    }

    public WarehouseItem(ContExplosive container, LocalDate loadDate) {
        this.container = container;
        expirationDate = loadDate.plusDays
                (Days.ContExplosive.maxStorage);
    }

    public WarehouseItem(ContBasic container) {

        this.container = container;
    }

    public String getId() {

        return container.getId();
    }

    public ContBasic getContainer() {
        return container;
    }
}
