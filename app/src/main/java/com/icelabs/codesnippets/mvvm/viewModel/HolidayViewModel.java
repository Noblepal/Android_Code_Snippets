package com.icelabs.codesnippets.mvvm.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.icelabs.codesnippets.mvvm.model.HolidayModel;
import com.icelabs.codesnippets.mvvm.repository.HolidayRepo;

import java.util.List;

public class HolidayViewModel extends ViewModel {

    private HolidayRepo holidayRepo;
    private MutableLiveData<List<HolidayModel>> mutableLiveData;

    public HolidayViewModel() {
        holidayRepo = new HolidayRepo();
    }

    public LiveData<List<HolidayModel>> getHolidays(String year) {
        if (mutableLiveData == null) {
            mutableLiveData = holidayRepo.getHolidays(year);
        }

        return mutableLiveData;
    }
}
