package utils;

import org.json.JSONException;
import org.json.JSONObject;

public class utlis {

    public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    public static final String ICON_URL = "https://openweathermap.org/img/w/";


    public static JSONObject getObject(String tagName,JSONObject jsonObject) throws JSONException{
        JSONObject JObj = jsonObject.getJSONObject(tagName);
        return JObj;
    }



    public static String getString(String tagName,JSONObject jsonObject) throws JSONException{
        return jsonObject.getString(tagName);
    }

    public static float getFloat(String tagName,JSONObject jsonObject) throws JSONException{
        return (float) jsonObject.getDouble(tagName);
    }


    public static double getDouble(String tagName,JSONObject jsonObject) throws JSONException{
        return (float) jsonObject.getDouble(tagName);
    }

    public static int getInt(String tagName,JSONObject jsonObject) throws JSONException{
        return (int) jsonObject.getInt(tagName);
    }
}
