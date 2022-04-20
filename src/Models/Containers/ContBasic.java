package Models.Containers;

import com.company.StaticClasses;

public class ContBasic {

private String id;
private double weight;
private int insuranceValue;

    public ContBasic(double weight, int insuranceValue){

        id = StaticClasses.IdGenerator.Generate();

        this.weight = weight;
        this.insuranceValue = insuranceValue;
    }
    public ContBasic(String id, double weight, int insuranceValue){

        this.id = id;

        this.weight = weight;
        this.insuranceValue = insuranceValue;
    }

    public String getId() {
        return id;
    }


    @Override
    public String toString() {
        return ("Conteiner ID:" + id );
    }

    public int getInsuranceValue(){
        return insuranceValue;
    }
    public double getWeight() {
        return weight;}
}
