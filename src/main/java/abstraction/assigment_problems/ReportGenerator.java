package abstraction.assigment_problems;

public class ReportGenerator implements Exportable {

    private final String reportName;

    public ReportGenerator(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public String exportData() {
        ExportDesk.recordExport();
        return "Exported report: " + reportName;
    }
}
