package com.ariemay.entertainmentlistmade3.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TV implements Parcelable {

    private int id;
    private String name;
    private String first_air_date;
    private String vote_average;
    private String overview;
    private String poster_path;
    private String backdrop_path;

    public TV(JSONObject object) {
        try {
            int id = object.getInt("id");
            String name = object.getString("name");
            int vote_average = object.getInt("vote_average");
            String overview = object.getString("overview");
            String first_air_date = object.getString("first_air_date");
            String poster_path = "https://image.tmdb.org/t/p/w185/" + object.getString("poster_path");
            String backdrop_path = "https://image.tmdb.org/t/p/w185/" + object.getString("backdrop_path");
            this.id = id;
            this.name = name;
            this.vote_average = Integer.toString(vote_average);
            this.overview = overview;
            this.first_air_date = first_air_date;
            this.poster_path = poster_path;
            this.backdrop_path = backdrop_path;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.first_air_date);
        dest.writeString(this.vote_average);
        dest.writeString(this.overview);
        dest.writeString(this.poster_path);
        dest.writeString(this.backdrop_path);
    }

    public TV() {
    }

    protected TV(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.first_air_date = in.readString();
        this.vote_average = in.readString();
        this.overview = in.readString();
        this.poster_path = in.readString();
        this.backdrop_path = in.readString();
    }

    public static final Creator<TV> CREATOR = new Creator<TV>() {
        @Override
        public TV createFromParcel(Parcel source) {
            return new TV(source);
        }

        @Override
        public TV[] newArray(int size) {
            return new TV[size];
        }
    };
}
