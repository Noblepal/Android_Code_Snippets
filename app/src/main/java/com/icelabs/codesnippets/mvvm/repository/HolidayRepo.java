package com.icelabs.codesnippets.mvvm.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import com.icelabs.codesnippets.mvvm.model.HolidayModel;
import com.icelabs.codesnippets.mvvm.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HolidayRepo {

    private static final String TAG = "HolidayRepo";

    public MutableLiveData<List<HolidayModel>> getHolidays(String year) {
        MutableLiveData<List<HolidayModel>> mutableLiveData = new MutableLiveData<>();

        RetrofitClient.getApiService().getHolidays(year)
                .enqueue(new Callback<List<HolidayModel>>() {
                    @Override
                    public void onResponse(Call<List<HolidayModel>> call, Response<List<HolidayModel>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Log.e(TAG, "onResponse: holidays " + response.body().size());
                            mutableLiveData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<HolidayModel>> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                    }
                });
        return mutableLiveData;
    }
}
