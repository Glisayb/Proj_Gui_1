package Commands;

import Exceptions.WarehouseStorageCapacityExceededException;
import Models.Containers.*;
import Models.Pollutions;
import Persistance.ContainerPerisistance;
import Persistance.PersistanceStatics;
import com.company.Main;

import java.util.Objects;
import java.util.regex.Pattern;

public class CreateContainerCommand implements ICommand {

    static String warehouseName;
    static String containerTypeString;
    static String containerString;

    public CreateContainerCommand(String warehouseName, String containerTypeString, String containerString) {
        this.warehouseName = warehouseName;
        this.containerTypeString = containerTypeString;
        this.containerString = containerString;

    }

    @Override
    public void execute() throws WarehouseStorageCapacityExceededException {

        var warehouse = Main.warehouses.stream().filter(w -> Objects.equals(w.name, warehouseName)).findFirst();
        if (warehouse.isPresent()) {

            warehouse.get().storeInWarehouse(GenerateContainer(containerString));
        }
    }
            
        public static ContBasic GenerateContainer(String containerString) {
            ContBasic container;
            if (Objects.equals(containerTypeString, ContainerPerisistance.ContainerType.COOLING.name))
                container = GenerateCoolingConatiner(containerString);

            else if (Objects.equals(containerTypeString, ContainerPerisistance.ContainerType.EXPLOSIVE.name))
                container = GenerateExplosiveConatiner(containerString);

            else if (Objects.equals(containerTypeString, ContainerPerisistance.ContainerType.HEAVY.name))
                container = GenerateHeavyConatiner(containerString);

            else if (Objects.equals(containerTypeString, ContainerPerisistance.ContainerType.TOXIC_LIQUID.name))
                container = GenerateToxicLiquidConatiner(containerString);

            else if (Objects.equals(containerTypeString, ContainerPerisistance.ContainerType.TOXIC_LOOSE.name))
                container = GenerateToxicLooseConatiner(containerString);

            else if (Objects.equals(containerTypeString, ContainerPerisistance.ContainerType.LIQUID.name))
                container = GenerateLiquidConatiner(containerString);
            else
                container = GenerateBasicConatiner(containerString);
            System.out.printf("Stworzono kontener o id : "+container.getId()+" i dodano do magazynu : "+warehouseName);
            return container;
        }
            
                private static Pattern containerInsurancePattern = Pattern.compile("Wysokość ubezpieczenia ([0-9]*)");
                private static Pattern containerCompoundName = Pattern.compile("Nazwa płynu (.*)");
                private static Pattern containerIsoPattern = Pattern.compile("Iso ([0-9]*)");
                private static Pattern containerDensity = Pattern.compile("Gęstość ([0-9]*)");
                private static Pattern containerIsSublimating = Pattern.compile("Niebezpieczeństwo trujących oparów ([a-zA-Z]{3})");
                private static Pattern containerPollutionType = Pattern.compile("Rodzaj niebezpieczeństwa (.*)");
                private static Pattern containerBlastRadius = Pattern.compile("Zasięg rażenia ([0-9]*)");
                private static Pattern containerAmperagePattern = Pattern.compile("Pobór prundu ([0-9]*)");
                private static Pattern containerWeightPattern = Pattern.compile("Waga kontenera ([0-9]*)");

                private static ContLiquid GenerateLiquidConatiner (String containerString){
                    double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
                    int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
                    double density = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerDensity);
                    return new ContLiquid(weight, insuranceValue, density);
                }
                private static ContToxicLiquid GenerateToxicLiquidConatiner (String containerString){
                    double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
                    int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
                    int iso = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerIsoPattern);
                    Pollutions pollutionType = Pollutions.valueOf(PersistanceStatics.PatternProperties.getStringProperty(containerString, containerPollutionType));
                    double density = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerDensity);
                    String compoundName = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerCompoundName);
                    return new ContToxicLiquid(weight, insuranceValue, iso, density, pollutionType, compoundName);
                }
                private static ContToxicLoose GenerateToxicLooseConatiner (String containerString){
                    double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
                    int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
                    int iso = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerIsoPattern);
                    Pollutions pollutionType = Pollutions.valueOf(PersistanceStatics.PatternProperties.getStringProperty(containerString, containerPollutionType));
                    Boolean isSublimating = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerIsSublimating)=="tak"?true:false;
                    return new ContToxicLoose(weight, insuranceValue, iso, pollutionType, isSublimating);
                }
                private static ContExplosive GenerateExplosiveConatiner (String containerString){
                    double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
                    int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
                    int iso = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerIsoPattern);
                    double blastradius = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerBlastRadius);
                    return new ContExplosive(weight, insuranceValue, iso, blastradius);
                }
                private static ContCooling GenerateCoolingConatiner (String containerString){
                    double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
                    int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
                    int iso = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerIsoPattern);
                    double amperage = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerAmperagePattern);
                    return new ContCooling(weight, insuranceValue, iso, amperage);
                }
                private static ContHeavy GenerateHeavyConatiner (String containerString){
                    double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
                    int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
                    int iso = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerIsoPattern);
                    return new ContHeavy(weight, insuranceValue, iso);
                }
                private static ContBasic GenerateBasicConatiner (String containerString){
                    double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
                    int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
                    return new ContBasic(weight, insuranceValue);
                }
}
