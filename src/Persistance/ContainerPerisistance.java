package Persistance;

import Models.Containers.*;
import Models.Pollutions;

import java.util.Objects;
import java.util.regex.Pattern;

public class ContainerPerisistance {


    private ContBasic container;

    public ContainerPerisistance(ContBasic container){
        this.container = container;
    }

    public String GenerateContainerString() {
        StringBuffer containerString = new StringBuffer();
        containerString.append(AddContBasicProperties(container));
        containerString.append(String.format("\n\t\tTyp kontenera : %s", container.toString()));

        if (container instanceof IContCooling) {
            containerString.append(AddContCoolingProperties((IContCooling) container));
        }
        if (container instanceof IContExplosive) {
            containerString.append(AddContExplosiveSProperties((IContExplosive) container));
        }
        if (container instanceof IContToxicLiquid) {
            containerString.append(AddContToxicLiquidProperties((IContToxicLiquid) container));
        }
        if (container instanceof IContToxicLoose) {
            containerString.append(AddContToxicLooseProperties((IContToxicLoose) container));
        }
        if (container instanceof IContLiquid) {
            containerString.append(AddContLiquidProperties((IContLiquid) container));
        }
        if (container instanceof IContHeavy) {
            containerString.append(AddContHeavyProperties((IContHeavy) container));
        }
        if (container instanceof IContToxic) {
            containerString.append(AddContToxicProperties((IContToxic) container));
        }
        return containerString.toString();
    }

    public enum ContainerType {
        BASIC("Zwykły"),
        COOLING("Lodówka"),
        EXPLOSIVE("Wybuchowy"),
        HEAVY("Ciężki"),
        LIQUID("Płynny"),
        TOXIC_LIQUID("Toksyczny płynny"),
        TOXIC_LOOSE("Toksyczny sypki");
        public String name;
        ContainerType(String name){
            this.name = name;
        }
    }

    private static Pattern containerIdPattern = Pattern.compile("Id Kontenera : (.*)\n");
    private static Pattern containerTypePattern = Pattern.compile("Typ kontenera : (.*)\n");
    private static Pattern containerInsurancePattern = Pattern.compile("Wysokość ubezpieczenia : (.*)\n");
    private static Pattern containerCompoundName = Pattern.compile("Nazwa płynu : (.*)\n");
    private static Pattern containerIsoPattern = Pattern.compile("Iso : (.*)\n");
    private static Pattern containerDensity = Pattern.compile("Gęstość : (.*)\n");
    private static Pattern containerIsSublimating = Pattern.compile("Niebezpieczeństwo trujących oparów : (.*)\n");
    private static Pattern containerPollutionType = Pattern.compile("Rodzaj niebezpieczeństwa : (.*)\n");
    private static Pattern containerBlastRadius = Pattern.compile("Zasięg rażenia : (.*)\n");
    private static Pattern containerAmperagePattern = Pattern.compile("Pobór prundu : (.*)\n");
    private static Pattern containerWeightPattern = Pattern.compile("Waga Kontenera : (.*)\n");


    public static ContBasic getContainerWithParameters(String containerString) {
        String containerTypeString = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerTypePattern);
        ContBasic container = GetContainerType(containerTypeString, containerString);
        return container;
    }

    private static ContBasic GetContainerType(String containerTypeString, String containerString) {
        if(Objects.equals(containerTypeString, ContainerType.COOLING.name))
            return (ContBasic) GenerateCoolingConatiner(containerString);

        else if(Objects.equals(containerTypeString, ContainerType.EXPLOSIVE.name))
            return (ContBasic) GenerateExplosiveConatiner(containerString);

        else if(Objects.equals(containerTypeString, ContainerType.HEAVY.name))
            return (ContBasic) GenerateHeavyConatiner(containerString);

        else if(Objects.equals(containerTypeString, ContainerType.TOXIC_LIQUID.name))
            return (ContBasic) GenerateToxicLiquidConatiner(containerString);

        else if(Objects.equals(containerTypeString, ContainerType.TOXIC_LOOSE.name))
            return (ContBasic) GenerateToxicLooseConatiner(containerString);

        else if(Objects.equals(containerTypeString, ContainerType.LIQUID.name))
            return (ContBasic) GenerateLiquidConatiner(containerString);

        else
            return  GenerateBasicConatiner(containerString);
    }
    private static ContLiquid GenerateLiquidConatiner(String containerString) {
        String id = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerIdPattern);
        double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
        int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
        double density = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerDensity);
        return new ContLiquid(id,weight,insuranceValue,density);
    }
    private static ContToxicLiquid GenerateToxicLiquidConatiner(String containerString) {
        String id = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerIdPattern);
        double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
        int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
        int iso  = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerIsoPattern);
        Pollutions pollutionType = Pollutions.valueOf(PersistanceStatics.PatternProperties.getStringProperty(containerString, containerPollutionType));
        double density = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerDensity);
        String compoundName = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerCompoundName);
        return new ContToxicLiquid(id,weight,insuranceValue,iso,density,pollutionType,compoundName);
    }
    private static ContToxicLoose GenerateToxicLooseConatiner(String containerString) {
        String id = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerIdPattern);
        double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
        int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
        int iso  = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerIsoPattern);
        Pollutions pollutionType = Pollutions.valueOf(PersistanceStatics.PatternProperties.getStringProperty(containerString, containerPollutionType));
        Boolean isSublimating = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerIsSublimating)=="tak"?true:false;
        return new ContToxicLoose(id,weight,insuranceValue,iso,pollutionType,isSublimating);
    }
    private static ContExplosive GenerateExplosiveConatiner(String containerString) {
        String id = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerIdPattern);
        double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
        int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
        int iso  = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerIsoPattern);
        double blastradius = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerBlastRadius);
        return new ContExplosive(id,weight,insuranceValue,iso,blastradius);
    }
    private static ContCooling GenerateCoolingConatiner(String containerString) {
        String id = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerIdPattern);
        double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
        int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
        int iso  = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerIsoPattern);;
        double amperage = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerAmperagePattern);
        return new ContCooling(id,weight,insuranceValue,iso,amperage);
    }
    private static ContHeavy GenerateHeavyConatiner(String containerString) {
        String id = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerIdPattern);
        double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
        int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
        int iso  = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerIsoPattern);;
        return new ContHeavy(id,weight,insuranceValue,iso);
    }
    private static ContBasic GenerateBasicConatiner(String containerString) {
        String id = PersistanceStatics.PatternProperties.getStringProperty(containerString, containerIdPattern);
        double weight = PersistanceStatics.PatternProperties.getDoubleProperty(containerString, containerWeightPattern);
        int insuranceValue = PersistanceStatics.PatternProperties.getIntProperty(containerString, containerInsurancePattern);
        return new ContBasic(id,weight,insuranceValue);
    }
    private String AddContToxicLiquidProperties(IContToxicLiquid container) {
        StringBuffer contDetails = new StringBuffer();
        contDetails.append(String.format("\n\t\tNazwa płynu : %s", container.getCompoundName()));
        return contDetails.toString();
    }
    private String AddContHeavyProperties(IContHeavy contHeavy) {
        StringBuffer contDetails = new StringBuffer();
        contDetails.append(String.format("\n\t\tIso : %s", contHeavy.getIso()));
        return contDetails.toString();
    }
    private String AddContLiquidProperties(IContLiquid contLiquid) {
        StringBuffer contDetails = new StringBuffer();
        contDetails.append(String.format("\n\t\tGęstość : %s", contLiquid.getDensity()));
        return contDetails.toString();
    }
    private String AddContToxicLooseProperties(IContToxicLoose contToxicLoose) {
        StringBuffer contDetails = new StringBuffer();
        contDetails.append(String.format("\n\t\tNiebezpieczeństwo trujących oparów : %s", contToxicLoose.getIsSublimating()?"tak":"nie"));
        return contDetails.toString();
    }
    private String AddContToxicProperties(IContToxic contToxic) {
        StringBuffer contDetails = new StringBuffer();
        contDetails.append(String.format("\n\t\tRodzaj niebezpieczeństwa : %s", contToxic.getPollutionType()));
        return contDetails.toString();
    }
    private String AddContExplosiveSProperties(IContExplosive contExplosive) {
        StringBuffer contDetails = new StringBuffer();
        contDetails.append(String.format("\n\t\tZasięg rażenia : %s", contExplosive.getBlastRadius()));
        return contDetails.toString();
    }
    private String AddContCoolingProperties(IContCooling contCooling) {
        StringBuffer contDetails = new StringBuffer();
        contDetails.append(String.format("\n\t\tPobór prundu : %s", contCooling.getAmperage()));
        return contDetails.toString();
    }
    private String AddContBasicProperties(ContBasic contBasic) {
        StringBuffer contDetails = new StringBuffer();
        contDetails.append(String.format("\n\t\tId Kontenera : %s", contBasic.getId()));
        contDetails.append(String.format("\n\t\tWysokość ubezpieczenia : %s", contBasic.getInsuranceValue()));
        contDetails.append(String.format("\n\t\tWaga Kontenera : %s", contBasic.getWeight()));
        return contDetails.toString();
    }
}
