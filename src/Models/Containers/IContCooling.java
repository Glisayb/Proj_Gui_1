package Models.Containers;

public interface IContCooling extends IContElectrified {

    @Override
    double getAmperage();

    @Override
    void setAmperage(double amperage);

}
