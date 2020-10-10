package com.icelabs.codesnippets.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.icelabs.codesnippets.R;
import com.icelabs.codesnippets.databinding.ActivityMainBinding;
import com.icelabs.codesnippets.mvvm.MvvmActivity;
import com.icelabs.codesnippets.room.RoomActivity;

public class MainActivity extends AppCompatActivity {

    //Data binding class
    private ActivityMainBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Android Code Snippets");
        getSupportActionBar().setSubtitle("with examples");

        b.btnRoom.setOnClickListener(v -> startActivity(new Intent(this, RoomActivity.class)));
        b.btnMVVM.setOnClickListener(v -> startActivity(new Intent(this, MvvmActivity.class)));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}