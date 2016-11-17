package co.meettheteam;

import com.google.gson.annotations.SerializedName;

/**
 * Created by horror on 11/15/16.
 */
public class User {
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("bio")
    private String bio;
    @SerializedName("avatar")
    private String avatar;


    public User(String id, String title, String firstName, String lastName, String bio, String avatar) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.avatar = avatar;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
