package com.example.easy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.easy.utils.AppUtils;
import com.example.easy.utils.LogUtil;

public class MainActivity extends AppCompatActivity {
    private ImageView img1,img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);


        LogUtil.e("打印的测试数据");
        LogUtil.w("打印的测试数据");
        LogUtil.d("打印的测试数据");
        LogUtil.i("打印的测试数据");
        LogUtil.v("打印的测试数据");
        LogUtil.e(AppUtils.getAppName());
    }
}
