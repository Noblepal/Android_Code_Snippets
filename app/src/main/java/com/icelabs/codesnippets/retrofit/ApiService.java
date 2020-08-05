package com.icelabs.codesnippets.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiService {
    /*Register anonymous account*/
    @POST("endpoint")
    Call<ResponseBody> requestMethod();
}
