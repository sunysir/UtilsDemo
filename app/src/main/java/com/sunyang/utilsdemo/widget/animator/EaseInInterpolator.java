package com.sunyang.utilsdemo.widget.animator;

/**
 * @author YuHuiJun   <yuhuijun@bytedance.com>
 * @since July 02, 2018
 */
public class EaseInInterpolator extends EaseCubicInterpolator {
    public EaseInInterpolator() {
        mControlPoint1.x = 0.75f;

        mControlPoint1.y = 0.00f;

        mControlPoint2.x = 0.65f;

        mControlPoint2.y = 1.00f;
    }
}

