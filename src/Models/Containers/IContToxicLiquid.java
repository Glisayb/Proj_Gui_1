package Models.Containers;

public interface IContToxicLiquid extends IContToxic, IContLiquid{

    String getCompoundName ();
    void setCompoundName(String compoundName);

}
