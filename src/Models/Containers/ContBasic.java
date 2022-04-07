package Models.Containers;

import com.company.StaticClasses;

public class ContBasic {

String Id;

double weight;
int insuranceValue;

    ContBasic(double weight, int insuranceValue){

        Id = StaticClasses.IdGenerator.Generate();

        this.weight = weight;
        this.insuranceValue = insuranceValue;

    }
}
