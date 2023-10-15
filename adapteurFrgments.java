package com.example.interventions;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class adapteurFrgments extends FragmentStateAdapter {

    public adapteurFrgments(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return  new setailsFragment();

            case 1: return  new fichiersFragment();

            case 2: return  new signatureFragment();
            default: return new setailsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
