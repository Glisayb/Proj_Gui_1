package Models.Containers;

import Models.Pollutions;

public class ContToxicLiquid extends ContHeavy implements IContToxicLiquid{
    String compoundName;

    public ContToxicLiquid(double weight, int insuranceValue, int iso, double density, Pollutions pollutionType, String compoundName) {

        super(weight, insuranceValue, iso);
        setDensity(density);
        setPollutionType(pollutionType);
        this.compoundName = compoundName;

    }

    @Override
    public double getDensity() {
        return 0;
    }

    @Override
    public void setDensity(double density) {

    }

    @Override
    public Pollutions getPollutionType() {
        return null;
    }

    @Override
    public void setPollutionType(Pollutions pollutionType) {

    }

    @Override
    public String getCompoundName() {
        return compoundName;
    }

    @Override
    public void setCompoundName(String compoundName) {

    }
}
