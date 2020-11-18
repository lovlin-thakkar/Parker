package com.squad.parker.model;

public enum CommandType {
    CREATE_PARKING_LOT("^Create_parking_lot [0-9]+$"),
    PARK_CAR("^Park [A-Z]{2}-[0-9]{2}-[A-Z]{2}-[0-9]{4} driver_age [0-9]+$"),
    QUERY_SLOTS_FOR_AGE("^Slot_numbers_for_driver_of_age [0-9]+$"),
    QUERY_SLOTS_FOR_CAR_NUM("^Slot_number_for_car_with_number [A-Z]{2}-[0-9]{2}-[A-Z]{2}-[0-9]{4}$"),
    LEAVE_CAR("^Leave [0-9]+$"),
    QUERY_CAR_NUMS_FOR_AGE("^Vehicle_registration_number_for_driver_of_age [0-9]+$"),
    UNKNOWN("");

    String commandPattern;
    private CommandType(String commandPattern) {
        this.commandPattern = commandPattern;
    }

    public String getCommandPattern() {
        return commandPattern;
    }
}