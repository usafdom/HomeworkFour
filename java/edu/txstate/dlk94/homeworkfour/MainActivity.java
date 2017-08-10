package edu.txstate.dlk94.homeworkfour;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    public static final String RENTAL_CAR_TABLE = "RentalCar";
    public static final String KEY_CAR_ID = "CarId";
    public static final String KEY_CAR_BRAND = "Brand";
    public static final String KEY_CAR_NAME = "CarName";
    public static final String KEY_CAR_COLOR = "Color";
    public static final String KEY_CAR_COST = "CostPerDay";

    List<RentalCar> array = new ArrayList<RentalCar>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        DatabaseHandler handler = new DatabaseHandler(this);
        array = handler.getAllRentalCars();
        setListAdapter(new ArrayAdapter<RentalCar>(MainActivity.this,
                R.layout.activity_main, R.id.txtCar, array));
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {

        if (array.get(position).getCarId() == 101) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.chevrolet.com/volt-electric-car/"));
            startActivity(intent);
        } else if (array.get(position).getCarId() == 103) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.bmwusa.com/vehicles/bmwi.html/"));
            startActivity(intent);
        } else {

            int idSelectedCar = array.get(position).getCarId();
            String brandSelectedCar = array.get(position).getBrand();
            String nameSelectedCar = array.get(position).getCarName();
            String colorSelectedCar = array.get(position).getColor();
            double costSelectedCar = array.get(position).getCostPerDay();

            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt(KEY_CAR_ID, idSelectedCar);
            editor.putString(KEY_CAR_BRAND, brandSelectedCar);
            editor.putString(KEY_CAR_NAME, nameSelectedCar);
            editor.putString(KEY_CAR_COLOR, colorSelectedCar);
            editor.putFloat(KEY_CAR_COST, (float) costSelectedCar);

            editor.commit();

            Intent intent = new Intent(MainActivity.this, GeneralActivity.class);
            startActivity(intent);

        }

    }
}
