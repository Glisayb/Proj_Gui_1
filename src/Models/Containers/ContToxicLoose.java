package Models.Containers;

import Models.Pollutions;

public class ContToxicLoose extends ContHeavy implements IContToxicLoose{
    boolean isSublimating;

    public ContToxicLoose(double weight, int insuranceValue, int iso, Pollutions pollutionType, boolean isSublimating) {

        super(weight, insuranceValue, iso);
        setPollutionType(pollutionType);
        this.isSublimating = isSublimating;

    }

    @Override
    public Pollutions getPollutionType() {
        return null;
    }

    @Override
    public void setPollutionType(Pollutions pollutionType) {

    }

    @Override
    public boolean getIsSublimating() {
        return false;
    }

    @Override
    public void setIsSublimating(boolean isSublimating) {

    }
}
