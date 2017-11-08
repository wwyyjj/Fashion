package com.five.fashion.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.five.fashion.R;
import com.five.fashion.utils.NetworkUtils;
import com.five.fashion.utils.TSUtils;

public class MainActivity extends AppCompatActivity {


    private boolean available;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        available = NetworkUtils.isAvailable(this);

        findViewById(R.id.main_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TSUtils.showShort(MainActivity.this, available +"");
                NetworkUtils.openWirelessSettings(MainActivity.this);
            }
        });

    }
}
