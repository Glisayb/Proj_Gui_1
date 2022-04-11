package Models;

public class ShipCapacityInfo {

    int capacity;
    double weight;
    int heavy;
    int electrified;
    int hazardous;

    public ShipCapacityInfo(int capacity, double weight, int heavy, int electrified, int hazardous) {

        this.capacity = capacity;
        this.weight = weight;
        this.heavy = heavy;
        this.electrified = electrified;
        this.hazardous = hazardous;
    }
    public ShipCapacityInfo() {

        this.capacity = 0;
        this.weight = 0;
        this.heavy = 0;
        this.electrified = 0;
        this.hazardous = 0;
    }

}
