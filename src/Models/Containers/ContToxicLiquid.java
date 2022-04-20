package Models.Containers;

import Models.Pollutions;
import Persistance.ContainerPerisistance;

public class ContToxicLiquid extends ContHeavy implements IContToxicLiquid{

    private String compoundName;
    private double density;
    private Pollutions pollutionType;

    public ContToxicLiquid(double weight, int insuranceValue, int iso, double density, Pollutions pollutionType, String compoundName) {

        super(weight, insuranceValue, iso);
        this.density = density;
        this.pollutionType = pollutionType;
        this.compoundName = compoundName;
    }
    public ContToxicLiquid(String id, double weight, int insuranceValue, int iso, double density, Pollutions pollutionType, String compoundName) {

        super(id, weight, insuranceValue, iso);
        this.density = density;
        this.pollutionType = pollutionType;
        this.compoundName = compoundName;
    }

    @Override
    public String getCompoundName() {
        return compoundName;
    }

    @Override
    public void setCompoundName(String compoundName) {
        this.compoundName = compoundName;
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
    public Pollutions getPollutionType() {
        return pollutionType;
    }

    @Override
    public void setPollutionType(Pollutions pollutionType) {
        this.pollutionType = pollutionType;
    }

    @Override
    public String toString() {
        return (ContainerPerisistance.ContainerType.TOXIC_LIQUID.name);
    }
}
