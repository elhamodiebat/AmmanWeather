package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import utils.utlis;

public class WeatherHttpClient {
    public String getWeatherData(String place) {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;


        try {
            urlConnection = (HttpURLConnection) (new URL(utlis.BASE_URL + place)).openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.setDoInput(true);
            urlConnection.connect();

            StringBuffer stringBuffer = new StringBuffer();
            inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + "\r\n");


            }
            inputStream.close();
            urlConnection.disconnect();

            return stringBuffer.toString();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
