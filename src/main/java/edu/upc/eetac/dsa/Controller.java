package edu.upc.eetac.dsa;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<List<Tracks>> {

    static final String BASE_URL = "http://localhost:8080/dsaApp/";


    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
        SwaggerAPI api = retrofit.create(SwaggerAPI.class);
        Call<List<Tracks>> callGetTracks = api.getTracks();
        callGetTracks.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Tracks>> call, Response<List<Tracks>> response) {
        if(response.isSuccessful()) {
            List<Tracks> trackList = response.body();
            trackList.forEach(track -> System.out.println("id: " + track.getId() + "\n" + "name: " + track.getTitle() + "\n" + "singer: " + track.getSinger()));
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Tracks>> call, Throwable t) {
        t.printStackTrace();
    }
}
