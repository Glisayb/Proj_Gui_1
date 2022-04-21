package Models;

import Models.Containers.*;
import java.time.LocalDate;

public class WarehouseItem {
    private ContBasic container;
    private LocalDate expirationDate;
    private LocalDate loadDate;

    public WarehouseItem(ContBasic container, LocalDate loadDate) {
        this.container = container;
        this.loadDate = loadDate;
        expirationDate = null;
    }
    public WarehouseItem(ContBasic container, LocalDate loadDate, LocalDate expirationDate) {
        this.container = container;
        this.loadDate = loadDate;
        this.expirationDate = expirationDate;
    }

    public WarehouseItem(ContToxicLoose container, LocalDate loadDate) {
        this.container = container;
        this.loadDate = loadDate;
        expirationDate = loadDate.plusDays
                (Days.ContToxicLoose.maxStorage);
    }

    public WarehouseItem(ContToxicLiquid container, LocalDate loadDate) {
        this.container = container;
        this.loadDate = loadDate;
        expirationDate = loadDate.plusDays
                (Days.ContToxicLiquid.maxStorage);
    }

    public WarehouseItem(ContExplosive container, LocalDate loadDate) {
        this.container = container;
        this.loadDate = loadDate;
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
    public LocalDate getLoadDate() { return loadDate;}
    public LocalDate getExpirationDate() { return expirationDate;}
    public double getWeight() {return container.getWeight();}
}
