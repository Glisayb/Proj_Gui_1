package Models;

public class ShipCapacityInfo {

    public int capacity;
    public double weight;
    public int heavy;
    public int electrified;
    public int hazardous;

    public ShipCapacityInfo(int capacity, double weight, int heavy, int electrified, int hazardous) {

        this.capacity = capacity;
        this.weight = weight;
        this.heavy = heavy;
        this.electrified = electrified;
        this.hazardous = hazardous;
    }

}
