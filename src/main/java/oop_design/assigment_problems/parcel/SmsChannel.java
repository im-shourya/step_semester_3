package oop_design.assigment_problems.parcel;

public class SmsChannel implements NotificationChannel {

    @Override
    public void notify(String message) {
        System.out.println("[SMS] " + message);
    }
}
