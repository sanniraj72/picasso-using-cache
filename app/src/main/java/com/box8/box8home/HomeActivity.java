package com.box8.box8home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int COUNT = 5;

    private JSONArray categoryArray;

    private ProgressBar progressBar;

    private FrameLayout category1;
    private FrameLayout category2;
    private FrameLayout category3;
    private FrameLayout category4;
    private FrameLayout category5;

    private TextView categoryTextView;

    private CarouselView carouselView;

    private String categories = "[\"Fusion Box\"," +
            "\"Curries\"," +
            "\"Biryani\"," +
            "\"Wraps\"," +
            "\"Ice Cream\"]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.home);
        }

        progressBar = findViewById(R.id.loading);
        categoryTextView = findViewById(R.id.category_text);

        // Set CarouselView
        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(COUNT);
        carouselView.setImageListener(imageListener);

        // Set Category
        setCategory();
        hideCategoryData();
    }

    private void hideCategoryData() {
        category1.setVisibility(View.INVISIBLE);
        category2.setVisibility(View.INVISIBLE);
        category3.setVisibility(View.INVISIBLE);
        category4.setVisibility(View.INVISIBLE);
        category5.setVisibility(View.INVISIBLE);
        categoryTextView.setVisibility(View.INVISIBLE);
        carouselView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void showCategoryData() {
        category1.setVisibility(View.VISIBLE);
        category2.setVisibility(View.VISIBLE);
        category3.setVisibility(View.VISIBLE);
        category4.setVisibility(View.VISIBLE);
        category5.setVisibility(View.VISIBLE);
        categoryTextView.setVisibility(View.VISIBLE);
        carouselView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    /**
     * Listener for carouselView
     */
    private ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Box8Application.getInstance().getPicasso().load("https://via.placeholder.com/480x320/99948e/ffffff/")
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            showCategoryData();
                        }

                        @Override
                        public void onError(Exception e) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(HomeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    };

    private void setCategory() {
        category1 = findViewById(R.id.category1);
        category2 = findViewById(R.id.category2);
        category3 = findViewById(R.id.category3);
        category4 = findViewById(R.id.category4);
        category5 = findViewById(R.id.category5);

        category1.setOnClickListener(this);
        category2.setOnClickListener(this);
        category3.setOnClickListener(this);
        category4.setOnClickListener(this);
        category5.setOnClickListener(this);

        ImageView categoryImage1 = category1.findViewById(R.id.category_background);
        ImageView categoryImage2 = category2.findViewById(R.id.category_background);
        ImageView categoryImage3 = category3.findViewById(R.id.category_background);
        ImageView categoryImage4 = category4.findViewById(R.id.category_background);
        ImageView categoryImage5 = category5.findViewById(R.id.category_background);

        TextView categoryName1 = category1.findViewById(R.id.category_name);
        TextView categoryName2 = category2.findViewById(R.id.category_name);
        TextView categoryName3 = category3.findViewById(R.id.category_name);
        TextView categoryName4 = category4.findViewById(R.id.category_name);
        TextView categoryName5 = category5.findViewById(R.id.category_name);

        Box8Application.getInstance().getPicasso()
                .load("https://via.placeholder.com/540x540/7e96bc/ffffff/")
                .into(categoryImage1);
        Box8Application.getInstance().getPicasso()
                .load("https://via.placeholder.com/540x540/81d1af/ffffff/")
                .into(categoryImage2);
        Box8Application.getInstance().getPicasso()
                .load("https://via.placeholder.com/1080x540/e5c982/ffffff/")
                .into(categoryImage3);
        Box8Application.getInstance().getPicasso()
                .load("https://via.placeholder.com/540x540/c2a1e0/ffffff/")
                .into(categoryImage4);
        Box8Application.getInstance().getPicasso()
                .load("https://via.placeholder.com/540x540/efaed3/ffffff/")
                .into(categoryImage5);

        try {
            categoryArray = new JSONArray(categories);
            categoryName1.setText(categoryArray.getString(0));
            categoryName2.setText(categoryArray.getString(1));
            categoryName3.setText(categoryArray.getString(2));
            categoryName4.setText(categoryArray.getString(3));
            categoryName5.setText(categoryArray.getString(4));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.category1:
                showMenuActivity(0);
                break;
            case R.id.category2:
                showMenuActivity(1);
                break;
            case R.id.category3:
                showMenuActivity(2);
                break;
            case R.id.category4:
                showMenuActivity(3);
                break;
            case R.id.category5:
                showMenuActivity(4);
                break;
            default:
                break;
        }
    }

    private void showMenuActivity(int position) {
        Intent intent = new Intent(HomeActivity.this, MenuActivity.class);
        try {
            intent.putExtra("category", categoryArray.getString(position));
            intent.putExtra("position", position);
        } catch (JSONException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        startActivity(intent);
    }
}
