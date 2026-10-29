package abstraction.assigment_problems;

public class UserProfile implements Exportable {

    private final String username;

    public UserProfile(String username) {
        this.username = username;
    }

    @Override
    public String exportData() {
        ExportDesk.recordExport();
        return "Exported profile: " + username;
    }
}
