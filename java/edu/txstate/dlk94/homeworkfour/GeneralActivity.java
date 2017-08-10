package edu.txstate.dlk94.homeworkfour;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class GeneralActivity extends AppCompatActivity {

    int numberRentalDays;
    int carId;
    String brand;
    String model;
    String color;
    double cost;
    double totalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Your Selection");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        //Retrieve the car info selected by the user in the Cars Activity
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(GeneralActivity.this);
        carId = pref.getInt(MainActivity.KEY_CAR_ID, 0);
        brand = pref.getString(MainActivity.KEY_CAR_BRAND, null);
        model = pref.getString(MainActivity.KEY_CAR_NAME, null);
        color = pref.getString(MainActivity.KEY_CAR_COLOR, null);
        cost = pref.getFloat(MainActivity.KEY_CAR_COST, 0);
        DecimalFormat currency = new DecimalFormat("$###,###.00");

        final TextView selection = (TextView) findViewById(R.id.txtSelection);
        selection.setText("The " + brand + " " + model + " for a cost of " + currency.format(cost) + " per day");



        final EditText numberOfDays = (EditText) findViewById(R.id.txtNumberOfDays);
        final Button costButton = (Button) findViewById(R.id.btnCalc);
        costButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    numberRentalDays = Integer.parseInt(numberOfDays.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(GeneralActivity.this, "Please enter a value.", Toast.LENGTH_SHORT).show();
                }

                totalCost = numberRentalDays*cost;
                DecimalFormat currency = new DecimalFormat("$###,###.00");


                if (numberRentalDays > 14) {
                    Toast.makeText(GeneralActivity.this, "Please call 1-800-777-2222 to make a reservation longer than 14 days.", Toast.LENGTH_LONG).show();

                }else

                    Toast.makeText(GeneralActivity.this, "The " + brand + " " + model + " you selected will cost " + currency.format(totalCost)
                            + " based on the number of days you selected.", Toast.LENGTH_LONG).show();
            }
        });



    }

}
