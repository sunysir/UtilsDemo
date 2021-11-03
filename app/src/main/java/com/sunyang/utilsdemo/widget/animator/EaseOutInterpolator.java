package com.sunyang.utilsdemo.widget.animator;

/**
 * @author YuHuiJun   <yuhuijun@bytedance.com>
 * @since July 02, 2018
 */
public class EaseOutInterpolator extends EaseCubicInterpolator {
    public EaseOutInterpolator() {
        mControlPoint1.x = 0.35f;

        mControlPoint1.y = 0.00f;

        mControlPoint2.x = 0.25f;

        mControlPoint2.y = 1.00f;
    }
}
