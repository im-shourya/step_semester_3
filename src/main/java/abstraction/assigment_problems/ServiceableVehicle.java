package abstraction.assigment_problems;

public abstract class ServiceableVehicle {

    private double mileage;

    public abstract String performMaintenance();

    public double getMileage() {
        return mileage;
    }

    public void addMileage(double km) {
        if (km < 0) {
            System.out.println("Rejected: distance cannot be negative (" + km + ")");
            return;
        }
        mileage += km;
    }
}
