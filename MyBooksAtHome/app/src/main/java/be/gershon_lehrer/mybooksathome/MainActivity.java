//TODO kijk naar style zoals bv style="?android:listSeparatorTextViewStyle"
//TODO probeer generic querymethode te maken, gebruik het voorbeeld van de website
//TODO kijk of singlefragmentactivity ergens gebruikt kan worden
//TODO dit kan mogelijk handy zijn  Log.v(TAG,""+getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container).toString());


package be.gershon_lehrer.mybooksathome;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
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

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.io.IOException;

import be.gershon_lehrer.mybooksathome.controller.AddNewBookActivity;
import be.gershon_lehrer.mybooksathome.controller.BarCodeScannerActivity;
import be.gershon_lehrer.mybooksathome.controller.DialogSearchHelper;
import be.gershon_lehrer.mybooksathome.controller.GoogleApiAdapter;
import be.gershon_lehrer.mybooksathome.controller.ShowBookSearchDialogActivity;
import be.gershon_lehrer.mybooksathome.controller.fragments.BookSearchFragment;
import be.gershon_lehrer.mybooksathome.controller.fragments.BooksListFragment;
import be.gershon_lehrer.mybooksathome.controller.StartUpFragment;
import be.gershon_lehrer.mybooksathome.model.SearchMethodGoogleApi;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    private BooksListFragment mBooksOverviewFragment;
    private BookSearchFragment mBookSearchFragment;
    private DialogSearchHelper mDialogSearchHelper;
    private final static int SHOWBOOKSEARCH_SUBACTIVITY=5;

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
        Log.v("oncreate", fab == null ? "null" : fab.toString());


        mBooksOverviewFragment = new BooksListFragment();
        mBookSearchFragment=new BookSearchFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, mBooksOverviewFragment)
                .addToBackStack(null)
                .commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//mijn code vanaf hier:
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

//kan natuurlijk ook binnen een eventlistener worden geimplementeerd


        FragmentManager manager=getSupportFragmentManager();


        Fragment fragment=manager.findFragmentById(R.id.fragment_container);
           //controleert of FragmentManager mBooksOverviewFragment reeds geplaatst heeft, als niet, plaatst het in fragment_container layout
        if (fragment==null){
            fragment=new StartUpFragment();
            manager.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
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

        if (id == R.id.action_main_menu_search) {
            Intent intent=new Intent(this, ShowBookSearchDialogActivity.class);
            startActivityForResult(intent,SHOWBOOKSEARCH_SUBACTIVITY);
            return true;
        }
        if (id==R.id.action_main_menu_item_add_to_db)
        {
            Intent intent=new Intent(this, AddNewBookActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SHOWBOOKSEARCH_SUBACTIVITY:
                if (data!=null && resultCode== Activity.RESULT_OK);
            {
                String teRunnenQuery="";
                if (data!=null) {
                    Bundle resultData = data.getExtras();
                     teRunnenQuery = resultData.getString("createquery");
                    App.getInstance().clearBooks();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, mBooksOverviewFragment)
                            .addToBackStack(null)
                            .commit();

                try {
                    mBooksOverviewFragment.loadGoogleBooks(teRunnenQuery);
                } catch (IOException e) {
                    e.printStackTrace();
                }}
            }
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_all_local_books) {
            App.getInstance().clearBooks();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, mBooksOverviewFragment)
                    .addToBackStack(null)
                    .commit();

            mBooksOverviewFragment.loadAllOwnedBooksDb();
            // mBooksOverviewFragment.loadOwnedBooks
            // swap() --> fragmentManager
        }  else if (id == R.id.nav_search_fragment) {
            App.getInstance().clearBooks();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, mBookSearchFragment)
                    .addToBackStack(null)
                    .commit();


        } else if (id == R.id.nav_share) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_container, new BarCodeScannerActivity())
//                    .addToBackStack(null)
//                    .commit();
            Intent intent = new Intent(this, BarCodeScannerActivity.class);
            startActivityForResult(intent,0);

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Realm realm=Realm.getDefaultInstance();
        if (realm != null) {
            realm.close();
        }
    }


}
