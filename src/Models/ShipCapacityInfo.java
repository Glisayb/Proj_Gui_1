package Models;

public class ShipCapacityInfo {

    int maxCapacity;
    double maxWeight;
    int maxHeavy;
    int maxElectrified;
    int maxHazzardous;

    public ShipCapacityInfo(int maxCapacity,double maxWeight,int maxHeavy,int maxElectrified,int maxHazzardous){

        this.maxCapacity = maxCapacity;
        this.maxWeight = maxWeight;
        this.maxHeavy = maxHeavy;
        this.maxElectrified = maxElectrified;
        this.maxHazzardous = maxHazzardous;

    }

}
