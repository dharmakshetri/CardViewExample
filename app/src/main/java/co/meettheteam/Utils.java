package co.meettheteam;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by horror on 11/15/16.
 */

public class Utils {
    //URL
    public  static  final String URL="http://dharmakshetri.com.np/data/json/team.json";
    // define the json tags
    public static final String TAG_ID="id";
    public static final String TAG_AVATAR="avatar";
    public static final String TAG_BIO="bio";
    public static final String TAG_FIRSTNAME="firstName";
    public static final String TAG_LASTNAME="lastName";
    public static final String TAG_TITLE="title";


    // load json from assets
    public static String loadJSONFromAsset(Activity activity) {
        String json = null;
        try {
            InputStream is = activity.getAssets().open("team.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    // get data from servers and local and set in to list of users
    public static  void setUserData(JSONArray jsonArray){
        try {
            UserResponse.users.clear();
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id=jsonObject.getString(Utils.TAG_ID);
                // Log.e("ID","ID="+id);
                String title = jsonObject.getString(Utils.TAG_TITLE);
                String firstName = jsonObject.getString(Utils.TAG_FIRSTNAME);
                String lastName = jsonObject.getString(Utils.TAG_LASTNAME);
                String bio=jsonObject.getString(Utils.TAG_BIO);
                String avatar=jsonObject.getString(Utils.TAG_AVATAR);

                //Add your values in your `ArrayList` as below:
                User user= new User(id, title,firstName, lastName,bio,avatar);
                //User user=UserResponse.parseJSON(jo_inside.toString());

                UserResponse.users.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
