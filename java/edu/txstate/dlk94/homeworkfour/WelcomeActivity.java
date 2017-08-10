package edu.txstate.dlk94.homeworkfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Welcome!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Button cloud = (Button) findViewById(R.id.btnCloud);
        cloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, CloudActivity.class));
            }
        });

        Button sqlite = (Button) findViewById(R.id.btnSQLite);
        sqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        });

        Button delete = (Button) findViewById(R.id.btnDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler handler = new DatabaseHandler(WelcomeActivity.this);
                handler.deleteAllRentalCars();
                handler.close();

                Toast.makeText(WelcomeActivity.this, "Car database successfully " +
                        "deleted", Toast.LENGTH_LONG).show();
            }
        });
    }
}
