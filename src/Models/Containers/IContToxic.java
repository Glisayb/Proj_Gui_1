package Models.Containers;

import Models.Pollutions;

public interface IContToxic extends IContHazardous {

    Pollutions getPollutionType();
    void setPollutionType(Pollutions pollutionType);

}
