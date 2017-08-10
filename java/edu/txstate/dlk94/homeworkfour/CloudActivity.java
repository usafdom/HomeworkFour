package edu.txstate.dlk94.homeworkfour;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class CloudActivity extends ListActivity {
    ArrayList<RentalCar> array = new ArrayList<RentalCar>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_cloud);
        getCarBrand();
    }

    void getCarBrand(){
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));
        RestClient.get(CloudActivity.this,"api/rentalcar", headers.toArray(new Header[headers.size()]),
                null,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                        for (int i = 0; i < response.length(); i++){
                            try {
                                array.add(new RentalCar(response.getJSONObject(i)));
                            } catch (Exception ex){}

                        }

                        setListAdapter(new ArrayAdapter<RentalCar>(CloudActivity.this,
                                android.R.layout.simple_list_item_1, array));

                    }
                });

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(CloudActivity.this, "" + array.get(position).getBrand() + " "
                + array.get(position).getCarName(), Toast.LENGTH_LONG).show();
        DatabaseHandler handler = new DatabaseHandler(CloudActivity.this);
        handler.addRentalCar(array.get(position));
        handler.close();
    }



}
