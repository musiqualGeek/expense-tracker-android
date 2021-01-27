package com.ptkzonelabs.rupeefy.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.navigation.NavigationView;
import com.ptkzonelabs.rupeefy.Fragment.ContactUsFragment;
import com.ptkzonelabs.rupeefy.Fragment.CurrencyFragment;
import com.ptkzonelabs.rupeefy.Fragment.HistoryFragment;
import com.ptkzonelabs.rupeefy.Fragment.LogoutFragment;
import com.ptkzonelabs.rupeefy.Fragment.PrivacyFragment;
import com.ptkzonelabs.rupeefy.Fragment.SettingsFragment;
import com.ptkzonelabs.rupeefy.Fragment.SupportFragment;
import com.ptkzonelabs.rupeefy.Model.CategoryModel;
import com.ptkzonelabs.rupeefy.Model.DataBaseHelper;
import com.ptkzonelabs.rupeefy.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;

    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList<PieEntry> pieEntries;
    ArrayList pieEntryLabels;

    ArrayAdapter categoryArrayAdapter;
    ListView lv_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(DashboardActivity.this);
        List<CategoryModel> everyone = dataBaseHelper.getEveryone();
        categoryArrayAdapter = new ArrayAdapter<CategoryModel>(DashboardActivity.this, android.R.layout.simple_list_item_1, everyone);

        /*  Rent Activity  */
        ImageView rent = findViewById(R.id.item_icon_1);
        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, RentActivity.class);
                startActivity(intent);
            }
        });

        /*  Travel Activity  */
        ImageView travel = findViewById(R.id.item_icon_2);
        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, TravelActivity.class);
                startActivity(intent);
            }
        });

        /*  Shopping Activity  */
        ImageView shopping = findViewById(R.id.item_icon_3);
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, ShoppingActivity.class);
                startActivity(intent);
            }
        });

        /*  Repair Activity  */
        ImageView repair = findViewById(R.id.item_icon_4);
        repair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, RepairActivity.class);
                startActivity(intent);
            }
        });

        /*  Fuel Activity  */
        ImageView fuel = findViewById(R.id.item_icon_5);
        fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, FuelActivity.class);
                startActivity(intent);
            }
        });

        /*  Food Activity  */
        ImageView food = findViewById(R.id.item_icon_6);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, FoodActivity.class);
                startActivity(intent);
            }
        });

        /*  Health Activity  */
        ImageView health = findViewById(R.id.item_icon_7);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, HealthActivity.class);
                startActivity(intent);
            }
        });

        /*  Sports Activity  */
        ImageView sports = findViewById(R.id.item_icon_8);
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, SportsActivity.class);
                startActivity(intent);
            }
        });

        /*  Pie Chart Properties  */
        pieChart = findViewById(R.id.pieChart);
        pieChart.getDescription().setEnabled(false);
        Legend legend = pieChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        getEntries();
        pieDataSet = new PieDataSet(pieEntries, "");
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(10f);
        pieDataSet.setSliceSpace(5f);

        /*  Navigation Drawer & Toolbar Elements  */
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                Fragment fragment = null;
                switch (id) {
                    case R.id.history:
                        fragment = new HistoryFragment();
                        loadingFragment(fragment);
                        break;
                    case R.id.currency:
                        fragment = new CurrencyFragment();
                        loadingFragment(fragment);
                        break;
                    case R.id.privacy:
                        fragment = new PrivacyFragment();
                        loadingFragment(fragment);
                        break;
                    case R.id.settings:
                        fragment = new SettingsFragment();
                        loadingFragment(fragment);
                        break;
                    case R.id.support:
                        fragment = new SupportFragment();
                        loadingFragment(fragment);
                        break;
                    case R.id.contact_us:
                        fragment = new ContactUsFragment();
                        loadingFragment(fragment);
                        break;
                    case R.id.logout:
                        fragment = new LogoutFragment();
                        loadingFragment(fragment);
                        break;
                    default:
                        return true;

                }
                return false;
            }
        });
    }

    private void getEntries() {
        pieEntries = new ArrayList<PieEntry>();
        pieEntries.add(new PieEntry(10f, "Food"));
        pieEntries.add(new PieEntry(4f, "Rent"));
        pieEntries.add(new PieEntry(6f, "Fuel"));
        pieEntries.add(new PieEntry(8f, "Travel"));
        pieEntries.add(new PieEntry(7f, "Health"));
    }

    private void loadingFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);
    }
}