package com.icelabs.codesnippets.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsManager {
    private static final String KEY_USER_DATA = "key_user_data";
    private static final String KEY_IS_USER_REGISTERED = "key_is_user_registered";
    private static final String KEY_CACHED_TAGS = "key_cached_tags";
    private static SharedPreferences sharedPreferences;
    private static SharedPrefsManager mInstance;
    private final String SHARED_PREFS_NAME = "kenya_sihami";
    private static final String TAG = "SharedPrefsManager";

    private SharedPrefsManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPrefsManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefsManager(context);
        }
        return mInstance;
    }
}
