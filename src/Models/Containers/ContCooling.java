package Models.Containers;

import Persistance.ContainerPerisistance;

public class ContCooling extends ContHeavy implements IContCooling{
    private double amperage;


    public ContCooling(double weight, int insuranceValue, int iso, double amperage) {

        super(weight, insuranceValue, iso);
        this.amperage = amperage;
    }
    public ContCooling(String id, double weight, int insuranceValue, int iso, double amperage) {

        super(id, weight, insuranceValue, iso);
        this.amperage = amperage;
    }

    @Override
    public double getAmperage() {
        return amperage;
    }

    @Override
    public void setAmperage(double amperage) {
        this.amperage = amperage;
    }

    @Override
    public String toString() {
        return (ContainerPerisistance.ContainerType.COOLING.name);
    }
}
