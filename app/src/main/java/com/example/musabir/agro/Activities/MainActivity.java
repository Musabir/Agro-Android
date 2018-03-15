package com.example.musabir.agro.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.musabir.agro.Fragment.AboutUs;
import com.example.musabir.agro.Fragment.HesablaFragment;
import com.example.musabir.agro.Fragment.MainScreenFragment;
import com.example.musabir.agro.Fragment.MayalanmaFragment;
import com.example.musabir.agro.Fragment.ToxumElaveEt;
import com.example.musabir.agro.Fragment.XidmetElaveEtFragment;
import com.example.musabir.agro.Fragment.XidmetGosterenSexsler;
import com.example.musabir.agro.Mapper.SuniMayalanmaMapper;
import com.example.musabir.agro.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Fragment fragment = null;
    private static FragmentManager fragmentManager;
    public static String fragmentTags = "";
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displayView(new MainScreenFragment(),"MainScreenFragment");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }       else if(getFragmentManager().getBackStackEntryCount()>1){

        super.onBackPressed();
        }

         else{

            if (doubleBackToExitPressedOnce) {
                finish();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 1000);        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.xidmet_gosteren_sexsler) {
            displayView(new XidmetGosterenSexsler(),"XidmetGosterenSexsler");
        } else if (id == R.id.hesabla) {
            displayView(new HesablaFragment(),"HesablaFragment");

        } else if (id == R.id.toxumlar) {
            displayView(new MainScreenFragment(),"MainScreenFragment");

        } else if (id == R.id.mayalanma) {
            displayView(new MayalanmaFragment(),"MayalanmaFragment");

        } else if (id == R.id.xidmet_teklif_Et) {
            displayView(new XidmetElaveEtFragment(),"XidmetElaveEtFragment");

        } else if (id == R.id.toxum_teklif_et) {
            displayView(new ToxumElaveEt(),"ToxumElaveEt");

        }
        else if (id == R.id.haqqimizda) {
            displayView(new AboutUs(),"AboutUs");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void displayView(Fragment fragment, String fragmentTags) {


        if (fragment != null) {
            fragmentManager = getFragmentManager();
            Bundle bundle = new Bundle();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.addToBackStack(fragmentTags);
            fragmentTransaction.replace(R.id.content_frame, fragment, fragmentTags);

            fragment.setArguments(bundle);
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();

        }
    }
}
