package com.example.assignment2activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new EventsFragment();
            case 1: return new ProfileFragment();
            case 2: return new ExploreFragment();
            case 3: return new MapFragment();
            default: return new ProfileFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
