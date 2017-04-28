package com.example.android.feednews;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private ListView news_list;
    private int count = 10;
    private NewsAdapter adapter;
//    private String WORLD = "world";
//    private String LOCAL = "local";
//    private String ECONOMICS = "economics";
//    private String TECH = "technology";
//    private String SPORTS = "sports";
//    private String HEALTH = "health";
//    private String SECTION = WORLD;
//    private String URL = "https://content.guardianapis.com/search?&section=" + SECTION + "&page-size=" + count + "&show-fields=thumbnail,trailText&show-tags=contributor&api-key=6de1a8cd-9a9d-4016-8273-26de99416430";


    private String WORLD = "mundo";
    private String LOCAL = "peru";
    private String ECONOMICS = "economia";
    private String TECH = "ciencia";
    private String SPORTS = "deportes";
    private String HEALTH = "salud";
    private String SECTION = WORLD;
    private String URL = "http://54.70.83.195:3000/lista?categoria="+SECTION;
    private CardView newsCard;
    private CardView readmore;
    private ProgressBar loading;
    private RelativeLayout no_internet;
    ConnectivityManager info;
    NetworkInfo activeNetwork;
    private NavigationView mNavigation;
    public static final String LOG_TAG = NewsLoader.class.getName();
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        mNavigation= (NavigationView) findViewById(R.id.navigation);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        no_internet = (RelativeLayout) findViewById(R.id.no_internet_view);
        news_list = (ListView) findViewById(R.id.list_view);
        readmore = (CardView) findViewById(R.id.readmore);
        readmore.setVisibility(View.VISIBLE);
        loading = (ProgressBar) findViewById(R.id.loading);
        adapter = new NewsAdapter(MainActivity.this, new ArrayList<News>());
        news_list.setAdapter(adapter);
        adapter.clear();
        news_list.setEmptyView(loading);
        newsCard = (CardView) findViewById(R.id.news_card);
        mToggle = new ActionBarDrawerToggle(this , mDrawerLayout , R.string.open , R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        mNavigation.setItemIconTintList(null);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.world_news:
                        SECTION = WORLD;
                        count = 10;
                        if (checkInternet()) {
                            readmore.setVisibility(View.VISIBLE);
                            URL = "http://54.70.83.195:3000/lista?categoria="+SECTION;
                            adapter.clear();
                            getLoaderManager().restartLoader(0, null, MainActivity.this);
                        }
                        return true;
                    case R.id.local_news:
                        SECTION = LOCAL;
                        count = 10;
                        if (checkInternet()) {
                            readmore.setVisibility(View.VISIBLE);
                            URL = "http://54.70.83.195:3000/lista?categoria="+SECTION;
                            adapter.clear();
                            getLoaderManager().restartLoader(0, null, MainActivity.this);
                        }
                        return true;
                    case R.id.economic_news:
                        SECTION = ECONOMICS;
                        count = 10;
                        if (checkInternet()) {
                            readmore.setVisibility(View.VISIBLE);
                            URL = "http://54.70.83.195:3000/lista?categoria="+SECTION;
                            adapter.clear();
                            getLoaderManager().restartLoader(0, null, MainActivity.this);
                        }
                        return true;
                    case R.id.tech_news:
                        SECTION = TECH;
                        count = 10;
                        if (checkInternet()) {
                            readmore.setVisibility(View.VISIBLE);
                            URL = "http://54.70.83.195:3000/lista?categoria="+SECTION;
                            adapter.clear();
                            getLoaderManager().restartLoader(0, null, MainActivity.this);
                        }
                        return true;
                    case R.id.sport_news:
                        SECTION = SPORTS;
                        count = 10;
                        if (checkInternet()) {
                            readmore.setVisibility(View.VISIBLE);
                            URL = "http://54.70.83.195:3000/lista?categoria="+SECTION;
                            adapter.clear();
                            getLoaderManager().restartLoader(0, null, MainActivity.this);
                        }
                        return true;
                    case R.id.health_news:
                        SECTION = HEALTH;
                        count = 10;
                        if (checkInternet()) {
                            readmore.setVisibility(View.VISIBLE);
                            URL = "http://54.70.83.195:3000/lista?categoria="+SECTION;
                            adapter.clear();
                            getLoaderManager().restartLoader(0, null, MainActivity.this);
                        }
                        return true;
                    default:
                        Toast.makeText(MainActivity.this, getString(R.string.sth_wrong), Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });

        checkInternet();

        news_list.setOnItemClickListener(new AdapterView.OnItemClickListener()

                                         {
                                             @Override
                                             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                 News current_news = adapter.getItem(position);
                                                 //Uri bookUri = Uri.parse(current_news.getUrl());

                                                 //Intent news_intent = new Intent(Intent.ACTION_VIEW, bookUri);
                                                 //startActivity(news_intent);
                                             }
                                         }

        );

        readmore.setOnClickListener(new View.OnClickListener()

                                    {
                                        @Override
                                        public void onClick(View v) {
                                            count += 10;
                                            if (count < 41) {
                                                URL = "https://content.guardianapis.com/search?&section=" + SECTION + "&page-size=" + count + "&show-fields=thumbnail,trailText&show-tags=contributor&api-key=6de1a8cd-9a9d-4016-8273-26de99416430";
                                                adapter.clear();
                                                Log.i(LOG_TAG, URL);
                                                getLoaderManager().restartLoader(0, null, MainActivity.this);
                                                Log.d(LOG_TAG, "readmore clicked");
                                            } else {
                                                readmore.setVisibility(GONE);
                                            }
                                        }
                                    }

        );

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        Log.e(LOG_TAG, "onCreateLoader...");
        return new NewsLoader(this, URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        Log.e(LOG_TAG, "onLoaderFinished...");
        adapter.clear();
        if (news != null && !news.isEmpty()) {
            adapter.addAll(news);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        Log.e(LOG_TAG, "onLoaderReset...");
        adapter.clear();
    }

    private boolean checkInternet() {
        info = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = info.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            no_internet.setVisibility(GONE);
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(0, null, this);
            readmore.setVisibility(View.VISIBLE);
            return true;
        } else {
            readmore.setVisibility(View.GONE);
            no_internet.setVisibility(View.VISIBLE);
            loading.setVisibility(GONE);
            return false;
        }
    }
}
