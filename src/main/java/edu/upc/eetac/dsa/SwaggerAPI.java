package edu.upc.eetac.dsa;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface SwaggerAPI {
    @GET("tracks/")
    Call<List<Tracks>> getTracks();

    @POST("tracks/")
    Call<Tracks> postTrack(@Body Tracks track);

    @DELETE("tracks/{id}")
    Call<Tracks> deleteTrack(@Path("id") String id);

    @PUT("tracks")
    Call<Tracks> updateTrack(@Body Tracks track);

    @GET("tracks/{id}")
    Call<Tracks> getTrack(@Path("id") String id);

}
