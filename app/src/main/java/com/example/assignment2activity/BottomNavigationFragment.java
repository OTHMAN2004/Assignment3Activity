package com.example.assignment2activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.assignment2activity.databinding.FragmentBottomNavigationBinding;

public class BottomNavigationFragment extends Fragment {

    private static final String ARG_FRAGMENT_NAME = "fragmentName";
    private String fragmentName;

    public BottomNavigationFragment() { }

    public static BottomNavigationFragment newInstance(String fragmentName) {
        BottomNavigationFragment fragment = new BottomNavigationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FRAGMENT_NAME, fragmentName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fragmentName = getArguments().getString(ARG_FRAGMENT_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentBottomNavigationBinding binding =
                FragmentBottomNavigationBinding.inflate(inflater, container, false);
        if (fragmentName != null)
            binding.fragmentName.setText(fragmentName);
        return binding.getRoot();
    }
}
