package com.assignment.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.assignment.R;
import com.assignment.entities.FoodItem;
import com.assignment.utils.CommonListeners;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by muddassiriqbal on 24/07/15.
 * This adapter is for the recycler view used to show food items
 */
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    private ArrayList<FoodItem> foodItems = new ArrayList<>();
    private Activity activity;
    private int rowLayout;
    private CommonListeners.AmountUpdateListener amountUpdateListener;

    /**
     * the cunstructor to initialise the recycler view adapter
     *
     * @param foodItems is array of food items
     * @param activity  is the activity to which it is to be attached
     * @param rowLayout is the layout which is to be inflated
     */
    public FoodAdapter(ArrayList<FoodItem> foodItems, Activity activity, int rowLayout) {
        this.foodItems = foodItems;
        this.activity = activity;
        this.rowLayout = rowLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        FoodItem foodItem = foodItems.get(position);

        // set the chef image
        Picasso.with(activity)
                .load(foodItem.getChef().getChefImage())
                .fit()
                .placeholder(R.mipmap.ic_chef)
                .into(viewHolder.chefImage);

        // set all the food images
        Picasso.with(activity)
                .load(foodItem.getImages()[0])
                .fit()
                .into(viewHolder.imageOne);
        Picasso.with(activity)
                .load(foodItem.getImages()[1])
                .fit()
                .into(viewHolder.imageTwo);
        Picasso.with(activity)
                .load(foodItem.getImages()[2])
                .fit()
                .into(viewHolder.imageThree);

        // set the veg or no-veg indicator
        if (foodItem.isVeg()) {
            viewHolder.imageType.setImageResource(R.mipmap.veg);
        } else {
            viewHolder.imageType.setImageResource(R.mipmap.non_veg);
        }

        // set the chef details
        viewHolder.chefName.setText(foodItem.getChef().getChefName());
        viewHolder.rating.setRating(foodItem.getChef().getRating());

        // set the details of the food item
        viewHolder.amount.setText(String.valueOf(foodItem.getRate()));
        viewHolder.itemName.setText(foodItem.getDishName());
        viewHolder.itemDescription.setText(foodItem.getDescription());
        viewHolder.quantity.setText(String.valueOf(foodItem.getQuantity()));

        // add increment button onClick listener
        viewHolder.incrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodItem foodItem = foodItems.get(position);
                foodItem.setQuantity(foodItem.getQuantity() + 1);
                viewHolder.quantity.setText(String.valueOf(foodItem.getQuantity()));
                amountUpdateListener.updateAmount();
            }
        });

        // add decrement button onClick listener
        viewHolder.decrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodItem foodItem = foodItems.get(position);
                if (foodItem.getQuantity() > 0) { // decrement only if quantity is greater than zero
                    foodItem.setQuantity(foodItem.getQuantity() - 1);
                    viewHolder.quantity.setText(String.valueOf(foodItem.getQuantity()));
                    amountUpdateListener.updateAmount();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodItems == null ? 0 : foodItems.size();
    }

    /**
     * set the array of food items
     *
     * @param foodItems is the array of food items to be displayed
     */
    public void setFoodItems(ArrayList<FoodItem> foodItems) {
        this.foodItems = foodItems;
        notifyDataSetChanged();
    }

    /**
     * this is the view holder of the recycler view for displaying the food items
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageButton incrementBtn, decrementBtn;
        public TextView chefName, itemName, itemDescription, amount, quantity;
        public ImageView chefImage, imageOne, imageTwo, imageThree, imageType;
        public RatingBar rating;

        public ViewHolder(View itemView) {
            super(itemView);
            incrementBtn = (ImageButton) itemView.findViewById(R.id.increment);
            decrementBtn = (ImageButton) itemView.findViewById(R.id.decrement);
            chefName = (TextView) itemView.findViewById(R.id.chef_name);
            chefImage = (ImageView) itemView.findViewById(R.id.chef_image);
            imageOne = (ImageView) itemView.findViewById(R.id.food_image_1);
            imageTwo = (ImageView) itemView.findViewById(R.id.food_image_2);
            imageThree = (ImageView) itemView.findViewById(R.id.food_image_3);
            imageType = (ImageView) itemView.findViewById(R.id.food_type_icon);
            itemName = (TextView) itemView.findViewById(R.id.item_name);
            itemDescription = (TextView) itemView.findViewById(R.id.item_description);
            amount = (TextView) itemView.findViewById(R.id.amount_label);
            quantity = (TextView) itemView.findViewById(R.id.quantity_label);
            rating = (RatingBar) itemView.findViewById(R.id.rating_layout);
        }
    }

    /**
     * this will set the amount update listener to this adapter
     *
     * @param amountUpdateListener the listener
     */
    public void setAmountUpdateListener(CommonListeners.AmountUpdateListener amountUpdateListener) {
        this.amountUpdateListener = amountUpdateListener;
    }
}
