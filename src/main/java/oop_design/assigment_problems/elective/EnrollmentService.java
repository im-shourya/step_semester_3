package oop_design.assigment_problems.elective;

import java.util.HashMap;
import java.util.Map;

/*
 * Student * --- * Elective (an elective holds at most 'capacity' students), Student * --- 1 CreditPolicy.
 * The service only looks up electives and delegates; the Elective guards its own seats and
 * waitlist, and the CreditPolicy answers the limit, so a new student type is a new policy class.
 */
public class EnrollmentService {

    private final Map<String, Elective> electives = new HashMap<>();

    public Elective createElective(String name, int credits, int capacity) {
        Elective elective = new Elective(name, credits, capacity);
        electives.put(name, elective);
        return elective;
    }

    public boolean enroll(Student student, String electiveName) {
        return electives.get(electiveName).enroll(student);
    }

    public boolean drop(Student student, String electiveName) {
        return electives.get(electiveName).drop(student);
    }
}
