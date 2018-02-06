package com.example.outipupu.skydive;

import android.content.Intent;
import android.os.Bundle;
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

public class Delete extends AppCompatActivity {
    DatabaseHelper db;
    EditText etId;
    Button btDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        db = new DatabaseHelper(this);

        etId = (EditText)findViewById(R.id.etId);
        btDel = (Button)findViewById(R.id.btDel);
        DeleteData();



    }
    public void DeleteData(){
        btDel.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Integer deletedRows = db.deleteData(etId.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(Delete.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Delete.this,"No Data Deleted",Toast.LENGTH_LONG).show();

                    }
                }
        );

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
                Intent intent5 = new Intent(Delete.this, MainActivity.class);
                startActivity(intent5);
                return true;
            case R.id.mnAddJump:
                Intent intent = new Intent(Delete.this, AddJump.class);
                startActivity(intent);
                return true;
            case R.id.mnEdit:
                Intent intent2 = new Intent(Delete.this, Edit.class);
                startActivity(intent2);
                return true;
            case R.id.mnShowAll:
                Intent intent3 = new Intent(Delete.this, ShowAll.class);
                startActivity(intent3);
                return true;
            case R.id.mnDelete:
                Intent intent4 = new Intent(Delete.this, Delete.class);
                startActivity(intent4);
                return true;
            case R.id.mnWeather:
                Intent intent6 = new Intent(Delete.this, ShowWeather.class);
                startActivity(intent6);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
