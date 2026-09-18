package encapsulation.assigment_problems;

import java.util.Arrays;
import java.util.regex.Pattern;

/*
 * The requirement asks for LoanReceipt to be final, yet ReferenceOnlyLoanReceipt must be a
 * LoanReceipt so it can sit in a LoanReceipt[] and be told apart with instanceof. A plain final
 * class can't have any subclass, so LoanReceipt is 'sealed' instead: it permits exactly one
 * subclass, which is itself final. No other class can ever extend LoanReceipt, which is the
 * guarantee 'final' is there to give.
 */
public sealed class LoanReceipt permits ReferenceOnlyLoanReceipt {
    private static final Pattern BOOK_ID_FORMAT;

    // One-time shared state: the compiled book-ID pattern used by every receipt.
    static {
        BOOK_ID_FORMAT = Pattern.compile("BK-\\d{3}");
    }

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("memberId is missing or blank");
        }
        if (bookIds == null || bookIds.length == 0) {
            throw new IllegalArgumentException("a receipt needs at least one book ID");
        }
        // Copy first, then validate the copy, so the caller can't swap values in afterwards.
        String[] copy = bookIds.clone();
        for (String id : copy) {
            if (id == null || !BOOK_ID_FORMAT.matcher(id).matches()) {
                throw new IllegalArgumentException("invalid book ID: " + id);
            }
        }
        this.memberId = memberId;
        this.bookIds = copy;
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        return new LoanReceipt(memberId, replaced(index, newId));
    }

    String[] replaced(int index, String newId) {
        if (index < 0 || index >= bookIds.length) {
            throw new IndexOutOfBoundsException("no book at index " + index);
        }
        String[] updated = bookIds.clone();
        updated[index] = newId;
        return updated;
    }

    @Override
    public String toString() {
        return "LoanReceipt{memberId=" + memberId + ", bookIds=" + Arrays.toString(bookIds) + "}";
    }

    public static void main(String[] args) {
        try {
            new LoanReceipt("LIB-8841", new String[]{"BK-100", "bad"});
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected: " + e.getMessage());
        }

        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println(r.getBookIds()[0]);

        LoanReceipt corrected = r.withCorrectedBookId(1, "BK-150");
        System.out.println("original:  " + r);
        System.out.println("corrected: " + corrected);
    }
}
