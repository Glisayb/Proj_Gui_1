package Models;

public enum Days {
    ContToxicLoose(14),
    ContToxicLiquid(10),
    ContExplosive(5),
    ContBasic(0);

    int maxStorage;

    Days(int maxStorage){
        this.maxStorage = maxStorage;
    }
}
