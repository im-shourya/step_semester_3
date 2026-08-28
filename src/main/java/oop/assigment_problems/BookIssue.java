package oop.assigment_problems;

public class BookIssue {
    private String title;
    private String borrowerName;
    private int daysOverdue;

    public BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    public double fineAmount() {
        if (daysOverdue > 0) {
            return daysOverdue * 5.0;
        }
        return 0.0;
    }

    public boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    /*
     * Why totalFineCollected is static while fineAmount is not:
     * fineAmount() calculates the fine for a specific book (instance), so it requires instance data (daysOverdue).
     * totalFineCollected() calculates the aggregate fine across multiple BookIssue objects.
     * It does not belong to any single book, but rather operates on a collection of them.
     * Thus, it is a utility method that belongs to the class itself.
     */
    public static double totalFineCollected(BookIssue[] issues) {
        double total = 0;
        for (BookIssue issue : issues) {
            total += issue.fineAmount();
        }
        return total;
    }

    public String getTitle() {
        return title;
    }

    public int getDaysOverdue() {
        return daysOverdue;
    }

    public static void main(String[] args) {
        BookIssue[] books = {
                new BookIssue("Clean Code", "Alice", 18),
                new BookIssue("Effective Java", "Bob", 5),
                new BookIssue("Refactoring", "Charlie", 0),
                new BookIssue("DSA Handbook", "Dave", 21),
                new BookIssue("Design Patterns", "Eve", 9)
        };

        for (BookIssue book : books) {
            String status = book.isSeverelyOverdue() ? "Severely overdue" : "OK";
            System.out.printf("%s - %d days - %s\n", book.getTitle(), book.getDaysOverdue(), status);
        }

        double totalFine = BookIssue.totalFineCollected(books);
        System.out.printf("Total fine collected: Rs %.1f\n", totalFine);
    }
}
