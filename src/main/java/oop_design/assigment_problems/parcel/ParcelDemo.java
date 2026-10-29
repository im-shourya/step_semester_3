package oop_design.assigment_problems.parcel;

import java.util.List;

public class ParcelDemo {

    public static void main(String[] args) {
        ParcelService service = new ParcelService();
        Customer customer = new Customer("Meera");

        Parcel p101 = service.book("P101", customer, new ExpressShipping(), 2,
                List.of(new SmsChannel(), new EmailChannel()));
        p101.advanceTo(ParcelStatus.PICKED_UP);
        p101.cancel();
        p101.advanceTo(ParcelStatus.IN_TRANSIT);
        p101.advanceTo(ParcelStatus.DELIVERED);

        System.out.printf("Fragile 3 kg charge: ₹%.2f%n", new FragileShipping().charge(3));
    }
}
