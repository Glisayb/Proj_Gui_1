package com.company;

import Exceptions.WarehouseStorageCapacityExceededException;
import Models.Containers.*;
import Models.Pollutions;
import Persistance.ContainerPerisistance;
import Persistance.PersistanceStatics;

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
            if (Objects.equals(containerTypeString, ContainerPerisistance.ContainerType.COOLING.name))
                return (ContBasic) GenerateCoolingConatiner(containerString);

            else if (Objects.equals(containerTypeString, ContainerPerisistance.ContainerType.EXPLOSIVE.name))
                return (ContBasic) GenerateExplosiveConatiner(containerString);

            else if (Objects.equals(containerTypeString, ContainerPerisistance.ContainerType.HEAVY.name))
                return (ContBasic) GenerateHeavyConatiner(containerString);

            else if (Objects.equals(containerTypeString, ContainerPerisistance.ContainerType.TOXIC_LIQUID.name))
                return (ContBasic) GenerateToxicLiquidConatiner(containerString);

            else if (Objects.equals(containerTypeString, ContainerPerisistance.ContainerType.TOXIC_LOOSE.name))
                return (ContBasic) GenerateToxicLooseConatiner(containerString);

            else if (Objects.equals(containerTypeString, ContainerPerisistance.ContainerType.LIQUID.name))
                return (ContBasic) GenerateLiquidConatiner(containerString);

            else
                return GenerateBasicConatiner(containerString);
        }
            
                private static Pattern containerIdPattern = Pattern.compile("Id Kontenera : (.*)");
                private static Pattern containerTypePattern = Pattern.compile("Typ kontenera : (.*)");
                private static Pattern containerInsurancePattern = Pattern.compile("Wysokość ubezpieczenia : (.*)");
                private static Pattern containerCompoundName = Pattern.compile("Nazwa płynu : (.*)");
                private static Pattern containerIsoPattern = Pattern.compile("Iso : (.*)");
                private static Pattern containerDensity = Pattern.compile("Gęstość : (.*)");
                private static Pattern containerIsSublimating = Pattern.compile("Niebezpieczeństwo trujących oparów : (.*)");
                private static Pattern containerPollutionType = Pattern.compile("Rodzaj niebezpieczeństwa : (.*)");
                private static Pattern containerBlastRadius = Pattern.compile("Zasięg rażenia : (.*)");
                private static Pattern containerAmperagePattern = Pattern.compile("Pobór prundu : (.*)");
                private static Pattern containerWeightPattern = Pattern.compile("Waga Kontenera : (.*)");

                private static ContLiquid GenerateLiquidConatiner (String containerString){
                    String id = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerIdPattern);
                    double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
                    int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
                    double density = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerDensity);
                    return new ContLiquid(id, weight, insuranceValue, density);
                }
                private static ContToxicLiquid GenerateToxicLiquidConatiner (String containerString){
                    String id = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerIdPattern);
                    double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
                    int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
                    int iso = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerIsoPattern);
                    Pollutions pollutionType = Pollutions.valueOf(PersistanceStatics.PatternProperties.getStringProperty(containerString, containerPollutionType));
                    double density = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerDensity);
                    String compoundName = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerCompoundName);
                    return new ContToxicLiquid(id, weight, insuranceValue, iso, density, pollutionType, compoundName);
                }
                private static ContToxicLoose GenerateToxicLooseConatiner (String containerString){
                    String id = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerIdPattern);
                    double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
                    int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
                    int iso = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerIsoPattern);
                    Pollutions pollutionType = Pollutions.valueOf(PersistanceStatics.PatternProperties.getStringProperty(containerString, containerPollutionType));
                    Boolean isSublimating = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerIsSublimating)=="tak"?true:false;
                    return new ContToxicLoose(id, weight, insuranceValue, iso, pollutionType, isSublimating);
                }
                private static ContExplosive GenerateExplosiveConatiner (String containerString){
                    String id = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerIdPattern);
                    double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
                    int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
                    int iso = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerIsoPattern);
                    double blastradius = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerBlastRadius);
                    return new ContExplosive(id, weight, insuranceValue, iso, blastradius);
                }
                private static ContCooling GenerateCoolingConatiner (String containerString){
                    String id = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerIdPattern);
                    double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
                    int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
                    int iso = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerIsoPattern);
                    ;
                    double amperage = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerAmperagePattern);
                    return new ContCooling(id, weight, insuranceValue, iso, amperage);
                }
                private static ContHeavy GenerateHeavyConatiner (String containerString){
                    String id = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerIdPattern);
                    double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
                    int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
                    int iso = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerIsoPattern);
                    ;
                    return new ContHeavy(id, weight, insuranceValue, iso);
                }
                private static ContBasic GenerateBasicConatiner (String containerString){
                    String id = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerIdPattern);
                    double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
                    int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
                    return new ContBasic(id, weight, insuranceValue);
                }
}
