package com.example.simple_ecommerce.data.util;

import static com.example.simple_ecommerce.data.util.Constants.KEY_ONBOARDING_COMPLETED;
import static com.example.simple_ecommerce.data.util.Constants.KEY_USER_TOKEN;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {
    private static final String PREF_NAME = "onboarding_pref";
    private static final String PREF_TOKEN = "user_token";

    public static void saveOnboardingStatus(Context context, boolean isOnboardingCompleted) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_ONBOARDING_COMPLETED, isOnboardingCompleted);
        editor.apply();
    }

    public static boolean getOnboardingStatus(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(KEY_ONBOARDING_COMPLETED, false);
    }

    public static void saveLoginToken(Context context, String tokenValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_TOKEN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_TOKEN, tokenValue);
        editor.apply();
    }

    public static String getLoginToken(Context context, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_TOKEN, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_TOKEN, value);
    }

    public static void clearLoginDetails(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_TOKEN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_USER_TOKEN);
        editor.apply();
    }

    public static void saveUserDetail(Context context, String username) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_TOKEN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_TOKEN, username);
        editor.apply();
    }
}