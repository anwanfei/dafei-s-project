package com.lingling.anwanfei.testan;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = (TextView) findViewById(R.id.textview);
        final BadgeView badgeView = new BadgeView(this, textview);

        //设置提示点的位置
        badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);

        badgeView.setText("+9");
        //显示，不调用此方法，不会显示，同理，也可以隐藏.hide()
        badgeView.show();

        badgeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                badgeView.hide();
                Toast.makeText(MainActivity.this, "消息被干", Toast.LENGTH_SHORT).show();
            }
        });

        //设置背景颜色
        badgeView.setBadgeBackgroundColor(Color.RED);

        //設置透明度
        badgeView.setAlpha(0.9f);

        //設置動畫
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(200);
        badgeView.setAnimation(scaleAnimation);
    }
}
