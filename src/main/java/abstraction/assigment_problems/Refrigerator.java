package abstraction.assigment_problems;

public class Refrigerator extends HomeDevice implements EnergyTrackable {

    private final double consumptionWatts;

    public Refrigerator(double consumptionWatts) {
        super();
        this.consumptionWatts = consumptionWatts;
    }

    @Override
    public String activate() {
        return "Refrigerator " + getSerialNumber() + " started cooling";
    }

    @Override
    public double getConsumptionWatts() {
        return consumptionWatts;
    }
}
