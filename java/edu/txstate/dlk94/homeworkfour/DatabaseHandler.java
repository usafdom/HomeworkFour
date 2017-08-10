package edu.txstate.dlk94.homeworkfour;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;
import static android.R.attr.version;

/**
 * Created by Dominic Kirsch on 8/9/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String RENTAL_CAR_TABLE = "RentalCar";
    private static final String KEY_CAR_ID = "CarId";
    private static final String KEY_CAR_BRAND = "CarBrand";
    private static final String KEY_CAR_NAME = "CarName";
    private static final String KEY_CAR_COLOR = "CarColor";
    private static final String KEY_CAR_COST = "CarCost";

    public DatabaseHandler(Context context) {
        super(context, "manager", null, DATABASE_VERSION);
    }

    @Override
    //creating tables
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RENTAL_CAR_TABLE = "CREATE TABLE " + RENTAL_CAR_TABLE + "("
                + KEY_CAR_ID + " INTEGER PRIMARY KEY,"
                + KEY_CAR_BRAND + " TEXT,"
                + KEY_CAR_NAME + " TEXT,"
                + KEY_CAR_COLOR + " TEXT,"
                + KEY_CAR_COST + " DOUBLE"
                + ")";
        db.execSQL(CREATE_RENTAL_CAR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + RENTAL_CAR_TABLE);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    void addRentalCar(RentalCar rentalCar) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(KEY_CAR_ID, rentalCar.getCarId());
        values.put(KEY_CAR_BRAND, rentalCar.getBrand());
        values.put(KEY_CAR_NAME, rentalCar.getCarName());
        values.put(KEY_CAR_COLOR, rentalCar.getColor());
        values.put(KEY_CAR_COST, rentalCar.getCostPerDay());

        // Inserting Row
        db.insert(RENTAL_CAR_TABLE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }


    // code to get the single contact
   /* RentalCar getRentalCar(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(RENTAL_CAR_TABLE, new String[] { KEY_CAR_ID,
                        KEY_CAR_NAME, KEY_CAR_BRAND, KEY_CAR_COLOR, KEY_CAR_COST }, KEY_CAR_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        RentalCar rentalCar = new RentalCar(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        // return contact
        return rentalCar;
    }*/

    // code to get all contacts in a list view
    public List<RentalCar> getAllRentalCars() {
        List<RentalCar> rentalCarList = new ArrayList<RentalCar>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + RENTAL_CAR_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RentalCar rentalCar = new RentalCar();
                int columnIndexId = cursor.getColumnIndex((KEY_CAR_ID));
                rentalCar.setCarId(cursor.getInt(columnIndexId));
                int columnIndexBrand = cursor.getColumnIndex(KEY_CAR_BRAND);
                rentalCar.setBrand(cursor.getString(columnIndexBrand));
                int columnIndexCarName = cursor.getColumnIndex(KEY_CAR_NAME);
                rentalCar.setCarName(cursor.getString(columnIndexCarName));
                int columnIndexCarColor = cursor.getColumnIndex(KEY_CAR_COLOR);
                rentalCar.setColor(cursor.getString(columnIndexCarColor));
                int columnIndexCarCost = cursor.getColumnIndex(KEY_CAR_COST);
                rentalCar.setCostPerDay(Double.parseDouble(cursor.getString(columnIndexCarCost)));
                // Adding contact to list
                rentalCarList.add(rentalCar);
            } while (cursor.moveToNext());
        }

        // return contact list
        return rentalCarList;
    }

/*    // code to update the single contact
    public int updateRentalCar(RentalCar rentalCar) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }*/

    public void deleteAllRentalCars() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(RENTAL_CAR_TABLE, "",new String[]{});
        db.close();
    }



}
