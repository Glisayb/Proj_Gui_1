package Models.Containers;

import Models.Pollutions;
import Persistance.ContainerPerisistance;

public class ContToxicLoose extends ContHeavy implements IContToxicLoose{
    private boolean sublimating;
    private Pollutions pollutionType;

    public ContToxicLoose(double weight, int insuranceValue, int iso, Pollutions pollutionType, boolean isSublimating) {
        super(weight, insuranceValue, iso);
        this.pollutionType = pollutionType;
        this.sublimating = isSublimating;
    }
    public ContToxicLoose(String id, double weight, int insuranceValue, int iso, Pollutions pollutionType, boolean isSublimating) {

        super(id, weight, insuranceValue, iso);
        this.pollutionType = pollutionType;
        this.sublimating = isSublimating;
    }
    @Override
    public boolean getIsSublimating() {
        return sublimating;
    }

    @Override
    public void setIsSublimating(boolean sublimating) {
        this.sublimating = sublimating;
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
        return (ContainerPerisistance.ContainerType.TOXIC_LOOSE.name);
    }
}
