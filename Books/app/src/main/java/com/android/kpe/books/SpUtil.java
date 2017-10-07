package com.android.kpe.books;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by LocalUser on 7/10/2017.
 */

public class SpUtil {

    private static final String PREF_NAME = "BooksPreferences";
    public static final String POSITION = "position";
    public static final String QUERY = "query";

    private SpUtil() {}

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static String getString(Context context, String key) {
        return getSharedPreferences(context).getString(key, "");
    }

    public static int getInt(Context context, String key) {
        return getSharedPreferences(context).getInt(key, 0);
    }

    public static void setString(Context context, String key, String value) {
        getSharedPreferences(context)
                .edit().putString(key, value).apply();
    }

    public static void setInt(Context context, String key, int value) {
        getSharedPreferences(context)
                .edit().putInt(key, value).apply();
    }

    public static void recordQuery(Context context, String title) {

        int position = getInt(context, SpUtil.POSITION);
        if(position <= 0 || position >= 5) {
            position = 1;
        } else {
            position += 1;
        }
        String key = QUERY + String.valueOf(position);
        setString(context, key, title);
        setInt(context, POSITION, position);

    }

    public static String getQuery(Context context, int position) {
        String key = QUERY + String.valueOf(position);
        return getString(context, key);
    }

    public static ArrayList<String> getQueryList(Context context) {
        ArrayList<String> retVal = new ArrayList<String>();
        for(int index=1; index <= 5; index++) {
            String query = getString(context, QUERY + String.valueOf(index));
            if(!query.isEmpty()) {
                retVal.add(query.replace(",", "").trim());
            }
        }
        return retVal;
    }

}
