package com.android.kpe.books;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

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

        Intent intent = getIntent();
        String query = intent.getStringExtra("query");

        try {

            URL bookUrl = null;
            if(query == null || query.trim().length() == 0) {
                bookUrl = ApiUtil.buildUrl("cooking");
            } else {
                bookUrl = new URL(query);
            }

            new BooksAsyncTask().execute(bookUrl);

        }
        catch (Exception e) {
            Log.d("error", e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.book_list_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        // Dynamic menu items
        MenuItem menuItem = null;
        ArrayList<String>  queries = SpUtil.getQueryList(getApplicationContext());

        for(int index=0; index<queries.size(); index++) {
            menuItem = menu.add(Menu.NONE, index, Menu.NONE, queries.get(index));
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_advanced_search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                return true;
            default:

                int position = item.getItemId() + 1;
                String query = SpUtil.getQuery(getApplicationContext(), position);
                URL searchUrl = ApiUtil.buildUrl(query);
                new BooksAsyncTask().execute(searchUrl);

                return super.onOptionsItemSelected(item);
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        try {
            URL bookUrl = ApiUtil.buildUrl(query);
            new BooksAsyncTask().execute(bookUrl);
        }
        catch (Exception e) {
            Log.d("error", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
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
                BooksAdapterTwo adapter = new BooksAdapterTwo(books);
                getBooksRecyclerView().setAdapter(adapter);
            }
        }

        @Override
        protected void onPreExecute() {
            getLoadingProgressBar().setVisibility(View.VISIBLE);
        }
    }


}



