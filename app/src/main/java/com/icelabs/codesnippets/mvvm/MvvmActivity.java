package com.icelabs.codesnippets.mvvm;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.icelabs.codesnippets.R;
import com.icelabs.codesnippets.databinding.ActivityMainMvvmBinding;
import com.icelabs.codesnippets.mvvm.model.HolidayModel;
import com.icelabs.codesnippets.mvvm.viewModel.HolidayViewModel;

import java.util.List;

public class MvvmActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainMvvmBinding b;
    private String year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_main_mvvm);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("MVVM Example");
        getSupportActionBar().setSubtitle("retrieve public holidays using mvvm");

        b.spnYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                year = adapterView.getSelectedItem().toString();
                getHolidays(year);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.e(TAG, "onNothingSelected: Nothing selected");
            }
        });

    }

    private void getHolidays(String year) {
        HolidayViewModel holidayViewModel = new HolidayViewModel();
        holidayViewModel.getHolidays(year).observe(this, new Observer<List<HolidayModel>>() {
            @Override
            public void onChanged(List<HolidayModel> holidayModels) {
                b.tvHolidays.setText("");
                for (HolidayModel h : holidayModels) {
                    b.tvHolidays.append(new StringBuilder()
                            .append(h.getName())
                            .append(" - ")
                            .append(h.getDate())
                            .append("\n")
                            .toString());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}