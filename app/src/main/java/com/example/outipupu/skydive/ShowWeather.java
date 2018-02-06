package com.example.outipupu.skydive;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import data.JSONWeatherParser;
import data.WeatherHttpClient;
import model.Weather;

/**
 * Created by outipupu on 03/12/2017.
 */

public class ShowWeather extends AppCompatActivity {

    private TextView cityName;
    private TextView temp;
    private ImageView iconView;
    private TextView description;
    private TextView humidity;
    private TextView pressure;
    private TextView wind;
    private TextView sunrise;
    private TextView sunset;
    private TextView updated;


    Weather weather = new Weather();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = (TextView) findViewById(R.id.cityText);
        temp = (TextView) findViewById(R.id.tempText);
        iconView = (ImageView) findViewById(R.id.thumbnailIcon);
        humidity = (TextView) findViewById(R.id.humidityText);
        pressure = (TextView) findViewById(R.id.pressureText);
        wind = (TextView) findViewById(R.id.windText);
        sunrise = (TextView) findViewById(R.id.riseText);
        sunset = (TextView) findViewById(R.id.setText);
        description = (TextView) findViewById(R.id.cloudText);
        updated = (TextView) findViewById(R.id.updateText);

        renderWeatherData("London");


    }

    public void renderWeatherData(String city) {
        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city});

    }

    private class WeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            // data = kaikki mitä stringBuffer sisältää
            String data = ((new WeatherHttpClient()).getWeatherData(params[0]));
            // parsataan
            weather = JSONWeatherParser.getWeather(data);
            Log.v("Data: ", weather.place.getCity());

            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

       /*Ä     DateFormat df = new DateFormat.getTimeInstance();
            String sunriseDate = df.format(new Date(weather.place.getSunrise()));
              */


            cityName.setText(weather.place.getCity() + ", " + weather.place.getCountry());
            temp.setText("" + weather.currentCondition.getTemperature() + " C");
            humidity.setText("Humidity: " + weather.currentCondition.getHumidity() + " %");
            pressure.setText("Pressure: " + weather.currentCondition.getPressure() + " hPa");
            wind.setText("Wind: " + weather.wind.getSpeed() + "mps");
            sunrise.setText("Sunrise: " + weather.place.getSunrise());
            sunset.setText("Sunset: " + weather.place.getSunset());
            updated.setText("Last updated: " + weather.place.getLastUpdate());
            description.setText("Condition: " + weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescription() + ")");



        }

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
                Intent intent5 = new Intent(ShowWeather.this, MainActivity.class);
                startActivity(intent5);
                return true;
            case R.id.mnAddJump:
                Intent intent = new Intent(ShowWeather.this, AddJump.class);
                startActivity(intent);
                return true;
            case R.id.mnEdit:
                Intent intent2 = new Intent(ShowWeather.this, Edit.class);
                startActivity(intent2);
                return true;
            case R.id.mnShowAll:
                Intent intent3 = new Intent(ShowWeather.this, ShowAll.class);
                startActivity(intent3);
                return true;
            case R.id.mnDelete:
                Intent intent4 = new Intent(ShowWeather.this, Delete.class);
                startActivity(intent4);
                return true;
            case R.id.mnWeather:
                Intent intent6 = new Intent(ShowWeather.this, ShowWeather.class);
                startActivity(intent6);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
