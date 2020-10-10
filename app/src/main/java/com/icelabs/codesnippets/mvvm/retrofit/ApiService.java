package com.icelabs.codesnippets.mvvm.retrofit;


import com.icelabs.codesnippets.mvvm.model.HolidayModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    /*Endpoint call*/

    @GET("PublicHolidays/{year}/us")
    Call<List<HolidayModel>> getHolidays(
            @Path("year") String year
    );

    @POST("endpoint")
    Call<ResponseBody> requestHolidays();
}
