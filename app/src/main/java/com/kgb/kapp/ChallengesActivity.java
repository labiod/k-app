package com.kgb.kapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.kgb.kapp.adapters.YourChallengeAdapter;
import com.kgb.kapp.data.ChallengeDataSource;
import com.kgb.kapp.database.ChallengeDBHelper;
import com.kgb.kapp.database.DBConnection;
import com.kgb.kapp.dialogs.AddChallengeDialog;
import com.kgb.kapp.dialogs.ChallengeChooserDialog;
import com.kgb.kapp.model.Challenge;

public class ChallengesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ChallengeChooserDialog.ChallengeChooserListener,
        AddChallengeDialog.AddChallengeListener {


    private YourChallengeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChallengeChooserDialog dialog = new ChallengeChooserDialog();
                dialog.setListener(ChallengesActivity.this);
                dialog.show(getFragmentManager(), "choose_challenge_fragment");
            }
        });
        ListView listView = findViewById(R.id.challenges_list);
        DBConnection dbConnection = new DBConnection(new ChallengeDBHelper(this));
        mAdapter = new YourChallengeAdapter(dbConnection);
        listView.setAdapter(mAdapter);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Button clear = findViewById(R.id.clear_challenges);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.getDataSource().clearChooseChallenges();
                mAdapter.notifyDataSetChanged();
            }
        });
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
        getMenuInflater().inflate(R.menu.challenges_activity, menu);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_challenge) {
            AddChallengeDialog dialog = new AddChallengeDialog();
            dialog.setListener(ChallengesActivity.this);
            dialog.show(getFragmentManager(), "add_challenge_fragment");
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

    @Override
    public void onItemChoose(ChallengeDataSource source, int itemChoose) {
        mAdapter.addChallenge(source.get(itemChoose));
    }

    @Override
    public void onItemAdded(Challenge newItem) {
    }

    @Override
    public void onCancel() {

    }
}
