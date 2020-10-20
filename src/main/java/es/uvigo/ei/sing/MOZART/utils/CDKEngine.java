package es.uvigo.ei.sing.MOZART.utils;

import lombok.Getter;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.qsar.DescriptorEngine;
import org.openscience.cdk.qsar.descriptors.molecular.*;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smiles.SmilesParser;

import java.util.*;


@Getter
public class CDKEngine {


    private final Set<String> allNames = new HashSet<>();
    private  DescriptorEngine engine;


    private final Map<String, String> descriptionsDefault = new LinkedHashMap<>();
    private final SmilesParser smilesParser = new SmilesParser(SilentChemObjectBuilder.getInstance());

    public CDKEngine() {
//            http://www.rsc.org/suppdata/c9/np/c9np00064j/c9np00064j1.pdf
        engine = new DescriptorEngine(Arrays.asList(AcidicGroupCountDescriptor.class.getName(),
//                    ALOGPDescriptor.class.getName(),
//                    AminoAcidCountDescriptor.class.getName(),
//                    APolDescriptor.class.getName(),
//                    AromaticAtomsCountDescriptor.class.getName(),
//                    AromaticBondsCountDescriptor.class.getName(),
//                    AtomCountDescriptor.class.getName(),
                AutocorrelationDescriptorCharge.class.getName(),
                AutocorrelationDescriptorMass.class.getName(),
                AutocorrelationDescriptorPolarizability.class.getName(),
//                    BasicGroupCountDescriptor.class.getName(),
//                    BCUTDescriptor.class.getName(),
//                    BondCountDescriptor.class.getName(),
//                    BPolDescriptor.class.getName(),
//                    CarbonTypesDescriptor.class.getName(),
                ChiChainDescriptor.class.getName(),
                ChiClusterDescriptor.class.getName(),
                ChiPathClusterDescriptor.class.getName(),
                ChiPathDescriptor.class.getName(),
////                    CPSADescriptor.class.getName(),
//                    EccentricConnectivityIndexDescriptor.class.getName(),
//                    FMFDescriptor.class.getName(),
//                    FractionalPSADescriptor.class.getName(),
//                    FragmentComplexityDescriptor.class.getName(),
////                    GravitationalIndexDescriptor.class.getName(),
//                    HBondAcceptorCountDescriptor.class.getName(),
//                    HBondDonorCountDescriptor.class.getName(),
//                    HybridizationRatioDescriptor.class.getName(),
////                    IPMolecularLearningDescriptor.class.getName(),
//                    KappaShapeIndicesDescriptor.class.getName(),
//                    KierHallSmartsDescriptor.class.getName(),
//                    LargestChainDescriptor.class.getName(),
//                    LargestPiSystemDescriptor.class.getName(),
//                    LengthOverBreadthDescriptor.class.getName(),
//                    LongestAliphaticChainDescriptor.class.getName(),
//                    MannholdLogPDescriptor.class.getName(),
//                    MDEDescriptor.class.getName(),
////                    MomentOfInertiaDescriptor.class.getName(),
//                    PetitjeanNumberDescriptor.class.getName(),
//                    PetitjeanShapeIndexDescriptor.class.getName(),
//                    RotatableBondsCountDescriptor.class.getName(),
//                    RuleOfFiveDescriptor.class.getName(),
//                    SmallRingDescriptor.class.getName(),
                TPSADescriptor.class.getName(),
////                    VABCDescriptor.class.getName(),
//                    VAdjMaDescriptor.class.getName(),
//                    WeightDescriptor.class.getName(),
                WeightedPathDescriptor.class.getName()
//                    WHIMDescriptor.class.getName(),
//                    WienerNumbersDescriptor.class.getName(),
//                    XLogPDescriptor.class.getName(),
//                    ZagrebIndexDescriptor.class.getName(),
//
//                    CPSADescriptor.class.getName(),
////                    GravitationalIndexDescriptor.class.getName(),
////                    LengthOverBreadthDescriptor.class.getName(),
////                    MomentOfInertiaDescriptor.class.getName(),
//                    PetitjeanShapeIndexDescriptor.class.getName()
////                    WHIMDescriptor.class.getName()

        ),
                DefaultChemObjectBuilder.getInstance());


        engine.getDescriptorInstances().stream().forEach(
                iDescriptor ->

                        allNames.addAll(Arrays.asList(iDescriptor.getDescriptorNames()))

        );
        descriptionsDefault.put("SMILE", "ERROR");

        allNames.stream().forEach(s -> {
            descriptionsDefault.computeIfAbsent(s, key -> "0");
        });

    }


    public Map<String, String> getDescriptionsDefault() {
        return new  LinkedHashMap<>(descriptionsDefault);
    }


}
