package uk.co.drdv.bink.mobilephonemasts.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import uk.co.drdv.bink.mobilephonemasts.R;
import uk.co.drdv.bink.mobilephonemasts.database.Mast;
import uk.co.drdv.bink.mobilephonemasts.database.MastRepository;
import uk.co.drdv.bink.mobilephonemasts.database.MastViewModel;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
