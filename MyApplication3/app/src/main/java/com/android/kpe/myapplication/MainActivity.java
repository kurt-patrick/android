package com.android.kpe.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        boolean handled = false;

        int id = item.getItemId();
        switch (id) {
            case R.id.action_act2:
                mnuAct2_onClick(item);
                handled = true;
                break;
            case R.id.action_act3:
                mnuAct3_onClick(item);
                handled = true;
                break;
            case R.id.action_other:
                mnuOther_onClick(item);
                handled = true;
                break;
            case R.id.action_exit:
                mnuExit_onClick(item);
                handled = true;
                break;
            default:
                handled = super.onOptionsItemSelected(item);
        }

        return handled;
    }

    private void mnuAct2_onClick(MenuItem item)
    {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    private void mnuAct3_onClick(MenuItem item)
    {
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }

    private void mnuOther_onClick(MenuItem item)
    {
        Intent intent = new Intent(this, OtherActivity.class);
        startActivity(intent);
    }

    private void mnuExit_onClick(MenuItem item)
    {
        finish();
    }

}
