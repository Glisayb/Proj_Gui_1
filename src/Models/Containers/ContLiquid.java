package Models.Containers;

import Persistance.ContainerPerisistance;

public class ContLiquid extends ContBasic implements IContLiquid{
    private double density;

    public ContLiquid(double weight, int insuranceValue, double density) {
        super(weight, insuranceValue);
        this.density = density;
    }
    public ContLiquid(String id, double weight, int insuranceValue, double density) {
        super(id, weight, insuranceValue);
        this.density = density;
    }

    @Override
    public double getDensity() {
        return density;
    }

    @Override
    public void setDensity(double density) {
        this.density = density;
    }

    @Override
    public String toString() {
        return (ContainerPerisistance.ContainerType.LIQUID.name);
    }
}
