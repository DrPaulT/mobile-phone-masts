package uk.co.drdv.bink.mobilephonemasts.ui;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import uk.co.drdv.bink.mobilephonemasts.ui.rents.RentsFragment;

public class TabAdapter extends FragmentPagerAdapter {

    private static final String TITLE_RENTS = "Rents";
    private static final String TITLE_TENANTS = "Tenants";
    private static final String TITLE_DATES = "Dates";

    public TabAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RentsFragment();
            case 1:
                return TenantsFragment.newInstance("", "");
            case 2:
                return DatesFragment.newInstance("", "");
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return TITLE_RENTS;
            case 1:
                return TITLE_TENANTS;
            case 2:
                return TITLE_DATES;
        }
        return null;
    }
}
