package encapsulation.assigment_problems;

public class LibraryMember {
    private static final int MIN_ID_LENGTH = 4;

    /*
     * Access levels chosen for each field:
     * - membershipId : private  -> identity of the member; never changed or read directly outside.
     * - branchCode   : default  -> only branch tools in this same package need it.
     * - finesOwed    : protected -> premium-membership subclasses (another package) settle fines.
     * - displayName  : private  -> exposed read-only through a public getter.
     */
    private final String membershipId;
    String branchCode;
    protected double finesOwed;
    private final String displayName;

    // No no-argument constructor: declaring this one stops Java from generating a default one.
    public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
        String trimmedId = membershipId == null ? "" : membershipId.trim();
        if (trimmedId.length() < MIN_ID_LENGTH) {
            throw new IllegalArgumentException("membershipId must have at least "
                    + MIN_ID_LENGTH + " non-blank characters: \"" + membershipId + "\"");
        }
        this.membershipId = trimmedId;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getFinesOwed() {
        return finesOwed;
    }

    public static void main(String[] args) {
        String[] ids = {"LB9", "LB94", "   ", "", null, "  LB94  "};

        for (String id : ids) {
            try {
                LibraryMember member = new LibraryMember(id, "BR1", 0, "Priya Nair");
                System.out.println("\"" + id + "\" -> created with id " + member.getMembershipId());
            } catch (IllegalArgumentException e) {
                System.out.println("\"" + id + "\" -> construction rejected");
            }
        }
    }
}
