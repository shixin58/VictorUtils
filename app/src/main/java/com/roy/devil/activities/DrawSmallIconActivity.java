package com.roy.devil.activities;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;

import com.bride.baselib.BaseActivity;
import com.bride.baselib.ResUtils;
import com.roy.devil.R;
import com.roy.devil.specific.DrawUtils;

/**
 * 绘制小icon
 * <p>Created by shixin on 2018/4/1.
 */
public class DrawSmallIconActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_small_icon);
        initView();
    }

    private void initView() {
        View viewFlag = findViewById(R.id.view_flag);
        DrawUtils.drawTag(this, viewFlag, "热卖");

        TextView tvTag = findViewById(R.id.tv_tag);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(ResUtils.getString(R.string.drink));
        DrawUtils.assembleText(spannableStringBuilder, "超值", R.color.black, R.color.red);
        tvTag.setText(spannableStringBuilder);
    }
}
