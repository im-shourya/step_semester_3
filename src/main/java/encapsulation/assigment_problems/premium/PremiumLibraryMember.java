package encapsulation.assigment_problems.premium;

import encapsulation.assigment_problems.AccessChecker;
import encapsulation.assigment_problems.LibraryMember;

/*
 * Problem 2 in real code: a subclass of LibraryMember living in a different package.
 * finesOwed is protected, so this class may use it, but only through a reference
 * whose compile-time type is PremiumLibraryMember (its own type).
 */
public class PremiumLibraryMember extends LibraryMember {

    public PremiumLibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
        super(membershipId, branchCode, finesOwed, displayName);
    }

    public void waiveHalfFine() {
        this.finesOwed = finesOwed / 2;   // own type through 'this' -> ALLOWED
    }

    public void transferFineFrom(PremiumLibraryMember other) {
        this.finesOwed += other.finesOwed; // own type -> ALLOWED
        other.finesOwed = 0;
    }

    public void tryReadParentFine(LibraryMember parentTypeRef) {
        // parentTypeRef.finesOwed -> compile error: declared type is LibraryMember (PARENT_TYPE),
        // even if the object at runtime is actually a PremiumLibraryMember.
        System.out.println("Through parent-type reference, only public API works: "
                + parentTypeRef.getFinesOwed());
    }

    public static void main(String[] args) {
        PremiumLibraryMember a = new PremiumLibraryMember("PRM-01", "BR1", 80, "Priya Nair");
        PremiumLibraryMember b = new PremiumLibraryMember("PRM-02", "BR1", 20, "Arjun Rao");

        a.waiveHalfFine();
        a.transferFineFrom(b);
        System.out.println(a.getDisplayName() + " owes " + a.getFinesOwed());

        LibraryMember parentRef = b;
        a.tryReadParentFine(parentRef);

        System.out.println(AccessChecker.describeContext("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE") + ": "
                + AccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println(AccessChecker.describeContext("SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE") + ": "
                + AccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
    }
}
