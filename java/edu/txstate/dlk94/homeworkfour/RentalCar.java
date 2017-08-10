package edu.txstate.dlk94.homeworkfour;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by usafd_000 on 8/9/2017.
 */

public class RentalCar {

    String brand;
    int carId;
    String carName;
    String color;
    double costPerDay;

    @Override
    public String toString() {
        return this.getCarName();
    }

    public RentalCar(){

    }

    public RentalCar(String brand, int carId, String carName, String color, double costPerDay) {
        this.brand = brand;
        this.carId = carId;
        this.carName = carName;
        this.color = color;
        this.costPerDay = costPerDay;
    }

    public RentalCar(JSONObject object) {
        try {
            this.brand = object.getString("Brand");
            this.carId = object.getInt("CarId");
            this.carName = object.getString("CarName");
            this.color = object.getString("Color");
            this.costPerDay = object.getDouble("CostPerDay");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
    }


}
