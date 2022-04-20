package Models.Containers;

public class ContCooling extends ContHeavy implements IContCooling{
    private double amperage;


    public ContCooling(double weight, int insuranceValue, int iso, double amperage) {

        super(weight, insuranceValue, iso);
        this.amperage = amperage;

    }

    @Override
    public double getAmperage() {
        return amperage;
    }

    @Override
    public void setAmperage(double amperage) {

    }
}
