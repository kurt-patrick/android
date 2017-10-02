package com.android.kpe.books;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by LocalUser on 21/09/2017.
 */

public class ApiUtil {

    private static final String BASE_API_URL = "https://www.googleapis.com/books/v1/volumes";
    private static final String QUERY_PARAM_Q = "q";
    private static final String QUERY_PARAM_KEY = "key";
    private static final String API_KEY = "AIzaSyApzrJIZdXmUnG_huKgsvewzmbOxMvBliM";

    private ApiUtil() {}

    public static URL buildUrl(String title) {

        URL url = null;
        Uri uri = Uri.parse(BASE_API_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM_Q, title)
                .build();
        /*
                .appendQueryParameter(QUERY_PARAM_KEY, API_KEY)
                .build();
*/
        try {
            url = new URL(uri.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getJson(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");

            if(scanner.hasNext()) {
                return scanner.next();
            }
            return null;
        }
        catch (Exception e) {
            Log.d("Error", e.toString());
            return null;
        }
        finally {
            connection.disconnect();
        }

    }

    public static ArrayList<Book> getBooksFromJson(String json) {
        final String ID = "id";
        final String TITLE = "title";
        final String SUBTITLE = "subTitle";
        final String AUTHORS = "authors";
        final String PUBLISHER = "publisher";
        final String PUBLISHED_DATE = "publishedDate";
        final String ITEMS = "items";
        final String VOLUME_INFO = "volumeInfo";
        final String DESCRIPTION = "description";
        final String IMAGE_LINKS = "imageLinks";
        final String THUMBNAIL = "thumbnail";

        ArrayList<Book> retVal = new ArrayList<Book>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(ITEMS);

            int itemCount = jsonArray.length();

            for(int index = 0; index < itemCount; index++) {

                JSONObject bookJsonObject = jsonArray.getJSONObject(index);
                JSONObject volumeInfoObject = bookJsonObject.getJSONObject(VOLUME_INFO);
                JSONObject imageLinksJSON = volumeInfoObject.getJSONObject(IMAGE_LINKS);

                int authorCount = volumeInfoObject.getJSONArray(AUTHORS).length();
                String[] authors = new String[authorCount];
                for(int authorIndex = 0; authorIndex < authorCount; authorIndex++) {
                    authors[authorIndex] = volumeInfoObject.getJSONArray(AUTHORS).get(authorIndex).toString();
                }

                Book book = new Book(
                        bookJsonObject.getString(ID),
                        volumeInfoObject.getString(TITLE),
                        volumeInfoObject.isNull(SUBTITLE) ? "" : volumeInfoObject.getString(SUBTITLE),
                        authors,
                        volumeInfoObject.getString(PUBLISHER),
                        volumeInfoObject.getString(PUBLISHED_DATE),
                        volumeInfoObject.getString(DESCRIPTION),
                        imageLinksJSON.getString(THUMBNAIL)
                );

                retVal.add(book);

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


        return retVal;
    }


}
