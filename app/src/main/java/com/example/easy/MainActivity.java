package com.example.easy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.easy.utils.ImageLoaderUtil;

public class MainActivity extends AppCompatActivity {
    private ImageView img1,img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageLoaderUtil.getInstance(this);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);

        ImageLoaderUtil.loader(img1,R.drawable.ic_launcher_background);
        ImageLoaderUtil.loaderCircle(img2,R.drawable.ic_launcher_foreground);
    }
}
