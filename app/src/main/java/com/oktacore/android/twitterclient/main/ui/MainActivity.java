package com.oktacore.android.twitterclient.main.ui;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.oktacore.android.twitterclient.LoginActivity;
import com.oktacore.android.twitterclient.R;
import com.oktacore.android.twitterclient.images.ui.ImagesFragment;
import com.oktacore.android.twitterclient.main.ui.adapters.MainSectionPagerAdapter;
import com.twitter.sdk.android.Twitter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.container)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        setupAdapter();
    }

    private void setupAdapter() {
        Fragment[] fragments = new Fragment[]{new ImagesFragment(),new ImagesFragment()};
        String [] titles = new String[]{getString(R.string.main_header_images),getString(R.string.main_header_hashtags)};
        MainSectionPagerAdapter adapter = new MainSectionPagerAdapter(
                                                getSupportFragmentManager(),
                                                titles,
                                                fragments);
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        Twitter.logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
