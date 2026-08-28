package oop.assigment_problems;

public class ParkingSlot {
    private String slotNo;
    private int capacity;
    private int occupiedCount;

    public ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    public void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            System.out.println(vehicleNo + " allotted to slot " + slotNo);
        }
    }

    public static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (ParkingSlot slot : slots) {
            if (slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }
        return null;
    }

    /*
     * Why passing the ParkingSlot array into these methods does not copy the slots themselves:
     * In Java, arrays are objects. When an array is passed to a method, the reference to the array is passed by value.
     * This means the method receives a copy of the reference pointing to the same array object in memory.
     * The objects inside the array are not copied; they are the exact same objects that the caller has.
     * Modifications to the objects inside the array will be reflected outside the method as well.
     */
    public static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
        ParkingSlot availableSlot = findAvailableSlot(slots);
        if (availableSlot != null) {
            availableSlot.allot(vehicleNo);
        } else {
            System.out.println("No slots available for " + vehicleNo);
        }
    }

    public String getSlotNo() {
        return slotNo;
    }

    public static void main(String[] args) {
        ParkingSlot[] slotsAvailable = {
                new ParkingSlot("A1", 4, 3),
                new ParkingSlot("A2", 5, 5)
        };
        safeAllot(slotsAvailable, "TN09AB1234");

        ParkingSlot[] slotsFull = {
                new ParkingSlot("A1", 4, 4),
                new ParkingSlot("A2", 5, 5)
        };
        safeAllot(slotsFull, "TN09AB1234");
    }
}
