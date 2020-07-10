package com.willpower.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

public class BasicLayout extends LinearLayout {
    @ColorInt
    int color;
    @ColorInt
    int titleColor;
    @ColorInt
    int titleTextColor;
    int titleHeight;
    int radio;
    String titleText;
    boolean hasTitle;
    Paint paint;

    public BasicLayout(Context context) {
        this(context, null);
    }

    public BasicLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BasicLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        setWillNotDraw(false);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(getResources().getDimensionPixelSize(R.dimen.default_title_text_size));
        if (attrs != null) {
            TypedArray td = getContext().obtainStyledAttributes(attrs, R.styleable.BasicLayout);
            color = td.getColor(R.styleable.BasicLayout_color, Color.WHITE);
            radio = td.getDimensionPixelSize(R.styleable.BasicLayout_radio, 0);
            titleTextColor = td.getColor(R.styleable.BasicLayout_titleTextColor, getResources().getColor(R.color.colorPrimary));
            titleColor = td.getColor(R.styleable.BasicLayout_titleColor, color);
            titleHeight = td.getDimensionPixelSize(R.styleable.BasicLayout_titleHeight, getResources().getDimensionPixelSize(R.dimen.default_title_height));
            hasTitle = td.getBoolean(R.styleable.BasicLayout_hasTitle, getResources().getBoolean(R.bool.hasTitle));
            titleText = td.getString(R.styleable.BasicLayout_titleText);
            td.recycle();
        }
        if (hasTitle) {
            setPadding(0, titleHeight, 0, 0);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawContent(canvas);
        if (hasTitle) {
            drawTitle(canvas);
            if (!TextUtils.isEmpty(titleText)) {
                drawTitleText(canvas);
            }
        }
    }

    private void drawContent(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        RectF r = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(r, radio, radio, paint);
    }

    private void drawTitle(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(titleColor);
        RectF r = new RectF(0, 0, getWidth(), titleHeight);
        canvas.drawRoundRect(r, radio, radio, paint);
    }

    private void drawTitleText(Canvas canvas) {
        paint.setColor(titleTextColor);
        // 文字baseline在y轴方向的位置
        float baseLineY = Math.abs(paint.ascent() + paint.descent()) / 2;
        //居下
        canvas.drawText(titleText, getResources().getDimension(R.dimen.default_title_text_margin_left),
                baseLineY + titleHeight / 2, paint);
    }


    public void setColor(int color) {
        this.color = color;
        postInvalidate();
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        postInvalidate();
    }

    public void setTitleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
        postInvalidate();
    }

    public void setTitleHeight(int titleHeight) {
        this.titleHeight = titleHeight;
        postInvalidate();
    }

    public void setRadio(int radio) {
        this.radio = radio;
        postInvalidate();
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
        postInvalidate();
    }

    public void setHasTitle(boolean hasTitle) {
        this.hasTitle = hasTitle;
        postInvalidate();
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
        postInvalidate();
    }
}
