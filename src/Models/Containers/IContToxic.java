package Models.Containers;

import Models.Pollutions;

public interface IContToxic {

    Pollutions getPollutionType();
    void setPollutionType(Pollutions pollutionType);

}
