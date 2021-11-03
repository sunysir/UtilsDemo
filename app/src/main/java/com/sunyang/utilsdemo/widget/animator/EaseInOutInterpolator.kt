package com.sunyang.utilsdemo.widget.animator

/**
 * Created by sunyingzhao on 2020-03-18.
 */
class EaseInOutInterpolator() : EaseCubicInterpolator() {

    constructor(x1: Float, y1: Float, x2: Float, y2: Float) : this() {
        mControlPoint1.x = x1

        mControlPoint1.y = y1

        mControlPoint2.x = x2

        mControlPoint2.y = y2
    }

}