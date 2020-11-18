package com.squad.parker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import com.squad.parker.model.CommandType;
import com.squad.parker.processor.command.CommandProcessor;

import org.junit.Test;

public class CommandProcessorTest {
    
    CommandProcessor commandProcessor = new CommandProcessor();

    @Test
    public void determineCommandTypeTest() {
        assertSame(CommandType.CREATE_PARKING_LOT, commandProcessor.determineCommandType("Create_parking_lot 6"));
        assertSame(CommandType.LEAVE_CAR, commandProcessor.determineCommandType("Leave 6"));
        assertSame(CommandType.PARK_CAR, commandProcessor.determineCommandType("Park KA-01-HH-1234 driver_age 21"));
        assertSame(CommandType.QUERY_SLOTS_FOR_AGE, commandProcessor.determineCommandType("Slot_numbers_for_driver_of_age 21"));
        assertSame(CommandType.QUERY_SLOTS_FOR_CAR_NUM, commandProcessor.determineCommandType("Slot_number_for_car_with_number PB-01-HH-1234"));
        assertSame(CommandType.QUERY_CAR_NUMS_FOR_AGE, commandProcessor.determineCommandType("Vehicle_registration_number_for_driver_of_age 18"));
        assertSame(CommandType.UNKNOWN, commandProcessor.determineCommandType(""));
        assertSame(CommandType.UNKNOWN, commandProcessor.determineCommandType("gibberish 7"));
    }

    @Test
    public void processCommandTest() {
        assertEquals("Created parking of 6 slots", commandProcessor.process("Create_parking_lot 6"));
        assertEquals("Car with vehicle registration number 'KA-01-HH-1234' has been parked at slot number 1", commandProcessor.process("Park KA-01-HH-1234 driver_age 21"));
        assertEquals("Car with vehicle registration number 'PB-01-HH-1234' has been parked at slot number 2", commandProcessor.process("Park PB-01-HH-1234 driver_age 21"));
        assertEquals("1, 2", commandProcessor.process("Slot_numbers_for_driver_of_age 21"));
        assertEquals("Car with vehicle registration number 'PB-01-TG-2341' has been parked at slot number 3", commandProcessor.process("Park PB-01-TG-2341 driver_age 40"));
        assertEquals("2", commandProcessor.process("Slot_number_for_car_with_number PB-01-HH-1234"));
        assertEquals("Slot number 2 vacated, the car with vehicle registration number 'PB-01-HH-1234' left the space, the driver of the car was of age 21", commandProcessor.process("Leave 2"));
        assertEquals("Car with vehicle registration number 'HR-29-TG-3098' has been parked at slot number 2", commandProcessor.process("Park HR-29-TG-3098 driver_age 39"));
        assertEquals("There are no Cars parked with drivers of age [18].", commandProcessor.process("Vehicle_registration_number_for_driver_of_age 18"));   
    }
    
}
