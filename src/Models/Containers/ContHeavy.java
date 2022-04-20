package Models.Containers;

public class ContHeavy extends ContBasic implements IContHeavy{

    private int iso;

    public ContHeavy(double weight, int insuranceValue, int iso){

        super(weight,insuranceValue);
        this.iso = iso;

    }

    @Override
    public int getIso() {
        return iso;
    }

    @Override
    public void setIso(int iso) {
        this.iso = iso;
    }
}
