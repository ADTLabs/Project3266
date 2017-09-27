package andlab.com.ecomtest.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
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
import android.widget.ProgressBar;

import andlab.com.ecomtest.R;
import andlab.com.ecomtest.ui.fragment.AccountFragment;
import andlab.com.ecomtest.ui.fragment.HomeFragment;
import andlab.com.ecomtest.ui.PageType;
import andlab.com.ecomtest.ui.fragment.ProductListFragment;

public class BaseDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getHeaderView(0).findViewById(R.id.header).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new AccountFragment(), PageType.LIST);
                onBackPressed();
            }
        });
        startFragment(new HomeFragment(), PageType.OPEN);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager mFragmentManager = getFragmentManager();
            if (mFragmentManager.getBackStackEntryCount() > 0)
                mFragmentManager.popBackStackImmediate();
            else super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_search :
                Intent in=new Intent(BaseDrawerActivity.this, SearchActivity.class);
                in.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(in);
                break;

            case R.id.action_cart :
                Intent inq=new Intent(BaseDrawerActivity.this, CartActivity.class);
                inq.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(inq);
                break;

            case android.R.id.home:finish();
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

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
    public void startFragment(Fragment fragment, PageType type)
    {
        getFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            //addToBackStack(null)
                .add(R.id.frame_viewer, fragment).commit();
    }

    public void replaceFragment(Fragment fragment, PageType type)
    {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_viewer, fragment) .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void progressVisibility(boolean b) {
       ProgressBar pd= (ProgressBar) findViewById(R.id.progressBar);
        if(b)
            pd.setVisibility(View.VISIBLE);
        else
            pd.setVisibility(View.GONE);
    }


}
