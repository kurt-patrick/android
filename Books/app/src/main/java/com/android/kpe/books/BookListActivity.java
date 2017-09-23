package com.android.kpe.books;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {

    private ProgressBar mLoadingProgressBar = null;
    private TextView mErrorTextView = null;
    private TextView mResponseTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        getResponseTextView().setVisibility(View.INVISIBLE);
        getLoadingProgressBar().setVisibility(View.INVISIBLE);

        try {
            URL bookUrl = ApiUtil.buildUrl("cooking");
            new BooksAsyncTask().execute(bookUrl);
        }
        catch (Exception e) {
            Log.d("error", e.getMessage());
        }
    }

    private ProgressBar getLoadingProgressBar() {
        if(mLoadingProgressBar == null) {
            mLoadingProgressBar = ((ProgressBar) findViewById(R.id.prgLoading));
        }
        return mLoadingProgressBar;
    }

    private TextView getErrorTextView() {
        if(mErrorTextView == null) {
            mErrorTextView = ((TextView) findViewById(R.id.txtError));
        }
        return mErrorTextView;
    }

    private TextView getResponseTextView() {
        if(mResponseTextView == null) {
            mResponseTextView = ((TextView) findViewById(R.id.txtResponse));
        }
        return mResponseTextView;
    }

    public class BooksAsyncTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... params) {
            String retVal = null;
            try {
                retVal = ApiUtil.getJson(params[0]);
            }
            catch (Exception e) {
                Log.d("error", e.getMessage());
            }
            return retVal;
        }

        @Override
        protected void onPostExecute(String s) {
            getLoadingProgressBar().setVisibility(View.INVISIBLE);

            ArrayList<Book> books = ApiUtil.getBooksFromJson(s);

            getResponseTextView().setVisibility(books.isEmpty() ? View.INVISIBLE : View.VISIBLE);
            getErrorTextView().setVisibility(books.isEmpty() ? View.VISIBLE : View.INVISIBLE);

            if(!books.isEmpty()) {

                StringBuilder sb = new StringBuilder();
                for (Book book : books) {
                    sb.append(book.title);
                    sb.append("\n");
                    sb.append(book.publishedDate);
                    sb.append("\n\n");
                }
                getResponseTextView().setText(sb.toString());

            }
        }

        @Override
        protected void onPreExecute() {
            getLoadingProgressBar().setVisibility(View.VISIBLE);
        }
    }


}



