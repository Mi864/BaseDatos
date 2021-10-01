package com.example.basedatos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ScreenSlidePagerAdapter screenSlidePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar    = findViewById(R.id.toolbar);
        tabLayout  = findViewById(R.id.tabs);
        viewPager2 = findViewById(R.id.viewPager2);

        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        screenSlidePagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager2.setAdapter(screenSlidePagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(""+(position+1))).attach();

    }

    private static class ScreenSlidePagerAdapter extends FragmentStateAdapter {


        public ScreenSlidePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {

            ArrayList<Fragment> fragments = new ArrayList<>();

            fragments.add(new FragmentFormulario(1));
            fragments.add(new FragmentFormulario(2));

            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}