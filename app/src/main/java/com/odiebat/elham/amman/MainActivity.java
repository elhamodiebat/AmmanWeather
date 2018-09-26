package com.odiebat.elham.amman;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

import data.JSONWeatherParser;
import data.WeatherHttpClient;
import model.Weather;
import utils.utlis;

public class MainActivity extends AppCompatActivity {


    private TextView cityName;
    private TextView Temp;
    private TextView cloud;
    private TextView humidity;
    private TextView wind;
    private TextView pressure;
    private TextView sunrise;
    private TextView sunset;
    private TextView updated;
    private TextView maxTemp;
    private TextView minTemp;


    Weather weather = new Weather();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cityName = findViewById(R.id.cityText);
        Temp = findViewById(R.id.tempText);
        cloud = findViewById(R.id.cloudText);
        humidity = findViewById(R.id.humidityText);
        wind = findViewById(R.id.windText);
        pressure = findViewById(R.id.pressureText);
        sunrise = findViewById(R.id.riseText);
        sunset = findViewById(R.id.setText);
        updated = findViewById(R.id.lastUpdate);
        maxTemp = findViewById(R.id.max);
        minTemp = findViewById(R.id.min);


        renderWeatherData("Amman,JO");
    }


    public void renderWeatherData(String city) {

        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city + "&appid=4788ee4ce9877888efd3ba241e06e19c"});
    }


    private class WeatherTask extends AsyncTask<String, Void, Weather> {
        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);


            DateFormat dateFormat = DateFormat.getTimeInstance();
            String sunriseDate = dateFormat.format(new Date(weather.place.getSunrice()));
            String sunsetDate = dateFormat.format(new Date(weather.place.getSunset()));
            String update = dateFormat.format(new Date(weather.place.getLastUpdate()));


            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String tempFormat = decimalFormat.format(weather.currentCondition.getTemperature());
            String maxFormat = decimalFormat.format(weather.currentCondition.getMaxTemp());
            String minFormat = decimalFormat.format(weather.currentCondition.getMinTemp());

            maxTemp.setText(getString(R.string.max) + maxFormat);
            minTemp.setText(getString(R.string.Min) + minFormat);
            cityName.setText(weather.place.getCity() + "," + weather.place.getCountry());
            Temp.setText(tempFormat + "°C");
            humidity.setText(weather.currentCondition.getHumidity() + " %");
            pressure.setText(weather.currentCondition.getPressure() + " hPa");
            wind.setText(weather.wind.getSpeed() + " mPs");
            sunrise.setText(sunriseDate);
            sunset.setText(sunsetDate);
            cloud.setText(weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescription() + ")");
            updated.setText(getString(R.string.last_update) + update);


        }

        @Override
        protected Weather doInBackground(String... strings) {

            String data = ((new WeatherHttpClient()).getWeatherData(strings[0]));


            weather = JSONWeatherParser.getWeather(data);

            Log.v("weather", weather.place.getCity().toString());
            Log.v("lat", weather.place.getLat() + " hi ");
            // ooh we can now go to onPost execute in order to show the data


            return weather;
        }
    }


}
