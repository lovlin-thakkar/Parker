package com.squad.parker.model;

import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CarInfo {
    // largest parking lot capacity 20K, reference: https://www.guinnessworldrecords.com/world-records/largest-car-park
    private int slotNumber;

    private String registrationNumber;
    private int driverAge;

    public CarInfo(String registrationNumber, int slotNumber, int driverAge) {
        this.registrationNumber = registrationNumber;
        this.slotNumber = slotNumber;
        this.driverAge = driverAge;
    }

    public int getSlotNumber() {
        return this.slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getDriverAge() {
        return this.driverAge;
    }

    public void setDriverAge(int age) {
        this.driverAge = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof CarInfo)) {
            return false;
        }

        CarInfo carInfo = (CarInfo) o;
        
        return slotNumber == carInfo.slotNumber && Objects.equals(registrationNumber, carInfo.registrationNumber) && driverAge == carInfo.driverAge;
    }

    @Override
    public int hashCode() {
        return Objects.hash(slotNumber, registrationNumber, driverAge);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                    .append("slotNumber", slotNumber)
                    .append("registrationNumber", registrationNumber)
                    .append("driverAge", driverAge)
                    .toString();
    }
    
}