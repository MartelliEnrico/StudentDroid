package me.martelli.enrico.studentdroid.drawable;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

import me.martelli.enrico.studentdroid.MyApplication;

/**
 * Created by Enrico on 07/02/14.
 */
public class ActionBarDrawable extends Drawable {

    private int color;

    public ActionBarDrawable(int color) {
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        int height = getBounds().height();
        int width = getBounds().width();

        Resources r = MyApplication.getAppContext().getResources();
        float shadowHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics());

        Paint shadow = new Paint();
        shadow.setARGB(25, 0, 0, 0);
        shadow.setStrokeWidth(0);
        shadow.setStyle(Paint.Style.FILL);

        canvas.drawColor(color);
        canvas.drawRect(0, height - shadowHeight, width, height, shadow);
    }

    @Override
    public void setAlpha(int i) {}

    @Override
    public void setColorFilter(ColorFilter colorFilter) {}

    @Override
    public int getOpacity() {
        return 255;
    }
}
