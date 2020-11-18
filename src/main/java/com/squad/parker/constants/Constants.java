package com.squad.parker.constants;

public class Constants {

    private Constants() {}

    public static final String FILE_NOT_FOUND_ERROR                 = "Unable to find file with path [%s]!";
    public static final String README_WEBPAGE_URL                   = "https://github.com/lovlin-thakkar/Parker/blob/main/README.md#parker";
    public static final String UNRECOGNIZED_COMMAND_OUTPUT          = "Unrecognized command, please try again.";
    public static final String QUERY_SLOTS_FOR_CAR_NUM_OUTPUT       = "Car with registration number [%s] has not been parked at the Parking Lot.";
    public static final String NO_CARS_WITH_DRIVERS_AGE_OUTPUT      = "There are no Cars parked with drivers of age [%s].";
    public static final String LEAVE_COMMAND_OUTPUT                 = "Slot number %s vacated, the car with vehicle registration number '%s' left the space, " + 
                                                                    "the driver of the car was of age %s";
    public static final String CREATE_PARKING_COMMAND_OUTPUT        = "Created parking of %s slots";    
    public static final String PARKING_SPACE_FULL_ERROR_MESSAGE     = "Sorry! Parking Space Full! Please try again after some time.";
    public static final String PARK_SUCCESS_OUTPUT                  = "Car with vehicle registration number '%s' has been parked at slot number %s";
    public static final String LEAVE_CAR_FAILURE_OUTPUT             = "Unable to unpark, no car is parked at slot [%s].";
    public static final String PARKING_LOT_NOT_INITIALIZED          = "Please run Create Parking Lot command before running other commands.";
    public static final String LOT_SIZE_SHOULD_BE_NON_ZERO_POSITIVE = "Parking Lot size should be greater than 0.";

}