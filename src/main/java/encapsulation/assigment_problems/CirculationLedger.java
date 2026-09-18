package encapsulation.assigment_problems;

public class CirculationLedger {

    /*
     * Settles one night of receipts in a single pass:
     * - null entries are counted and skipped, never dereferenced.
     * - ReferenceOnlyLoanReceipt: books stay in the reading room, so they are logged against the
     *   room and not added to the books-out-of-building count.
     * - regular LoanReceipt: books leave the building and are added to the books-out count.
     */
    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 reference-only | 0 regular";
        }

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;
        int booksOutOfBuilding = 0;

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            int bookCount = receipt.getBookIds().length;
            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                ReferenceOnlyLoanReceipt reference = (ReferenceOnlyLoanReceipt) receipt;
                System.out.println(receipt.getMemberId() + ": " + bookCount
                        + " book(s) kept in " + reference.getRoomNumber());
                referenceOnly++;
            } else {
                System.out.println(receipt.getMemberId() + ": " + bookCount + " book(s) checked out");
                booksOutOfBuilding += bookCount;
                regular++;
            }
            processed++;
        }

        System.out.println("Books out of the building tonight: " + booksOutOfBuilding);
        return processed + " processed | " + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | " + regular + " regular";
    }

    public static void main(String[] args) {
        LoanReceipt[] receipts = {
                new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
                null,
                new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };

        System.out.println(processNightlyCirculation(receipts));
    }
}
