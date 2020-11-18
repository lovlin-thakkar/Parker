package com.squad.parker.processor.command;

import java.util.List;
import java.util.regex.Pattern;

import com.squad.parker.constants.Constants;
import com.squad.parker.model.CarInfo;
import com.squad.parker.model.CommandType;
import com.squad.parker.model.ParkingLot;
import com.squad.parker.processor.Processor;

public class CommandProcessor implements Processor<String, String> {

    private static ParkingLot parkingLot = ParkingLot.getInstance();

    public String process(String command) {
        CommandType commandType = determineCommandType(command);
        String output;

        if (commandType == CommandType.CREATE_PARKING_LOT) {
            output = createLot(command);
        } else {
            if (!parkingLot.isLotInitialized()) {
                output = Constants.PARKING_LOT_NOT_INITIALIZED;
            } else {
                if (commandType == CommandType.PARK_CAR) {
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
                    output = Constants.UNRECOGNIZED_COMMAND_OUTPUT;
                }
            }
        }

        return output;
    }

    private String querySlotsForCarNum(String command) {
        String carNumber = command.split(" ")[1];
        int slot = parkingLot.getSlotsForCarNum(carNumber);
        if (slot != -1) {
            return String.valueOf(slot);
        } else {
            return String.format(Constants.QUERY_SLOTS_FOR_CAR_NUM_OUTPUT, carNumber);
        }
    }

    private String querySlotsForAge(String command) {
        int age = Integer.parseInt(command.split(" ")[1]);
        List<String> slots = parkingLot.getSlotsForAge(age);
        if (!slots.isEmpty()) {
            return String.join(", ", slots);
        } else {
            return String.format(Constants.NO_CARS_WITH_DRIVERS_AGE_OUTPUT, age);
        }
    }

    private String queryCarNumsForAge(String command) {
        int age = Integer.parseInt(command.split(" ")[1]);
        List<String> carNumbers = parkingLot.getCarNumsForAge(age);
        if (!carNumbers.isEmpty()) {
            return String.join(", ", carNumbers);
        } else {
            return String.format(Constants.NO_CARS_WITH_DRIVERS_AGE_OUTPUT, age);
        }
    }

    private String leave(String command) {
        int slotNumber = Integer.parseInt(command.split(" ")[1]);
        CarInfo leavingCar = parkingLot.leave(slotNumber);

        if (leavingCar == null) {
            return String.format(Constants.LEAVE_CAR_FAILURE_OUTPUT, slotNumber);
        }

        return String.format(Constants.LEAVE_COMMAND_OUTPUT, 
                            leavingCar.getSlotNumber(), 
                            leavingCar.getRegistrationNumber(), 
                            leavingCar.getDriverAge());
    }

    private String createLot(String command) {
        int size = Integer.parseInt(command.split(" ")[1]);

        if (size <= 0) {
            return Constants.LOT_SIZE_SHOULD_BE_NON_ZERO_POSITIVE;
        }

        parkingLot.createLot(size);

        return String.format(Constants.CREATE_PARKING_COMMAND_OUTPUT, size);
    }

    private String park(String command) {
        int age = Integer.parseInt(command.split(" ")[3]);
        String carNumber = command.split(" ")[1];
        int slotNumber = parkingLot.park(new CarInfo(carNumber, -1, age));

        if (slotNumber == -1) {
            return Constants.PARKING_SPACE_FULL_ERROR_MESSAGE;
        }

        return String.format(Constants.PARK_SUCCESS_OUTPUT, carNumber, slotNumber);
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