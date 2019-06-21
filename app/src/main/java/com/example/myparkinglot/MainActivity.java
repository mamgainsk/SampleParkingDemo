package com.example.myparkinglot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public EditText vehicle_number_field, vehicle_lot_number_field;
    private ParkingLot parkingLot;
    public MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vehicle_number_field = findViewById(R.id.vehicle_number_field);
        vehicle_lot_number_field = findViewById(R.id.lot_number_field);
        database = MyDatabase.getInstance(getApplicationContext());
        parkingLot = new ParkingLot();
    }

    public void ParkVehicleNow(View view) {

        String vehicle_id_field_txt = vehicle_number_field.getText().toString().trim();
        String vehicle_lot_number_field_txt = vehicle_lot_number_field.getText().toString().trim();

        if (TextUtils.isEmpty(vehicle_id_field_txt)) {
            vehicle_number_field.setError("Please enter vehicle number");
        } else if (TextUtils.isEmpty(vehicle_lot_number_field_txt)) {
            vehicle_lot_number_field.setError("Please enter lot number");
        } else {
            park(Integer.parseInt(vehicle_id_field_txt),
                    Integer.parseInt(vehicle_lot_number_field_txt));
        }

    }

    private void park(int vehicle_id_field_txt, int vehicle_lot_number_field_txt) {
        String result = parkingLot.parkVehicle(database, vehicle_id_field_txt,
                vehicle_lot_number_field_txt);
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}