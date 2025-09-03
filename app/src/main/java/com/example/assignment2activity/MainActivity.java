package com.example.assignment2activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.assignment2activity.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize views
        drawerLayout = binding.drawerLayout;
        navigationView = binding.navigationView;
        bottomNavigationView = binding.bottomNavigation;
        tabLayout = binding.tabLayout;
        viewPager = binding.viewPager;
        fragmentContainer = binding.fragmentContainer;

        // Setup toolbar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        // Setup Drawer Navigation
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            drawerLayout.closeDrawer(GravityCompat.START);

            if (id == R.id.profile_nav) {
                bottomNavigationView.setSelectedItemId(R.id.profileItem);
            } else if (id == R.id.calendar_nav) {
                bottomNavigationView.setSelectedItemId(R.id.eventsItem);
            } else if (id == R.id.mark_nav) {
                bottomNavigationView.setSelectedItemId(R.id.mapItem);
            } else if (id == R.id.massege_nav) {
                bottomNavigationView.setSelectedItemId(R.id.exploreItem);
            }

            return true;
        });

        // Setup Bottom Navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.profileItem) {
                showMainTabs();
                return true;
            } else if (id == R.id.eventsItem) {
                showFragment(new EventsFragment(), "Events Fragment");
                return true;
            } else if (id == R.id.mapItem) {
                showFragment(new MapFragment(), "Map Fragment");
                return true;
            } else if (id == R.id.exploreItem) {
                showFragment(new ExploreFragment(), "Explore Fragment");
                return true;
            }

            return true;
        });

        // Setup ViewPager2 and TabLayout
        setupViewPager();

        // Show main tabs by default
        showMainTabs();
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Events Fragment");
                    break;
                case 1:
                    tab.setText("Explore Fragment");
                    break;
                case 2:
                    tab.setText("Map Fragment");
                    break;
                case 3:
                    tab.setText("Profile Fragment");
            }
        }).attach();
    }

    private void showMainTabs() {
        fragmentContainer.setVisibility(View.GONE);
        tabLayout.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    private void showFragment(Fragment fragment, String tag) {
        fragmentContainer.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.GONE);
        viewPager.setVisibility(View.GONE);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, tag)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (fragmentContainer.getVisibility() == View.VISIBLE) {
            bottomNavigationView.setSelectedItemId(R.id.profileItem);
        } else {
            super.onBackPressed();
        }
    }
}