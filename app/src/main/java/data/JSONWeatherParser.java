package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Place;
import model.Weather;
import util.Utils;

/**
 * Created by outipupu on 03/12/2017.
 */

public class JSONWeatherParser {
    public static Weather getWeather(String data){
        Weather weather = new Weather();

        // luodaan JSONObject datasta
        try {
            JSONObject jsonObject = new JSONObject(data);
            Place place = new Place();
            // coodrObj pitää koordinaatit, haetaan Utilsist, asetetaan placeen
            JSONObject coordObj = Utils.getObject("coord", jsonObject);
            place.setLat(Utils.getFloat("lat", coordObj));
            place.setLon(Utils.getFloat("lon", coordObj));

            //haetaat sys objekti
            JSONObject sysObj = Utils.getObject("sys", jsonObject);
            place.setCountry(Utils.getString("country", sysObj));
            place.setLastUpdate(Utils.getInt("dt", jsonObject));
            place.setSunrise(Utils.getInt("sunrise", sysObj));
            place.setSunset(Utils.getInt("sunset", sysObj));
            place.setCity(Utils.getString("name", jsonObject));
            weather.place = place;

            // Weather infon haku
            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            JSONObject jsonWeather = jsonArray.getJSONObject(0);
            weather.currentCondition.setWeatherId(Utils.getInt("id", jsonWeather));
            weather.currentCondition.setDescription(Utils.getString("description", jsonWeather));
            weather.currentCondition.setCondition(Utils.getString("main", jsonWeather));
            weather.currentCondition.setIcon(Utils.getString("icon", jsonWeather));

            JSONObject windObj = Utils.getObject("wind", jsonObject);
            weather.wind.setSpeed(Utils.getFloat("speed", jsonObject));
            weather.wind.setDeg(Utils.getFloat("deg", jsonObject));

            JSONObject cloudObj = Utils.getObject("clouds", jsonObject);
            weather.clouds.setPrecipitation(Utils.getInt("all", cloudObj));

            return weather;


        } catch (JSONException e) {
            e.printStackTrace();

            return null;
        }
    }
}
