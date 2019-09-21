package com.ariemay.entertainmentlistmade3.services.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.ariemay.entertainmentlistmade3.models.Movies;
import com.ariemay.entertainmentlistmade3.models.TV;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.ariemay.entertainmentlistmade3.services.Constants.Data.SEARCH_MOVIE_URL;
import static com.ariemay.entertainmentlistmade3.services.Constants.Data.SEARCH_TV_URL;
import static com.ariemay.entertainmentlistmade3.services.Constants.Data.TV_URL;

public class TVViewModel extends ViewModel {

    private MutableLiveData<ArrayList<TV>> listTv = new MutableLiveData<>();

    public void setTV() {
        final ArrayList<TV> listItems = new ArrayList<>();

        AsyncHttpClient client = new AsyncHttpClient();
        String url = TV_URL;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject tv = list.getJSONObject(i);
                        TV tvItem = new TV(tv);
                        listItems.add(tvItem);
                        Log.d("LISTITEMSTV", listItems.toString());
                    }

                    listTv.postValue(listItems);

                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public void searchTV(String input) {
        final ArrayList<TV> listItems = new ArrayList<>();

        AsyncHttpClient client = new AsyncHttpClient();
        String url = SEARCH_TV_URL + input;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    Log.d("SEARCH", result.toString());
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject tv = list.getJSONObject(i);
                        TV tvItems = new TV(tv);
                        listItems.add(tvItems);
                    }

                    listTv.postValue(listItems);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<TV>> getTVShow() {
        return listTv;
    }
}
