package android.magician.com.myazkar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Magician on 2/9/2018.
 *
 */
/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class AzkarPagerAdapter extends FragmentPagerAdapter {
    private static final int NUMBER_OF_PAGES=7;
    public AzkarPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a AzkarPagerFragment (defined as a static inner class below).
        return AzkarPagerFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return NUMBER_OF_PAGES;
    }
}
