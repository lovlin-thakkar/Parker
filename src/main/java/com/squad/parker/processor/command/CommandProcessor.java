package com.squad.parker.processor.command;

import java.util.List;
import java.util.regex.Pattern;

import com.squad.parker.model.CarInfo;
import com.squad.parker.model.CommandType;
import com.squad.parker.model.ParkingLot;

public class CommandProcessor {

    private static ParkingLot parkingLot = new ParkingLot();

    public void processCommand(String command) {
        CommandType commandType = determineCommandType(command);
        String output;

        if (commandType == CommandType.CREATE_PARKING_LOT) {
            output = createLot(command);
        } else if (commandType == CommandType.PARK_CAR) {
            output = park(command);
        } else if (commandType == CommandType.LEAVE_CAR) {
            output = leave(command);
        } else if (commandType == CommandType.QUERY_CAR_NUMS_FOR_AGE) {
            output = queryCarNumsForAge(command);
        } else if (commandType == CommandType.QUERY_SLOTS_FOR_AGE) {
            output = querySlotsForAge(command);
        } else if (commandType == CommandType.QUERY_SLOTS_FOR_CAR_NUM) {
            output = querySlotsForCarNum(command);
        } else {
            output = "Unrecognized command, please try again.";
        }

        System.out.println(output);
    }

    private String querySlotsForCarNum(String command) {
        String carNumber = command.split(" ")[1];
        int slot = parkingLot.getSlotsForCarNum(carNumber);
        if (slot != -1) {
            return String.valueOf(slot);
        } else {
            return String.format("Car with registration number [%s] has not been parked at the Parking Lot.", carNumber);
        }
    }

    private String querySlotsForAge(String command) {
        int age = Integer.parseInt(command.split(" ")[1]);
        List<String> slots = parkingLot.getSlotsForAge(age);
        if (!slots.isEmpty()) {
            return String.join(",", slots);
        } else {
            return String.format("There are no Cars parked with drivers of age [%s].", age);
        }
    }

    private String queryCarNumsForAge(String command) {
        int age = Integer.parseInt(command.split(" ")[1]);
        List<String> carNumbers = parkingLot.getCarNumsForAge(age);
        if (!carNumbers.isEmpty()) {
            return String.join(",", carNumbers);
        } else {
            return String.format("There are no Cars parked with drivers of age [%s].", age);
        }
    }

    private String leave(String command) {
        int slotNumber = Integer.parseInt(command.split(" ")[1]);
        CarInfo leavingCar = parkingLot.leave(slotNumber);

        return String.format("Slot number %s vacated, " +
                            "the car with vehicle registration number '%s' left the space, " + 
                            "the driver of the car was of age %s", 
                            leavingCar.getSlotNumber(), 
                            leavingCar.getRegistrationNumber(), 
                            leavingCar.getDriverAge());
    }

    private String createLot(String command) {
        int size = Integer.parseInt(command.split(" ")[1]);
        parkingLot.createLot(size);

        return String.format("Created parking of %s slots", size);
    }

    private String park(String command) {
        int age = Integer.parseInt(command.split(" ")[3]);
        String carNumber = command.split(" ")[1];
        int slotNumber = parkingLot.park(new CarInfo(carNumber, -1, age));

        if (slotNumber == -1) {
            return "Sorry! Parking Space Full! Please try again after some time.";
        }

        return String.format("Car with vehicle registration number '%s' has been parked at slot number %s", carNumber, slotNumber);
    }

    public CommandType determineCommandType(String command) {
        for (CommandType commandType : CommandType.values()) {
            if (Pattern.matches(commandType.getCommandPattern(), command)) {
                return commandType;
            }
        }

        return CommandType.UNKNOWN;
    }
}