package Models.Containers;

public class ContToxicLoose extends ContHeavy implements IContToxic{
    public ContToxicLoose(double weight, int insuranceValue, int iso, String pollutionType) {

        super(weight, insuranceValue, iso);
        setPollutionType(pollutionType);

    }

    @Override
    public String getPollutionType() {
        return null;
    }

    @Override
    public void setPollutionType(String pollutionType) {

    }
}
