package com.assignment.activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.R;
import com.assignment.adapters.FoodAdapter;
import com.assignment.entities.Chef;
import com.assignment.entities.FoodItem;
import com.assignment.utils.CommonListeners;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements CommonListeners.AmountUpdateListener {
    private TextView dateLabel, amountLabel, locationLabel;
    private Calendar calendar;
    private SimpleDateFormat sdf;
    private FoodAdapter foodAdapter;
    private RecyclerView recyclerFoodList;
    private ArrayList<FoodItem> lunchItems, dinnerItems;
    private LinearLayout lunchTabLayout, dinnerTabLayout;
    private DrawerLayout drawerLayout;
    private double amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        populateFoodItems();
    }

    // populate the lunch and dinner food items
    private void populateFoodItems() {
        // add food items to lunch and dinner
        lunchItems.add(new FoodItem(0, "Paneer Butter Masala", "Butter Paneer Masala with Raita " +
                "and Butter Naan", 0, 150, true, "Lunch",
                new int[]{R.mipmap.lunch_4, R.mipmap.lunch_5, R.mipmap.lunch_6}
                , new Chef(0, "Singh Saab", 4.5f, R.mipmap.ic_launcher)));

        dinnerItems.add(new FoodItem(0, "Chhole Bhature", "Chhole Bhature with Raita and Chutni"
                , 0, 90, true, "Dinner",
                new int[]{R.mipmap.lunch_1, R.mipmap.lunch_2, R.mipmap.lunch_3}
                , new Chef(0, "Komal Razvi", 2.75f, R.mipmap.ic_chef)));

        lunchItems.add(new FoodItem(0, "Chicken Butter Masala", "Butter Chicken Masala with Raita " +
                "and Butter Naan", 0, 250, false, "Lunch",
                new int[]{R.mipmap.dinner_1, R.mipmap.dinner_2, R.mipmap.dinner_3}
                , new Chef(0, "Arjun Kumar", 3f, R.mipmap.ic_chef)));

        dinnerItems.add(new FoodItem(0, "Mutton Seekh Kebab", "Mutton Seekh Kebab with Amritsari Raita " +
                "and Butter Naan", 0, 375, false, "Dinner",
                new int[]{R.mipmap.dinner_4, R.mipmap.dinner_5, R.mipmap.dinner_6}
                , new Chef(0, "Anuj Gupta", 1.25f, R.mipmap.ic_launcher)));

        // initialise and set the adapter
        foodAdapter = new FoodAdapter(lunchItems, MainActivity.this, R.layout.food_item_row);
        foodAdapter.setAmountUpdateListener(this);
        recyclerFoodList.setAdapter(foodAdapter);
    }

    // Initialise all the UI components
    private void initComponents() {
        dateLabel = (TextView) findViewById(R.id.date_label);
        amountLabel = (TextView) findViewById(R.id.amount_label);
        locationLabel = (TextView) findViewById(R.id.location_label);

        lunchTabLayout = (LinearLayout) findViewById(R.id.lunch_tab);
        dinnerTabLayout = (LinearLayout) findViewById(R.id.dinner_tab);

        recyclerFoodList = (RecyclerView) findViewById(R.id.food_list);
        recyclerFoodList.setLayoutManager(new LinearLayoutManager(this));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

        calendar = Calendar.getInstance();
        sdf = new SimpleDateFormat(getString(R.string.date_format), Locale.getDefault());
        lunchItems = new ArrayList<>();
        dinnerItems = new ArrayList<>();

        dateLabel.setText(sdf.format(calendar.getTime()));
        locationLabel.setText(getString(R.string.current_location));
    }

    /**
     * Perform onClick action of the UI components
     *
     * @param view on which click action is performed
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu:
                openOrCloseDrawer();
                break;
            case R.id.cart:
                Toast.makeText(this, "Cart clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.prev_date:
                updateDate(false);
                break;
            case R.id.next_date:
                updateDate(true);
                break;
            case R.id.location_icon:
                updateLocation();
                break;
            case R.id.lunch_tab:
                toggleView(lunchItems, lunchTabLayout, dinnerTabLayout);
                break;
            case R.id.dinner_tab:
                toggleView(dinnerItems, dinnerTabLayout, lunchTabLayout);
                break;
        }
    }

    // this method attempts to open or close the left navigation drawer
    private void openOrCloseDrawer() {
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            drawerLayout.openDrawer(Gravity.LEFT);
        }

    }

    /**
     * It will update the date based on the action i.e. increment or decrement
     *
     * @param increment if forward button is tapped
     */
    private void updateDate(boolean increment) {
        if (increment) {
            calendar.add(Calendar.DATE, 1);
        } else {
            calendar.add(Calendar.DATE, -1);
        }

        dateLabel.setText(sdf.format(calendar.getTime()));
    }

    /**
     * this will toggle the view between lunch time and dinner time based on the respective
     * tab click
     *
     * @param foodItems is the food item array
     * @param focused   is the focused layout
     * @param unFocused is the unFocused layout
     */
    private void toggleView(ArrayList<FoodItem> foodItems, LinearLayout focused, LinearLayout unFocused) {
        focused.setBackgroundResource(R.drawable.focussed_background);
        unFocused.setBackgroundResource(R.color.white);
        foodAdapter.setFoodItems(foodItems);
    }

    // this will update the total amount
    @Override
    public void updateAmount() {
        amount = 0;

        // for lunch items
        for (FoodItem item : lunchItems) {
            amount += item.getQuantity() * item.getRate();
        }

        // for dinner items
        for (FoodItem item : dinnerItems) {
            amount += item.getQuantity() * item.getRate();
        }

        amountLabel.setText(String.valueOf(amount));
    }

    // this will update the location text field
    private void updateLocation() {
        locationLabel.setText(getString(R.string.delhi));
    }
}
