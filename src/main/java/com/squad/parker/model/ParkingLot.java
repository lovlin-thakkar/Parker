package com.squad.parker.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class ParkingLot {

    private TreeSet<Integer> freeSlots;
    private int availability;
    private Map<Integer, CarInfo> slotVsCarInfoMap;

    public ParkingLot() {
        freeSlots = new TreeSet<>();
        slotVsCarInfoMap = new HashMap<>();
    }

    public void createLot(int size) {
        availability = size;
        freeSlots = new TreeSet<>();
        for (int i = 1; i <= size; i++) {
            freeSlots.add(i);
        }
    }

    public CarInfo leave(int slotNumber) {
        freeSlots.add(slotNumber);
        availability++;
        return slotVsCarInfoMap.remove(slotNumber);
    }

    public int getFreeSlot() {
        return freeSlots.first();
    }
	
	public int park(CarInfo carInfo) {
        if (availability <= 0) {
            return -1;
        }

        int slotNumber = getFreeSlot();
        freeSlots.remove(slotNumber);
        availability--;

        carInfo.setSlotNumber(slotNumber);
        slotVsCarInfoMap.put(slotNumber, carInfo);

        return slotNumber;
    }

    public int getSlotsForCarNum(String registrationNumber) {
        Iterator<Map.Entry<Integer, CarInfo>> iterator = slotVsCarInfoMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, CarInfo> entry = iterator.next();
            if (entry.getValue().getRegistrationNumber().equals(registrationNumber)) {
                return entry.getKey();
            }
        }

        return -1;
    }

    public List<String> getSlotsForAge(int age) {
        List<String> slots = new ArrayList<>();

        Iterator<Map.Entry<Integer, CarInfo>> iterator = slotVsCarInfoMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, CarInfo> entry = iterator.next();
            if (entry.getValue().getDriverAge() == age) {
                slots.add(String.valueOf(entry.getKey()));
            }
        }

        return slots;
    }

    public List<String> getCarNumsForAge(int age) {
        List<String> carNumbers = new ArrayList<>();

        Iterator<Map.Entry<Integer, CarInfo>> iterator = slotVsCarInfoMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, CarInfo> entry = iterator.next();
            if (entry.getValue().getDriverAge() == age) {
                carNumbers.add(entry.getValue().getRegistrationNumber());
            }
        }

        return carNumbers;
    }

    
    
}