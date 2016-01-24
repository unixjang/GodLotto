package org.hyochan.godlotto;

import android.os.Bundle;
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

import org.hyochan.godlotto.utils.android.RollingItem;
import org.hyochan.godlotto.utils.java.NumGenerator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private final String TAG = "MainActivity";

    private Toolbar toolbar;

    private DrawerLayout drawer;
    private NavigationView navView;

    private RollingItem rollingItem1;
    private RollingItem rollingItem2;
    private RollingItem rollingItem3;
    private RollingItem rollingItem4;
    private RollingItem rollingItem5;
    private RollingItem rollingItem6;
    private Button btnRoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);

        // content main
        rollingItem1 = (RollingItem) findViewById(R.id.roll_item_1);
        rollingItem2 = (RollingItem) findViewById(R.id.roll_item_2);
        rollingItem3 = (RollingItem) findViewById(R.id.roll_item_3);
        rollingItem4 = (RollingItem) findViewById(R.id.roll_item_4);
        rollingItem5 = (RollingItem) findViewById(R.id.roll_item_5);
        rollingItem6 = (RollingItem) findViewById(R.id.roll_item_6);
        btnRoll = (Button) findViewById(R.id.btn_roll);
        btnRoll.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_roll:

                int numbers[] = NumGenerator.getInstance().generate();

                rollingItem1.roll(numbers[0]);
                rollingItem2.roll(numbers[1]);
                rollingItem3.roll(numbers[2]);
                rollingItem4.roll(numbers[3]);
                rollingItem5.roll(numbers[4]);
                rollingItem6.roll(numbers[5]);
                break;
        }
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

        if (id == R.id.nav_table) {

        } else if (id == R.id.nav_match) {

        } else if (id == R.id.nav_analyze) {

        } else if (id == R.id.nav_email) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
