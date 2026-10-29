package oop_design.assigment_problems.parcel;

import java.util.ArrayList;
import java.util.List;

public class Parcel {

    private final String id;
    private final Customer customer;
    private final ShippingType shippingType;
    private final double weightKg;
    private final List<NotificationChannel> channels = new ArrayList<>();
    private ParcelStatus status;

    Parcel(String id, Customer customer, ShippingType shippingType, double weightKg) {
        this.id = id;
        this.customer = customer;
        this.shippingType = shippingType;
        this.weightKg = weightKg;
    }

    public String getId() {
        return id;
    }

    public ParcelStatus getStatus() {
        return status;
    }

    public double getCharge() {
        return shippingType.charge(weightKg);
    }

    public String getShippingName() {
        return shippingType.getName();
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void subscribe(NotificationChannel channel) {
        channels.add(channel);
    }

    void markBooked() {
        changeStatus(ParcelStatus.BOOKED);
    }

    public boolean advanceTo(ParcelStatus target) {
        if (!status.canMoveTo(target)) {
            System.out.println("Invalid transition: " + status + " → " + target + " is not allowed.");
            return false;
        }
        changeStatus(target);
        return true;
    }

    public boolean cancel() {
        if (status != ParcelStatus.BOOKED) {
            System.out.println("Cancellation failed: " + id + " can be cancelled only while BOOKED.");
            return false;
        }
        changeStatus(ParcelStatus.CANCELLED);
        return true;
    }

    private void changeStatus(ParcelStatus newStatus) {
        status = newStatus;
        for (NotificationChannel channel : channels) {
            channel.notify(id + " is now " + status + ".");
        }
    }
}
