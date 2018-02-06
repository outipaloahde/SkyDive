package com.example.outipupu.skydive;

import android.content.Intent;
import android.database.Cursor;
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

public class AddJump extends AppCompatActivity {
    DatabaseHelper db;
    EditText etDate, etPlace, etAircraft, etGear, etExit, etDeploy, etDesc, etType, etId;

    Button btSave, btShowAll, btEdit, btDel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addjump);
        db = new DatabaseHelper(this);

        etDate = (EditText)findViewById(R.id.etDate);
        etPlace = (EditText)findViewById(R.id.etPlace);
        etAircraft = (EditText)findViewById(R.id.etAircraft);
        etGear = (EditText)findViewById(R.id.etGear);
        etType = (EditText)findViewById(R.id.etType);
        etExit = (EditText)findViewById(R.id.etExit);
        etDeploy = (EditText)findViewById(R.id.etDeploy);
        etDesc = (EditText)findViewById(R.id.etDesc);
        btSave = (Button)findViewById(R.id.btSave);
        btShowAll = (Button)findViewById(R.id.btShowAll);
        btEdit = (Button)findViewById(R.id.btEdit);
        btDel = (Button)findViewById(R.id.btDel);
        AddData();
        viewAll();

    }

    public void AddData(){
        btSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = db.insertData(etDate.getText().toString(),
                                etPlace.getText().toString(),
                                etAircraft.getText().toString(),
                                etGear.getText().toString(),
                                etType.getText().toString(),
                                etExit.getText().toString(),
                                etDeploy.getText().toString(),
                                etDesc.getText().toString());

                        if (isInserted == true)
                            Toast.makeText(AddJump.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddJump.this,"No Data Inserted",Toast.LENGTH_LONG).show();



                    }
                }
        );
    }
    public void viewAll() {
        btShowAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor result = db.getAllJumps();
                        if (result.getCount() == 0) {
                            showMessage("Error ", "Nothing found");
                            //show message

                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (result.moveToNext()) {
                            buffer.append("Jump number: " +  result.getString(0) + "\n");
                            buffer.append("Place: " +  result.getString(1) + "\n");
                            buffer.append("Date: " +  result.getString(2) + "\n");
                            buffer.append("Aircraft: " +  result.getString(3) + "\n");
                            buffer.append("Gear: " +  result.getString(4) + "\n");
                            buffer.append("Type: " +  result.getString(5) + "\n");
                            buffer.append("Exit: " +  result.getString(6) + "\n");
                            buffer.append("Deploy: " +  result.getString(7) + "\n");
                            buffer.append("Description: " +  result.getString(8) + "\n\n");

                        }
                        showMessage("Data ", buffer.toString());//show all data

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
                Intent intent5 = new Intent(AddJump.this, MainActivity.class);
                startActivity(intent5);
                return true;
            case R.id.mnAddJump:
                Intent intent = new Intent(AddJump.this, AddJump.class);
                startActivity(intent);
                return true;
            case R.id.mnEdit:
                Intent intent2 = new Intent(AddJump.this, Edit.class);
                startActivity(intent2);
                return true;
            case R.id.mnShowAll:
                Intent intent3 = new Intent(AddJump.this, ShowAll.class);
                startActivity(intent3);
                return true;
            case R.id.mnDelete:
                Intent intent4 = new Intent(AddJump.this, Delete.class);
                startActivity(intent4);
                return true;
            case R.id.mnWeather:
                Intent intent6 = new Intent(AddJump.this, ShowWeather.class);
                startActivity(intent6);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
