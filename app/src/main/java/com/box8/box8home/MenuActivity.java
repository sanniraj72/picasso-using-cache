package com.box8.box8home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener,
        FusionBoxMenuFragment.OnFragmentInteractionListener,
        CurriesMenuFragment.OnFragmentInteractionListener,
        BiryaniMenuFragment.OnFragmentInteractionListener,
        WrapsMenuFragment.OnFragmentInteractionListener,
        IceCreamMenuFragment.OnFragmentInteractionListener {

    private String categories = "[\"Fusion Box\"," +
            "\"Curries\"," +
            "\"Biryani\"," +
            "\"Wraps\"," +
            "\"Ice Cream\"]";

    private String category = "{" +
            "\"Fusion Box\": [" +
            "\"Dal Makhni Rice Box\"," +
            "\"Chole Chawal Box\"," +
            "\"Rajma Chawal Box\"," +
            "\"Grilled Tikki Box\"," +
            "\"Paneer Masala Box\"" +
            "]," +
            "" +
            "\"Curries\": [" +
            "\"Basmati Rice\"," +
            "\"Tawa Paratha\"," +
            "\"Kadhai Paneer\"," +
            "\"Raita\"," +
            "\"Butter Chicken\"" +
            "]," +
            "\"Biryani\": [" +
            "\"Sahi Panner Biryani\"," +
            "\"Firangi Subz Biryani\"," +
            "\"Chicken Tikka Biryani\"," +
            "\"Murg Dum Biryani\"" +
            "]," +
            "\"Wraps\": [" +
            "\"Paneer Wrap\"," +
            "\"Chicken Wrap\"," +
            "\"Mayo Wrap\"," +
            "\"Tikki Wrap\"," +
            "\"Patty Wrap\"" +
            "]," +
            "\"Ice Cream\": [" +
            "\"Tender CocoNut\"," +
            "\"Sheer Khurma\"" +
            "]" +
            "}";

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private static final int FUSION_BOX_TAB_INDEX = 0;
    private static final int CURRIES_TAB_INDEX = 1;
    private static final int BIRYANI_TAB_INDEX = 2;
    private static final int WRAPS_TAB_INDEX = 3;
    private static final int ICE_CREAM_TAB_INDEX = 4;

    private ArrayList<String> fussionBoxList;
    private ArrayList<String> curriesList;
    private ArrayList<String> biryaniList;
    private ArrayList<String> wrapList;
    private ArrayList<String> iceCreamList;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);

        // Show Back button
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabs);

        prepareData();
        setUpTab();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setUpTab() {
        FusionBoxMenuFragment fusionBoxMenuFragment = FusionBoxMenuFragment.newInstance(fussionBoxList);
        CurriesMenuFragment curriesMenuFragment = CurriesMenuFragment.newInstance(curriesList);
        BiryaniMenuFragment biryaniMenuFragment = BiryaniMenuFragment.newInstance(biryaniList);
        WrapsMenuFragment wrapsMenuFragment = WrapsMenuFragment.newInstance(wrapList);
        IceCreamMenuFragment iceCreamMenuFragment = IceCreamMenuFragment.newInstance(iceCreamList);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(fusionBoxMenuFragment);
        fragmentList.add(curriesMenuFragment);
        fragmentList.add(biryaniMenuFragment);
        fragmentList.add(wrapsMenuFragment);
        fragmentList.add(iceCreamMenuFragment);

        MenuPageAdapter pageAdapter = new MenuPageAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(pageAdapter);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setCurrentItem(position);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        TabLayout.Tab fussionTab = tabLayout.getTabAt(FUSION_BOX_TAB_INDEX);
        if (fussionTab != null) {
            fussionTab.setCustomView(R.layout.fussion_tab_layout);
        }
        TabLayout.Tab curriesTab = tabLayout.getTabAt(CURRIES_TAB_INDEX);
        if (curriesTab != null) {
            curriesTab.setCustomView(R.layout.curries_tab_layout);
        }
        TabLayout.Tab biryaniTab = tabLayout.getTabAt(BIRYANI_TAB_INDEX);
        if (biryaniTab != null) {
            biryaniTab.setCustomView(R.layout.biryani_tab_layout);
        }
        TabLayout.Tab wrapTab = tabLayout.getTabAt(WRAPS_TAB_INDEX);
        if (wrapTab != null) {
            wrapTab.setCustomView(R.layout.wrap_tab_layout);
        }
        TabLayout.Tab iceCreamTab = tabLayout.getTabAt(ICE_CREAM_TAB_INDEX);
        if (iceCreamTab != null) {
            iceCreamTab.setCustomView(R.layout.ice_cream_tab_layout);
        }
        tabLayout.addOnTabSelectedListener(this);
    }

    private void prepareData() {
        try {
            JSONArray categoryArray = new JSONArray(categories);
            JSONObject menuObject = new JSONObject(category);
            for (int i = 0; i < categoryArray.length(); i++) {
                String item = categoryArray.getString(i);
                JSONArray productArray = menuObject.getJSONArray(item);
                switch (i) {
                    case 0:
                        fussionBoxList = new ArrayList<>();
                        for (int j = 0; j < productArray.length(); j++) {
                            fussionBoxList.add(productArray.getString(j));
                        }
                        break;
                    case 1:
                        curriesList = new ArrayList<>();
                        for (int j = 0; j < productArray.length(); j++) {
                            curriesList.add(productArray.getString(j));
                        }
                        break;
                    case 2:
                        biryaniList = new ArrayList<>();
                        for (int j = 0; j < productArray.length(); j++) {
                            biryaniList.add(productArray.getString(j));
                        }
                        break;
                    case 3:
                        wrapList = new ArrayList<>();
                        for (int j = 0; j < productArray.length(); j++) {
                            wrapList.add(productArray.getString(j));
                        }
                        break;
                    case 4:
                        iceCreamList = new ArrayList<>();
                        for (int j = 0; j < productArray.length(); j++) {
                            iceCreamList.add(productArray.getString(j));
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when a tab enters the selected state.
     *
     * @param tab The tab that was selected
     */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    /**
     * Called when a tab exits the selected state.
     *
     * @param tab The tab that was unselected
     */
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    /**
     * Called when a tab that is already selected is chosen again by the user. Some applications
     * may use this action to return to the top level of a category.
     *
     * @param tab The tab that was reselected.
     */
    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
