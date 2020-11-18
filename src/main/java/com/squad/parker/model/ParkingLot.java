package com.squad.parker.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ParkingLot {

    private TreeSet<Integer> freeSlots;
    private AtomicInteger availability;
    private Map<Integer, CarInfo> slotVsCarInfoMap;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static volatile ParkingLot instance;

    private ParkingLot() {}

    public static ParkingLot getInstance() {
        if (instance == null) {
            synchronized (ParkingLot.class) {
                if (instance == null) {
                    instance = new ParkingLot();
                }
            }
        }

        return instance;
    }

    public boolean isLotInitialized() {
        return freeSlots != null && !freeSlots.isEmpty();
    }

    public void createLot(int size) {
        readWriteLock.writeLock().lock();

        try {
            availability = new AtomicInteger(size);
            freeSlots = new TreeSet<>();
            slotVsCarInfoMap = new HashMap<>();

            for (int i = 1; i <= size; i++) {
                freeSlots.add(i);
            }

        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    /**
     * Unpark the car at given slot number.
     * @param slotNumber to unpark from.
     * @return CarInfo for the unparked car, null if there was no car at given slot number.
     */
    public CarInfo leave(int slotNumber) {
        readWriteLock.writeLock().lock();

        CarInfo leftCarInfo = null;

        try {
            if (slotVsCarInfoMap.containsKey(slotNumber)) {
                freeSlots.add(slotNumber);
                availability.incrementAndGet();
                leftCarInfo = slotVsCarInfoMap.remove(slotNumber);
            }
        } finally {
            readWriteLock.writeLock().unlock();
        }

        return leftCarInfo;
    }

    /**
     * @return returns first free slot from the parking lot.
     */
    private int getFreeSlot() {
        readWriteLock.readLock().lock();
        
        int freeSlot = -1;

        try {
            if (!freeSlots.isEmpty()) {
                freeSlot = freeSlots.first();
            }
        } finally {
            readWriteLock.readLock().unlock();
        }

        return freeSlot;
    }
    
    /**
     * Parks the Car with given CarInfo.
     * @return slot number assigned to the parked car. Returns -1 if unable to park the car.
     */
    public int park(CarInfo carInfo) {
        if (availability.get() <= 0) {
            return -1;
        }

        readWriteLock.writeLock().lock();
        
        int slotNumber;

        try {
            slotNumber = getFreeSlot();
            if (slotNumber == -1) {
                return -1;
            }

            freeSlots.remove(slotNumber);
            availability.decrementAndGet();

            carInfo.setSlotNumber(slotNumber);
            slotVsCarInfoMap.put(slotNumber, carInfo);

        } finally {
            readWriteLock.writeLock().unlock();
        }

        return slotNumber;
    }

    /**
     * @return returns slot number for car with given registration number, -1 if not found.
     */
    public int getSlotsForCarNum(String registrationNumber) {
        readWriteLock.readLock().lock();

        try {
            Iterator<Map.Entry<Integer, CarInfo>> iterator = slotVsCarInfoMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, CarInfo> entry = iterator.next();
                if (entry.getValue().getRegistrationNumber().equals(registrationNumber)) {
                    return entry.getKey();
                }
            }

        } finally {
            readWriteLock.readLock().unlock();
        }

        return -1;
    }

    /**
     * @return returns list of slot numbers for a given driver age, empty list if no slots found.
     */
    public List<String> getSlotsForAge(int age) {
        readWriteLock.readLock().lock();
        
        List<String> slots = new ArrayList<>();

        try {
            Iterator<Map.Entry<Integer, CarInfo>> iterator = slotVsCarInfoMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, CarInfo> entry = iterator.next();
                if (entry.getValue().getDriverAge() == age) {
                    slots.add(String.valueOf(entry.getKey()));
                }
            }

        } finally {
            readWriteLock.readLock().unlock();
        }

        return slots;
    }

    /**
     * @return returns list of registration numbers for a given driver age, returns empty list if no cars found.
     */
    public List<String> getCarNumsForAge(int age) {
        readWriteLock.readLock().lock();

        List<String> carNumbers = new ArrayList<>();

        try {
            Iterator<Map.Entry<Integer, CarInfo>> iterator = slotVsCarInfoMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, CarInfo> entry = iterator.next();
                if (entry.getValue().getDriverAge() == age) {
                    carNumbers.add(entry.getValue().getRegistrationNumber());
                }
            }
            
        } finally {
            readWriteLock.readLock().unlock();
        }

        return carNumbers;
    }
    
}