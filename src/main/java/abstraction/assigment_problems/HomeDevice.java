package abstraction.assigment_problems;

public abstract class HomeDevice {

    private static int counter = 1000;

    private final String serialNumber;

    protected HomeDevice() {
        counter++;
        serialNumber = "HD-" + counter;
    }

    public abstract String activate();

    public String getSerialNumber() {
        return serialNumber;
    }
}
