package Models;

import Models.Containers.ContBasic;
import Models.Containers.ContExplosive;
import Models.Containers.ContToxicLiquid;
import Models.Containers.ContToxicLoose;

public class WarehouseItem {
    ContBasic container;
    Days counter;

    WarehouseItem(ContToxicLoose container){

        this.container = container;
        counter = Days.ContToxicLoose;

    }
    WarehouseItem(ContToxicLiquid container){

        this.container = container;
        counter = Days.ContToxicLiquid;

    }
    WarehouseItem(ContExplosive container){

        this.container = container;
        counter = Days.ContExplosives;

    }
    WarehouseItem(ContBasic container){

        this.container = container;

    }

}
