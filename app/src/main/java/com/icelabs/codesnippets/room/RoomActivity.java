package com.icelabs.codesnippets.room;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.icelabs.codesnippets.R;
import com.icelabs.codesnippets.adapter.UserAdapter;
import com.icelabs.codesnippets.databinding.ActivityRoomBinding;
import com.icelabs.codesnippets.room.model.User;
import com.icelabs.codesnippets.util.AppExecutors;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity {

    private ActivityRoomBinding b;
    private AppExecutors appExecutors;
    private AppDatabase appDatabase;
    private List<User> userList = new ArrayList<>();
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_room);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Room Database Example");
        getSupportActionBar().setSubtitle("simple user store example");

        appDatabase = AppDatabase.getInstance(this);
        appExecutors = AppExecutors.getInstance();

        initUI();

        getAllUsers();

    }

    private void initUI() {
        b.rvUsers.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(userList, this);
        b.rvUsers.setAdapter(adapter);

        b.btnSaveUser.setOnClickListener(v -> {
            String username = b.edtUsername.getText().toString().trim();
            String email = b.edtEmail.getText().toString().trim();
            String password = b.edtPassword.getText().toString().trim();
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else {
                User userToBeSaved = new User(username, email, password);
                saveUser(userToBeSaved);
            }
        });

    }

    private void saveUser(User user) {
        appExecutors.diskIO().execute(() -> {
            appDatabase.userDao().addUser(user);
        });
    }

    private void getAllUsers() {
        appExecutors.diskIO().execute(() -> {
            runOnUiThread(() -> {
                appDatabase.userDao().getAllUsers().observe(RoomActivity.this, users -> {
                    if (users != null && users.size() > 0) {
                        userList.clear();
                        userList.addAll(users);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, "List updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "No users in database", Toast.LENGTH_SHORT).show();
                    }

                });
            });
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