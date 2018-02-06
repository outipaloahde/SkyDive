package com.example.outipupu.skydive;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by outipupu on 23/11/2017.
 */

public class Edit extends AppCompatActivity {
    DatabaseHelper db;
    EditText etDate, etPlace, etAircraft, etGear, etExit, etDeploy, etDesc, etType, etId;

    Button btEdit;
    SQLiteDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        db = new DatabaseHelper(this);

        etId = (EditText)findViewById(R.id.etId);
        etDate = (EditText)findViewById(R.id.etDate);
        etPlace = (EditText)findViewById(R.id.etPlace);
        etAircraft = (EditText)findViewById(R.id.etAircraft);
        etGear = (EditText)findViewById(R.id.etGear);
        etType = (EditText)findViewById(R.id.etType);
        etExit = (EditText)findViewById(R.id.etExit);
        etDeploy = (EditText)findViewById(R.id.etDeploy);
        etDesc = (EditText)findViewById(R.id.etDesc);
        btEdit = (Button)findViewById(R.id.btEdit);
        UpdateData();



    }

    public void UpdateData(){
        btEdit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = db.updateData(etId.getText().toString(),
                                etPlace.getText().toString(),
                                etDate.getText().toString(),
                                etAircraft.getText().toString(),
                                etGear.getText().toString(),
                                etType.getText().toString(),
                                etExit.getText().toString(),
                                etDeploy.getText().toString(),
                                etDesc.getText().toString());
                        if (isUpdated == true)
                            Toast.makeText(Edit.this,"Data Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Edit.this,"No Data Updated",Toast.LENGTH_LONG).show();


                    }
                }
        );

    }
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnMain:
                Intent intent5 = new Intent(Edit.this, MainActivity.class);
                startActivity(intent5);
                return true;
            case R.id.mnAddJump:
                Intent intent = new Intent(Edit.this, AddJump.class);
                startActivity(intent);
                return true;
            case R.id.mnEdit:
                Intent intent2 = new Intent(Edit.this, Edit.class);
                startActivity(intent2);
                return true;
            case R.id.mnShowAll:
                Intent intent3 = new Intent(Edit.this, ShowAll.class);
                startActivity(intent3);
                return true;
            case R.id.mnDelete:
                Intent intent4 = new Intent(Edit.this, Delete.class);
                startActivity(intent4);
                return true;
            case R.id.mnWeather:
                Intent intent6 = new Intent(Edit.this, ShowWeather.class);
                startActivity(intent6);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}


