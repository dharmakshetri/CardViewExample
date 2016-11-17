package co.meettheteam;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by horror on 11/15/16.
 */

public class UserResponse {
   public static List<User>  users = new ArrayList<User>();;
    // public constructor is necessary for collections
    public UserResponse(User user) {
        users.add(user);
    }

    public static User parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        User userResponse = gson.fromJson(response, User.class);
        return userResponse;
    }
}