package com.sunyang.utilsdemo.widget.utils;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;

/**
 * {@Link Handler} that guarantees life cycle safety, only supports running on Main Looper
 *  <p>
 * Combined with FragmentActivity and Fragment, it will automatically discard the remaining messages when onDestroy().
 *
 */
@SuppressLint("RestrictedApi")
public class SafeHandler extends Handler implements LifecycleEventObserver {

    private final LifecycleOwner mOwner;

    public SafeHandler(LifecycleOwner owner) {
        super(Looper.getMainLooper());
        mOwner = owner;
        registerObserver();
    }

    public void destroy() {
        removeCallbacksAndMessages(null);
        unregisterObserver();
    }

    @Override
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        switch (event) {
            case ON_DESTROY:
                destroy();
                break;
        }
    }

    private void registerObserver() {
        runOnMain(() -> {
            if (mOwner != null) {
                mOwner.getLifecycle().addObserver(SafeHandler.this);
            }
        });
    }

    private void unregisterObserver() {
        runOnMain(() -> {
            if (mOwner != null) {
                mOwner.getLifecycle().removeObserver(this);
            }
        });
    }

    /**
     * fix lancet error
     *
     * @param task
     */
    private void runOnMain(Runnable task) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            post(task);
        } else {
            task.run();
        }
    }
}
