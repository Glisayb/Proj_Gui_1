package Models.Containers;

import com.company.StaticClasses;

public class ContBasic {

String id;

double weight;
int insuranceValue;

    public ContBasic(double weight, int insuranceValue){

        id = StaticClasses.IdGenerator.Generate();

        this.weight = weight;
        this.insuranceValue = insuranceValue;

    }

    public String getId() {
        return id;
    }
}
