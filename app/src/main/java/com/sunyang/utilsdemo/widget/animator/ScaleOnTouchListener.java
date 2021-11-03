package com.sunyang.utilsdemo.widget.animator;

import androidx.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author   : snow
 * Date     : 2018-04-08 17:07
 * Email    : 491552395@qq.com
 */
public class ScaleOnTouchListener implements View.OnTouchListener {
    private final float pressedScale;
    private final long duration;
    private final View.OnTouchListener delegate;

    public ScaleOnTouchListener(float pressedScale, long duration, @Nullable View.OnTouchListener delegate) {
        this.pressedScale = pressedScale;
        this.duration = duration;
        this.delegate = delegate;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                v.animate().scaleX(pressedScale).scaleY(pressedScale).setDuration(duration).start();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                v.animate().scaleX(1F).scaleY(1F).setDuration(duration).start();
                break;
            default:
                break;
        }

        return delegate != null && delegate.onTouch(v, event);
    }
}
