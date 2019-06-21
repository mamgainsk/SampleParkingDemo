package com.example.myparkinglot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {

    private static MyDatabase myDatabase;

    public static final String DATABASE_NAME = "parking_database.db";

    private static final int DATABASE_VERSION = 1;

    private static final String PARKING_TABLE = "parking_lots";

    // Column Id Primary Key
    private static final String COLUMN_ID = "id";

    // Table fields
    private static final String COLUMN_LOT_NUMBER = "lot_number";
    private static final String COLUMN_VEHICLE_NUMBER = "vehicle_number";

    // Create table query
    private static final String CREATE_PARKING_TABLE = "CREATE TABLE " + PARKING_TABLE + " (" + COLUMN_ID + " integer PRIMARY KEY AUTOINCREMENT, " + COLUMN_LOT_NUMBER + " INTEGER, " + COLUMN_VEHICLE_NUMBER + " INTEGER UNIQUE);";

    // Drop/delete table query
    private static final String DROP_PARKING_TABLE = "Drop table if exists "
            + PARKING_TABLE;

    public static MyDatabase getInstance(Context context) {
        if (myDatabase == null) {
            return myDatabase = new MyDatabase(context);
        }
        return myDatabase;
    }


    private MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PARKING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_PARKING_TABLE);
        onCreate(sqLiteDatabase);
    }

    /**
     * save vehicle and return row id
     * @param vehicle
     * @return
     */
    public long ParkVehicle(Vehicle vehicle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOT_NUMBER, vehicle.getLot());
        values.put(COLUMN_VEHICLE_NUMBER, vehicle.getId());
        return db.insert(PARKING_TABLE, null, values);
    }

    /**
     * get all parked vehicle
     * @return
     */
    public List<Vehicle> getAllParkedVehicle() {
        List<Vehicle> vehicleList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String select_query = "Select * from " + PARKING_TABLE;

        Cursor cursor = db.rawQuery(select_query, null);
        try {
            if (cursor.moveToFirst()) {

                do {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_VEHICLE_NUMBER)));
                    vehicle.setLot(cursor.getInt(cursor.getColumnIndex(COLUMN_LOT_NUMBER)));
                    vehicleList.add(vehicle);
                } while (cursor.moveToNext());

            }
        } finally {

            cursor.close();

        }
        return vehicleList;

    }

    /**
     * get parked vehicle from vehicleId
     * @param vehicleId
     * @return
     */
    public Vehicle getAllParkedVehicle(int vehicleId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String select_query = "Select * from " + PARKING_TABLE + " where " + COLUMN_VEHICLE_NUMBER + " = " + vehicleId;

        Cursor cursor = db.rawQuery(select_query, null);
        try {
            if (cursor.moveToFirst()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_VEHICLE_NUMBER)));
                vehicle.setLot(cursor.getInt(cursor.getColumnIndex(COLUMN_LOT_NUMBER)));
                return vehicle;
            }
        } finally {

            cursor.close();

        }
        return null;

    }

    /**
     * check if lot is already taken
     * @param lot
     * @return
     */
    public Vehicle isLotAlreadyTaken(int lot) {
        SQLiteDatabase db = this.getReadableDatabase();

        String select_query = "Select * from " + PARKING_TABLE + " where " + COLUMN_LOT_NUMBER + " = " + lot;

        Cursor cursor = db.rawQuery(select_query, null);
        try {
            if (cursor.moveToFirst()) {

                Vehicle vehicle = new Vehicle();
                vehicle.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_VEHICLE_NUMBER)));
                vehicle.setLot(cursor.getInt(cursor.getColumnIndex(COLUMN_LOT_NUMBER)));
                return vehicle;

            }
        } finally {

            cursor.close();

        }
        return null;

    }
}
