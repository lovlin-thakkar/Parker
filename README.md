# Parker
A Command Line Interface for making note of car and driver information for a Parking Lot.

## Valid Commands:

| Command  | Description |
| ------------- | ------------- |
| `Create_parking_lot 6` | Creates a parking lot of 6 slots  |
| `Park KA-01-HH-1234 driver_age 21`  | Parks car with vehicle registration number "KA-01-HH-1234", and the vehicle is driven by the driver of age 21  |
| `Slot_numbers_for_driver_of_age 21` | Views all slot numbers of all cars which have drivers with age equal to 21  |
| `Slot_number_for_car_with_number PB-01-HH-1234` | Views slot number for the car with registration number "PB-01-HH-1234"  |
| `Leave 2` | Vacates the slot number 2 from the parking lot, i.e. car which was parked at slot number 2 has left the space if there's no car at slot number 2, prints "Slot already vacant"  |
| `Vehicle_registration_number_for_driver_of_age 18` | Views all parked vehicle registration number of cars parked by the driver of age 18  |


## How to Run:
1. Download the Repository as ZIP from GitHub. 
2. Extract the ZIP file. (alternatively downloading `package/` and `runApp.sh` works too)
3. Open a Terminal / Command Prompt in the extracted directory's location (OR navigate into the extracted directory using `cd` command).
4. Run command: `./runApp.sh src/resources/input.txt`

To try with a separate file, 
- Either edit `src/resources/input.txt` OR 
- Create a new file and run the command with path of the new file. (i.e. `./runApp.sh file_path`)

#### Troubleshooting:
If `Permission denied` on running the shell script -> Run command: `chmod 555 runApp.sh` and try again.

## Notable Features:
- Opens this README page in default browser in case of incorrect way of executing the shell script. 
<br/>(e.g., no input file path passed as argument, or file not found)
- Thread-safe way of handling each command using Read Write Lock & Atomic Integer.
