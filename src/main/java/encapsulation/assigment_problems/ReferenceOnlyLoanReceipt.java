package encapsulation.assigment_problems;

import java.util.Arrays;

// A reference-only book never leaves the library: it is read inside a specific room.
public final class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        if (roomNumber == null || roomNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("roomNumber is missing or blank");
        }
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    // Keeps the receipt reference-only (and its room) when a book ID is corrected.
    @Override
    public ReferenceOnlyLoanReceipt withCorrectedBookId(int index, String newId) {
        return new ReferenceOnlyLoanReceipt(getMemberId(), replaced(index, newId), roomNumber);
    }

    @Override
    public String toString() {
        return "ReferenceOnlyLoanReceipt{memberId=" + getMemberId()
                + ", bookIds=" + Arrays.toString(getBookIds()) + ", room=" + roomNumber + "}";
    }
}
