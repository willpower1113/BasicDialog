package com.willpower.lib;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;

import static com.willpower.lib.Utils.screenWidth;

/**
 * 基础样式的双选Dialog
 */
public class JBasicDialog extends Dialog {

    public static final int MATCH_PARENT = -1;

    private TextView tvContent;

    private Button button1, button2;

    private BasicLayout mBasic;

    private int width, height;

    private JBasicDialog(@NonNull Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_basic, null);
        tvContent = view.findViewById(R.id.tvContent);
        button1 = view.findViewById(R.id.btn1);
        button2 = view.findViewById(R.id.btn2);
        mBasic = view.findViewById(R.id.mBasic);
        setContentView(view);
        setCanceledOnTouchOutside(false);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public static JBasicDialog create(Context context) {
        return new JBasicDialog(context);
    }

    /**
     * @param widthDP 宽度 单位dp
     * @return
     */
    public JBasicDialog width(int widthDP) {
        this.width = widthDP;
        return this;
    }

    public JBasicDialog width(int width, @Dimension int unit) {
        if (unit == Dimension.DP) {
            width = Utils.dp2px(getContext(), width);
        }
        this.width = width;
        return this;
    }

    public JBasicDialog width(float percent) {
        this.width = (int) (screenWidth(getContext()) * percent);
        return this;
    }

    /**
     * @param heightDP 高度 单位dp
     * @return
     */
    public JBasicDialog height(int heightDP) {
        this.height = heightDP;
        return this;
    }

    public JBasicDialog height(int height, @Dimension int unit) {
        if (unit == Dimension.DP) {
            height = Utils.dp2px(getContext(), height);
        }
        this.height = height;
        return this;
    }

    public JBasicDialog height(float percent) {
        this.height = (int) (screenWidth(getContext()) * percent);
        return this;
    }


    public JBasicDialog content(String content) {
        tvContent.setText(content);
        return this;
    }

    public JBasicDialog title(String title) {
        mBasic.setTitleText(title);
        return this;
    }

    public JBasicDialog titleColor(@ColorInt int color) {
        mBasic.setTitleColor(color);
        return this;
    }

    public JBasicDialog titleHeight(int height) {
        mBasic.setTitleHeight(height);
        mBasic.setHasTitle(true);
        return this;
    }

    public JBasicDialog radio(int radio) {
        mBasic.setRadio(radio);
        return this;
    }

    public JBasicDialog hasTitle(boolean hasTitle) {
        mBasic.setHasTitle(hasTitle);
        return this;
    }

    @Override
    public void show() {
        getWindow().setLayout(this.width, this.height);
        super.show();
    }
}
