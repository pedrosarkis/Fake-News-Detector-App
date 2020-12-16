package com.pedro.fakenewsdetector.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import com.pedro.fakenewsdetector.R;
import me.relex.circleindicator.CircleIndicator;

public class TutorialActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        ViewPager viewPager = findViewById(R.id.view_pager);
        CircleIndicator indicator = findViewById(R.id.indicator);
        viewPager.setAdapter(new TutorialPagerAdapter());
        indicator.setViewPager(viewPager);

        findViewById(R.id.btn_complete_tutorial).setOnClickListener(view -> {
            Intent intent = new Intent(this, FakeNewsActivity.class);
            intent.putExtra("tutorial_done", true);
            startActivity(intent);
        });
    }
}