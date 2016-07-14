package com.makotogo.mobile.rotation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        if (savedInstanceState != null) {
//            mButtonClickCount = (Integer)savedInstanceState.getSerializable(STATE_BUTTON_CLICK_COUNT);
//            mTextToDisplay = (String)savedInstanceState.get(STATE_TEXT_TO_DISPLAY);
//            Log.d(TAG, "Restored instance state: mButtonClickCount => " + mButtonClickCount + ", mTextToDisplay => " + mTextToDisplay);
//        }

        updateTextViewDisplay();

        createButtonDisplay();

        updateTextViewButtonClickCount();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // PRIVATE SCAFFOLDING

    private static final String STATE_TEXT_TO_DISPLAY = MainActivity.class.getName() + ".TEXT_TO_DISPLAY";
    private static final String STATE_BUTTON_CLICK_COUNT = MainActivity.class.getName() + ".BUTTON_CLICK_COUNT";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(STATE_TEXT_TO_DISPLAY, mTextToDisplay);
        outState.putSerializable(STATE_BUTTON_CLICK_COUNT, mButtonClickCount);
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);

        mTextToDisplay = (String)bundle.getSerializable(STATE_TEXT_TO_DISPLAY);
        mButtonClickCount = (Integer)bundle.getSerializable(STATE_BUTTON_CLICK_COUNT);

    }

    // BEGIN PRIVATE STATE VARIABLES
    private String mTextToDisplay = "FYI: initial value";

    private Integer mButtonClickCount = -1;
    // END PRIVATE STATE VARIABLES


    public void onResume() {
        super.onResume();

        updateTextViewDisplay();

        updateTextViewButtonClickCount();
    }


    private void updateTextViewDisplay() {
        TextView textViewDisplay = (TextView)safeFindViewById(R.id.textView_display);

        textViewDisplay.setText(mTextToDisplay);
    }

    private void createButtonDisplay() {
        Button displayButton = (Button)safeFindViewById(R.id.button_display);

        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Grab the contents of the EditText and display it in the TextView
                EditText inputEditText = (EditText)safeFindViewById(R.id.editText_input_text);

                mTextToDisplay = inputEditText.getText().toString();

                TextView displayTextView = (TextView)safeFindViewById(R.id.textView_display);
                displayTextView.setText(mTextToDisplay);

                mButtonClickCount++;
                TextView buttonCountTextView = (TextView)safeFindViewById(R.id.textView_button_click_count);
                buttonCountTextView.setText("Button click count: " + mButtonClickCount);

                // Blank out the text in the EditText field, we don't need it anymore
                inputEditText.setText("");
            }
        });
    }

    private void updateTextViewButtonClickCount() {
        TextView textView = (TextView)safeFindViewById(R.id.textView_button_click_count);

        textView.setText("Button click count: " + Integer.toString(mButtonClickCount));
    }


    /**
     * Gets rid of those pesky Lint messages. Oh, and it's probably a good
     * idea to do the null check (there's that).
     *
     * @param resourceId
     * @return
     */
    private View safeFindViewById(int resourceId) {
        final String METHOD = "safeFindViewById(" + resourceId + ")";
        View ret = findViewById(resourceId);

        if (ret == null) {
            Log.e(TAG, METHOD+"Resource from findResourceById() is null!");
        }

        return ret;
    }

}
