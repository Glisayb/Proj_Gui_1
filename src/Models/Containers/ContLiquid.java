package Models.Containers;

public class ContLiquid extends ContBasic implements IContLiquid{
    private double density;

    ContLiquid(double weight, int insuranceValue, double density) {

        super(weight, insuranceValue);
        this.density = density;

    }

    @Override
    public double getDensity() {
        return density;
    }

    @Override
    public void setDensity(double density) {

    }
}
