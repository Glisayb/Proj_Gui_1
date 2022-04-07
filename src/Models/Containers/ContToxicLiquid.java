package Models.Containers;

public class ContToxicLiquid extends ContHeavy implements IContLiquid, IContToxic{

    public ContToxicLiquid(double weight, int insuranceValue, int iso, double density, String pollutionType) {

        super(weight, insuranceValue, iso);
        setDensity(density);
        setPollutionType(pollutionType);

    }

    @Override
    public double getDensity() {
        return 0;
    }

    @Override
    public void setDensity(double density) {

    }

    @Override
    public String getPollutionType() {
        return null;
    }

    @Override
    public void setPollutionType(String pollutionType) {

    }
}
