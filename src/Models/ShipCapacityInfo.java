package Models;

public class ShipCapacityInfo {

    int capacity;
    double weight;
    int heavy;
    int electrified;
    int hazzardous;

    public ShipCapacityInfo(int capacity, double weight, int heavy, int electrified, int hazzardous) {

        this.capacity = capacity;
        this.weight = weight;
        this.heavy = heavy;
        this.electrified = electrified;
        this.hazzardous = hazzardous;
    }
    public ShipCapacityInfo() {

        this.capacity = 0;
        this.weight = 0;
        this.heavy = 0;
        this.electrified = 0;
        this.hazzardous = 0;
    }

}
