package oop_design.assigment_problems.parcel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Customer 1 --- 0..* Parcel, Parcel * --- 1 ShippingType, Parcel 1 --- 0..* NotificationChannel.
 * ParcelService creates the Parcel, asks its ShippingType for the charge, subscribes the
 * channels, then marks it BOOKED. Every later status change goes through Parcel, which checks
 * the transition and pushes the update to each subscribed channel.
 */
public class ParcelService {

    private final Map<String, Parcel> parcels = new HashMap<>();

    public Parcel book(String id, Customer customer, ShippingType type, double weightKg,
                       List<NotificationChannel> channels) {
        Parcel parcel = new Parcel(id, customer, type, weightKg);
        parcels.put(id, parcel);
        System.out.printf("Parcel %s booked (%s, %s kg). Charge: ₹%.2f.%n",
                id, type.getName(), formatWeight(weightKg), parcel.getCharge());
        channels.forEach(parcel::subscribe);
        parcel.markBooked();
        return parcel;
    }

    public Parcel find(String id) {
        return parcels.get(id);
    }

    private static String formatWeight(double kg) {
        return kg == Math.floor(kg) ? String.valueOf((long) kg) : String.valueOf(kg);
    }
}
