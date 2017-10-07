package com.android.kpe.books;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final EditText txtTitle = (EditText) findViewById(R.id.txtTitle);

        final Button button = (Button) findViewById(R.id.btnSearch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!hasSearchCriteria()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.no_search_text), Toast.LENGTH_LONG);
                } else {

                    String title = getEditText(R.id.txtTitle);
                    URL queryUrl = ApiUtil.buildUrl(title);

                    // shared prefs
                    Context context = getApplicationContext();
                    SpUtil.recordQuery(context, title);

                    Intent intent = new Intent(getApplicationContext(), BookListActivity.class);
                    String query = queryUrl.toString();
                    intent.putExtra("query", query);
                    startActivity(intent);

                }

            }
        });
    }

    boolean hasSearchCriteria() {
        return hasText(R.id.txtTitle) || hasText(R.id.txtAuthor) || hasText(R.id.txtPublisher) || hasText(R.id.txtISBN);
    }

    boolean hasText(int id) {
        return getEditText(id).length() > 0;
    }

    String getEditText(int id) {
        return ((EditText) findViewById(id)).getText().toString().trim();
    }

}
