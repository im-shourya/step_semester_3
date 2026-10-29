package oop_design.assigment_problems.parcel;

public class EmailChannel implements NotificationChannel {

    @Override
    public void notify(String message) {
        System.out.println("[Email] " + message);
    }
}
