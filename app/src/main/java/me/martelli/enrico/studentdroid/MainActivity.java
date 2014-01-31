package me.martelli.enrico.studentdroid;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String CURRENT_FRAGMENT = "current_fragment";

    // Within which the entire activity is enclosed
    DrawerLayout mDrawerLayout;

    // ListView represents Navigation Drawer
    ListView mDrawerList;

    // ActionBarDrawerToggle indicates the presence of Navigation Drawer in the action bar
    ActionBarDrawerToggle mDrawerToggle;

    // Title of the action bar
    String mTitle = "";

    String[] mPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPages = new String[] {
                getString(R.string.title_section1),
                getString(R.string.title_section2),
                getString(R.string.title_section3),
                getString(R.string.title_section4),
        };

        mTitle = mPages[0];

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.drawer_list);

        // Getting reference to the ActionBarDrawerToggle
        mDrawerToggle = new ActionBarDrawerToggle( this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close){

            /** Called when drawer is closed */
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            /** Called when a drawer is opened */
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(getString(R.string.app_name));
                invalidateOptionsMenu();
            }
        };

        // Setting DrawerToggle on DrawerLayout
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Setting the adapter on mDrawerList
        mDrawerList.setAdapter(new NavigationDrawerArrayAdapter(
                getBaseContext(),
                R.layout.drawer_list_item,
                mPages
        ));

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // Enabling Home button
        getActionBar().setHomeButtonEnabled(true);

        // Enabling Up navigation
        getActionBar().setDisplayHomeAsUpEnabled(true);

        // Setting item click listener for the listview mDrawerList
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {

                mTitle = mPages[position];

                Fragment fragment = null;

                switch(position) {
                    case 0:
                        fragment = new DayViewFragment();
                        break;
                    case 1:
                        fragment = new WeekViewFragment();
                        break;
                    case 2:
                        fragment = new HomeworkExamsFragment();
                        break;
                    case 3:
                        fragment = new VotesFragment();
                        break;
                }

                getFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();

                ((NavigationDrawerArrayAdapter)parent.getAdapter()).selectItem(position);

                // Closing the drawer
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        });

        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new DayViewFragment())
                    .commit();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    /** Handling the touch event of app icon */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Toast.makeText(this, "Show settings", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

        if(drawerOpen) {
            menu.clear();
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        int position = savedInstanceState.getInt(CURRENT_FRAGMENT);

        mTitle = mPages[position];

        Fragment fragment = null;

        switch(position) {
            case 0:
                fragment = new DayViewFragment();
                break;
            case 1:
                fragment = new WeekViewFragment();
                break;
            case 2:
                fragment = new HomeworkExamsFragment();
                break;
            case 3:
                fragment = new VotesFragment();
                break;
        }

        getFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

        ((NavigationDrawerArrayAdapter)mDrawerList.getAdapter()).selectItem(position);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_FRAGMENT, ((NavigationDrawerArrayAdapter)mDrawerList.getAdapter()).selectedItem);

        super.onSaveInstanceState(outState);
    }
}
