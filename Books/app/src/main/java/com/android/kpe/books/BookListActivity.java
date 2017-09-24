package com.android.kpe.books;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {

    private RecyclerView mBooksRecyclerView = null;
    private TextView mErrorTextView = null;
    private ProgressBar mLoadingProgressBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        getBooksRecyclerView().setVisibility(View.VISIBLE);
        getLoadingProgressBar().setVisibility(View.INVISIBLE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        getBooksRecyclerView().setLayoutManager(layoutManager);

        try {
            URL bookUrl = ApiUtil.buildUrl("cooking");
            new BooksAsyncTask().execute(bookUrl);
        }
        catch (Exception e) {
            Log.d("error", e.getMessage());
        }
    }

    private RecyclerView getBooksRecyclerView() {
        if(mBooksRecyclerView == null) {
            mBooksRecyclerView = ((RecyclerView) findViewById(R.id.recBooks));
        }
        return mBooksRecyclerView;
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

            getBooksRecyclerView().setVisibility(books.isEmpty() ? View.INVISIBLE : View.VISIBLE);
            getErrorTextView().setVisibility(books.isEmpty() ? View.VISIBLE : View.INVISIBLE);

            if(!books.isEmpty()) {
                BooksAdapter adapter = new BooksAdapter(books);
                getBooksRecyclerView().setAdapter(adapter);
            }
        }

        @Override
        protected void onPreExecute() {
            getLoadingProgressBar().setVisibility(View.VISIBLE);
        }
    }


}



