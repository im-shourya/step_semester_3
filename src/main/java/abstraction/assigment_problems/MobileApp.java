package abstraction.assigment_problems;

public class MobileApp implements RemoteControllable {

    private final String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public String connect(String appId) {
        return appName + " connected to " + appId;
    }
}
