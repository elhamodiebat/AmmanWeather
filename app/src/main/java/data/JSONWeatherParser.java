package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Place;
import model.Weather;
import utils.utlis;

public class JSONWeatherParser {

    public static Weather getWeather(String data) {
        Weather weather = new Weather();
        try {
            JSONObject jsonObject = new JSONObject(data);
            Place place = new Place();


            // coord json object info
            JSONObject coorObj = utlis.getObject("coord", jsonObject);
            place.setLat(utlis.getFloat("lat", coorObj));
            place.setLon(utlis.getFloat("lon", coorObj));


            // sys json object info

            JSONObject sysObj = utlis.getObject("sys", jsonObject);

            place.setSunrice(utlis.getInt("sunrise", sysObj));
            place.setSunset(utlis.getInt("sunset", sysObj));
            place.setCountry(utlis.getString("country", sysObj));
            place.setLastUpdate(utlis.getInt("dt", jsonObject));
            place.setCity(utlis.getString("name", jsonObject));
            weather.place = place;


            // weather json array info
            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            JSONObject jsonWeather = jsonArray.getJSONObject(0);
            weather.currentCondition.setWeatherId(utlis.getInt("id", jsonWeather));
            weather.currentCondition.setDescription(utlis.getString("description", jsonWeather));
            weather.currentCondition.setCondition(utlis.getString("main", jsonWeather));
            weather.currentCondition.setIcon(utlis.getString("icon", jsonWeather));


            JSONObject mainOpj = utlis.getObject("main", jsonObject);
            weather.currentCondition.setHumidity(utlis.getInt("humidity", mainOpj));
            weather.currentCondition.setTemperature(utlis.getFloat("temp", mainOpj));
            weather.currentCondition.setPressure(utlis.getInt("pressure", mainOpj));
            weather.currentCondition.setMaxTemp(utlis.getFloat("temp_max", mainOpj));
            weather.currentCondition.setMinTemp(utlis.getFloat("temp_min", mainOpj));
            // wind json object info

            JSONObject windObj = utlis.getObject("wind", jsonObject);

            weather.wind.setSpeed(utlis.getFloat("speed", windObj));
            weather.wind.setDeg(utlis.getInt("deg", windObj));


            // Cloud json object info


            JSONObject cloudsdObj = utlis.getObject("clouds", jsonObject);
            weather.cloud.setPrecipitation(utlis.getInt("all", cloudsdObj));


            return weather;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
