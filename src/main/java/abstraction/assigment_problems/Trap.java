package abstraction.assigment_problems;

public class Trap implements Defendable {

    private final String trapType;

    public Trap(String trapType) {
        this.trapType = trapType;
    }

    @Override
    public String defend() {
        return trapType + " triggers automatically";
    }
}
