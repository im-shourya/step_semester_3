package abstraction.assigment_problems;

public class Forklift extends ServiceableVehicle implements Insurable {

    private final String assetTag;

    public Forklift(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getAssetTag() {
        return assetTag;
    }

    @Override
    public String performMaintenance() {
        return "Forklift " + assetTag + ": hydraulic and fork inspection complete";
    }

    @Override
    public String getInsuranceInfo() {
        return "Insured under fleet policy - Asset " + assetTag;
    }
}
