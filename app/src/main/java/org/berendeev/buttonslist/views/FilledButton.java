package org.berendeev.buttonslist.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;


public class FilledButton extends android.support.v7.widget.AppCompatTextView {

    private float fill;
    private Paint fillPaint;
    private String text;
    private Paint textPaint;

    public FilledButton(Context context) {
        super(context);
        init();
    }

    public FilledButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FilledButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        fillPaint = new Paint();
        fillPaint.setColor(Color.GREEN);
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(getTextSize());
        text = "";
        fill = 0.3f;
        this.setText(text);
        this.setPadding(30, 30, 30, 30);
    }

    @Override protected void onDraw(Canvas canvas) {
        canvas.drawRect(0,0, getWidth()*fill, getHeight(), fillPaint);
        super.onDraw(canvas);
    }

    public float getFill() {
        return fill;
    }

    public void setFill(float fill) {
        this.fill = fill;
    }

    public void setText(String text){
        this.text = text;
    }
}
