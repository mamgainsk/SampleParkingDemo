package com.example.myparkinglot;

public class ParkingLot {


    public void parkVehicle(Vehicle vehicle, int lot) {

    }

    /**
     * park vehicle
     * @param myDatabase
     * @param vehicleId
     * @param lot
     * @return
     */
    public String parkVehicle(MyDatabase myDatabase,int vehicleId, int lot) {

        if (isVehicleExists(myDatabase,vehicleId)) {
            return "Vehicle already present";
        } else if (isLotBooked(myDatabase,lot)) {
            return "Lot already taken";
        } else {
            Vehicle vehicle = new Vehicle();
            vehicle.setLot(lot);
            vehicle.setId(vehicleId);
            long id = myDatabase.ParkVehicle(vehicle);
            if (id > 0) {
                return "Vehicle Parked.";
            }
            return "Vehicle Already Parked choose a different lot or vehicle.";
        }

    }

    /**
     * check if lot is already booked
     * @param myDatabase
     * @param lot
     * @return
     */
    private boolean isLotBooked(MyDatabase myDatabase,int lot) {
        return myDatabase.isLotAlreadyTaken(lot) != null;
    }

    /**
     * check if vehicle already exist
     * @param myDatabase
     * @param vehicleId
     * @return
     */
    private boolean isVehicleExists(MyDatabase myDatabase,int vehicleId) {

        return myDatabase.getAllParkedVehicle(vehicleId) != null;

    }
}