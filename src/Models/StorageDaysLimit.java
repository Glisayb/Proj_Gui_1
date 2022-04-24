package Models;

public enum StorageDaysLimit {
    ContToxicLoose(14),
    ContToxicLiquid(10),
    ContExplosive(5),
    None(0);

    int maxStorage;

    StorageDaysLimit(int maxStorage){
        this.maxStorage = maxStorage;
    }
}
