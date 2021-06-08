package com.nathan.digipizza;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class RestaurantPagerActivity extends AppCompatActivity {

    private ViewPager2 mViewPager;
    private Lifecycle mLifecycle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_view_pager);

        mViewPager = (ViewPager2) findViewById(R.id.activity_view_pager);
        mLifecycle = getLifecycle();

        FragmentManager fragmentManager = getSupportFragmentManager();

        RestaurantPagerAdapter pagerAdapter = new RestaurantPagerAdapter(fragmentManager, mLifecycle);

        mViewPager.setAdapter(pagerAdapter);


        //TAB LAYOUT CODE -->CREATE TAB LAYOUT & CONNECT TO VIEWPAGER2

        final TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.restaurant_tab));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.pizza_tab));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.pasta_tab));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //The setCurrentItem() method sets the currently selected page
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //The registerOnPageChangeCallback() method adds a callback that is invoked whenever the
        //page changes or is incrementally scrolled
        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


    }





    private class RestaurantPagerAdapter extends FragmentStateAdapter {

        public RestaurantPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch(position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new PizzaListFragment();
                case 2:
                    return new PastaListFragment();
            }
            return null;
        }

        @Override
        public int getItemCount() {
            return 3;
        }


    }
}
