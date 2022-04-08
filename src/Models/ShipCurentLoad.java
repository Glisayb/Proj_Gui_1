package Models;

import Models.Containers.ContBasic;

public class ShipCurentLoad implements LoadOnShip {

    int curentCapacity;
    double curentWeight;
    int curentHeavy;
    int curentElectrified;
    int curentHazzardous;

    public ShipCurentLoad(int curentCapacity, double curentWeight, int curentHeavy, int curentElectrified, int curentHazzardous) {

        this.curentCapacity = curentCapacity;
        this.curentWeight = curentWeight;
        this.curentHeavy = curentHeavy;
        this.curentElectrified = curentElectrified;
        this.curentHazzardous = curentHazzardous;
    }

    public ShipCurentLoad() {

        this.curentCapacity = 0;
        this.curentWeight = 0;
        this.curentHeavy = 0;
        this.curentElectrified = 0;
        this.curentHazzardous = 0;

    }

    @Override
    public ContBasic getContOnShip() {
        return null;
    }

    @Override
    public void setContOnShip(ContBasic contOnShip) {

    }
}