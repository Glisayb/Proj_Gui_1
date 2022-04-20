package Models.Containers;

public class ContExplosive extends ContHeavy implements IContExplosive{
    private double blastRadius;

    public ContExplosive(double weight, int insuranceValue, int iso, double blastRadius) {

        super(weight, insuranceValue, iso);
        this.blastRadius = blastRadius;
    }

    @Override
    public double getBlastRadius() {
        return blastRadius;
    }

    @Override
    public void setBlastRadius(double blastRadius) {

    }
}
