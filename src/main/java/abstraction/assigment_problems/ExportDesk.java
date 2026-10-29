package abstraction.assigment_problems;

// The two exportable classes share nothing else, so the single shared counter lives here.
public class ExportDesk {

    private static int totalExports = 0;

    static void recordExport() {
        totalExports++;
    }

    public static int getTotalExports() {
        return totalExports;
    }

    public static void exportAll(Exportable[] items) {
        for (Exportable item : items) {
            System.out.println(item.exportData());
        }
    }

    public static void main(String[] args) {
        ReportGenerator r = new ReportGenerator("Sales Q1");
        UserProfile u = new UserProfile("jane_doe");

        Exportable ref = r;
        exportAll(new Exportable[]{ref, u});
        System.out.println(getTotalExports());
    }
}
