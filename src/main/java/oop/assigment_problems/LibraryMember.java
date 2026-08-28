package oop.assigment_problems;

class BrokenLibraryMember {
    static String name;
    static String memberId;
    static int booksIssued;

    /*
     * Why static is wrong for name, memberId, and booksIssued:
     * - name: A name belongs to a specific person. If static, all members share the same name.
     * - memberId: Each member needs a unique ID. If static, there is only one ID shared across all members.
     * - booksIssued: Each member borrows a different number of books. If static, one global counter is updated.
     * Static fields belong to the class, not individual objects.
     */
    public BrokenLibraryMember(String memberName, String id, int books) {
        name = memberName;
        memberId = id;
        booksIssued = books;
    }

    public void printMemberCard() {
        System.out.println(name);
    }
}

public class LibraryMember {
    private String name;
    private String memberId;
    private int booksIssued;
    
    private static String libraryName = "Central Library";
    private static int memberCount = 0;

    public LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
        memberCount++;
        this.memberId = "LM-" + (1000 + memberCount);
    }

    public void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    public static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }

    public static void main(String[] args) {
        System.out.println("Broken version:");
        BrokenLibraryMember b1 = new BrokenLibraryMember("Aditi", "LM-1001", 2);
        BrokenLibraryMember b2 = new BrokenLibraryMember("Rohan", "LM-1002", 3);
        b1.printMemberCard();
        b2.printMemberCard();
        System.out.println("(Aditi's data was overwritten — both members now show \"Rohan\")\n");

        System.out.println("Fixed version:");
        LibraryMember m1 = new LibraryMember("Aditi", 2);
        LibraryMember m2 = new LibraryMember("Rohan", 3);
        m1.printMemberCard();
        m2.printMemberCard();
        LibraryMember.printTotalMembers();
    }
}
