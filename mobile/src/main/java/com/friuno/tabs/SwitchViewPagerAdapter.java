package com.friuno.tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.friuno.fragment.switchboard.BedRoomFragment;
import com.friuno.fragment.switchboard.KitchenFragment;
import com.friuno.fragment.switchboard.LivingRoomFragment;
import com.friuno.fragment.switchboard.MainHallFragment;
import com.friuno.fragment.switchboard.OthersFragment;
import com.friuno.util.Constants;

/**
 * Created by GodwinRoseSamuel on 18-11-2017.
 */
public class SwitchViewPagerAdapter extends FragmentPagerAdapter {

    // Declare the number of ViewPager pages
    final int PAGE_COUNT = 5;
    private String titles[] = new String[]{Constants.LIVING_ROOM, Constants.MAIN_HALL, Constants.BED_ROOM, Constants.KITCHEN, Constants.OTHER_ROOMS};

    public SwitchViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LivingRoomFragment livingRoomFragment = new LivingRoomFragment();
                return livingRoomFragment;
            case 1:
                MainHallFragment mainHallFragment = new MainHallFragment();
                return mainHallFragment;
            case 2:
                BedRoomFragment bedRoomFragment = new BedRoomFragment();
                return bedRoomFragment;
            case 3:
                KitchenFragment kitchenFragment = new KitchenFragment();
                return kitchenFragment;
            case 4:
                OthersFragment othersFragment = new OthersFragment();
                return othersFragment;
        }
        return null;
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}