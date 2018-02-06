package com.example.outipupu.skydive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
                Intent intent5 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent5);
                return true;
            case R.id.mnAddJump:
                Intent intent = new Intent(MainActivity.this, AddJump.class);
                startActivity(intent);
                return true;
            case R.id.mnEdit:
                Intent intent2 = new Intent(MainActivity.this, Edit.class);
                startActivity(intent2);
                return true;
            case R.id.mnShowAll:
                Intent intent3 = new Intent(MainActivity.this, ShowAll.class);
                startActivity(intent3);
                return true;
            case R.id.mnDelete:
                Intent intent4 = new Intent(MainActivity.this, Delete.class);
                startActivity(intent4);
                return true;
            case R.id.mnWeather:
                Intent intent6 = new Intent(MainActivity.this, ShowWeather.class);
                startActivity(intent6);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
