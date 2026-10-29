package oop_design.assigment_problems.elective;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.Set;

/*
 * Invariants: enrolled.size() <= capacity; a student is never both enrolled and waitlisted;
 * the waitlist keeps arrival order. All changes go through enroll() and drop().
 */
public class Elective {

    private final String name;
    private final int credits;
    private final int capacity;
    private final Set<Student> enrolled = new LinkedHashSet<>();
    private final Queue<Student> waitlist = new ArrayDeque<>();

    public Elective(String name, int credits, int capacity) {
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public boolean enroll(Student student) {
        if (enrolled.contains(student) || waitlist.contains(student)) {
            System.out.println("Enrollment failed: " + student.getName() + " is already enrolled or waitlisted for " + name + ".");
            return false;
        }
        if (!student.canTake(credits)) {
            System.out.println("Enrollment failed: " + student.getName() + " would exceed the "
                    + student.getPolicy().getTypeName() + " credit limit ("
                    + (student.getCredits() + credits) + "/" + student.getPolicy().getCreditLimit() + ").");
            return false;
        }
        if (enrolled.size() >= capacity) {
            waitlist.add(student);
            System.out.println(name + " is full. " + student.getName() + " added to waitlist (position " + waitlist.size() + ").");
            return false;
        }
        admit(student);
        System.out.println(student.getName() + " enrolled in " + name + " (credits: " + student.creditSummary() + ").");
        return true;
    }

    // Drop and promote happen in one call so the seat is never left empty while someone eligible waits.
    public boolean drop(Student student) {
        if (!enrolled.remove(student)) {
            if (waitlist.remove(student)) {
                System.out.println(student.getName() + " left the waitlist for " + name + ".");
                return true;
            }
            System.out.println("Drop failed: " + student.getName() + " is not enrolled in " + name + ".");
            return false;
        }
        student.addCredits(-credits);
        System.out.println(student.getName() + " dropped " + name + " (credits: " + student.creditSummary() + ").");
        promoteNext();
        return true;
    }

    private void promoteNext() {
        Iterator<Student> it = waitlist.iterator();
        while (it.hasNext()) {
            Student next = it.next();
            if (next.canTake(credits)) {
                it.remove();
                admit(next);
                System.out.println(next.getName() + " promoted from waitlist and enrolled in " + name
                        + " (credits: " + next.creditSummary() + ").");
                return;
            }
        }
    }

    private void admit(Student student) {
        enrolled.add(student);
        student.addCredits(credits);
    }
}
