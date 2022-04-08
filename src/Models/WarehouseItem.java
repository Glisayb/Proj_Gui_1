package Models;

import Models.Containers.*;

import java.time.LocalDate;
import java.util.Date;

public class WarehouseItem {
    ContBasic container;
    LocalDate expirationDate;

    public WarehouseItem(ContBasic container, LocalDate loadDate){
        this.container = container;
        expirationDate = null;
    }

    WarehouseItem(ContToxicLoose container, LocalDate loadDate){

        this.container = container;
        expirationDate = loadDate.plusDays(Days.ContToxicLoose.maxStorage);

    }
    WarehouseItem(ContToxicLiquid container, LocalDate loadDate){

        this.container = container;
        expirationDate = loadDate.plusDays(Days.ContToxicLiquid.maxStorage);

    }
    WarehouseItem(ContExplosive container,  LocalDate loadDate){

        this.container = container;
        expirationDate = loadDate.plusDays(Days.ContExplosive.maxStorage);

    }
    WarehouseItem(ContBasic container){

        this.container = container;

    }

}
