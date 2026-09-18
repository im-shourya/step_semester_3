package encapsulation.assigment_problems.bean;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * Problem 4: JavaBean version of LibraryMember. Kept in its own package because Problem 1's
 * LibraryMember must have no no-arg constructor, while a JavaBean must have one.
 */
public class LibraryMember {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswerHash;

    public LibraryMember() {
        this((String) null);
    }

    public LibraryMember(String name) {
        this(null, name);
    }

    // The one real initialization path every constructor ends up in.
    public LibraryMember(String membershipId, String name) {
        this.name = name;
        setMembershipId(membershipId);
    }

    public String getMembershipId() {
        return membershipId;
    }

    // Write-once: only the first non-blank id ever takes effect; later calls are ignored.
    public void setMembershipId(String id) {
        if (this.membershipId == null && id != null && !id.trim().isEmpty()) {
            this.membershipId = id.trim();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    // Write-only: only a one-way SHA-256 hash is stored, and there is no getter for it.
    public void setSecurityAnswer(String answer) {
        if (answer != null) {
            this.securityAnswerHash = hash(answer);
        }
    }

    // Lets the desk check an answer without ever exposing the stored value.
    public boolean verifySecurityAnswer(String attempt) {
        return securityAnswerHash != null && attempt != null
                && MessageDigest.isEqual(securityAnswerHash.getBytes(StandardCharsets.UTF_8),
                hash(attempt).getBytes(StandardCharsets.UTF_8));
    }

    private static String hash(String value) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA-256").digest(value.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (byte b : digest) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
    }

    @Override
    public String toString() {
        return "LibraryMember{membershipId=" + membershipId + ", name=" + name
                + ", premiumMember=" + premiumMember + "}";
    }

    public static void main(String[] args) {
        System.out.println(new LibraryMember("Priya Nair").getMembershipId());
        System.out.println(new LibraryMember("LIB-8841", "Priya Nair").getMembershipId());

        LibraryMember m = new LibraryMember();
        m.setMembershipId("LIB-8841");
        m.setMembershipId("FAKE-0000");
        System.out.println(m.getMembershipId());

        m.setName("Priya Nair");
        m.setPremiumMember(true);
        m.setSecurityAnswer("Mango");
        System.out.println(m);
        System.out.println("Correct answer verifies: " + m.verifySecurityAnswer("Mango"));
        System.out.println("Wrong answer verifies: " + m.verifySecurityAnswer("Apple"));
    }
}
