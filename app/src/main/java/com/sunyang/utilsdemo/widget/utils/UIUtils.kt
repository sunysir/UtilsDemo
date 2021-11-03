package com.sunyang.utilsdemo.widget.utils

import android.content.Context
import android.content.res.Resources

/**
 * @author  sunyang.888@bytedance.com
 * @date  2021/10/29
 */
object UIUtils {
    @JvmStatic
    fun getScreenWidth(context: Context?): Int {
        if (context == null) {
            return 0
        }
        val dm = context.resources.displayMetrics
        return dm?.widthPixels ?: 0
    }

    @JvmStatic
    fun getScreenWidth(): Int {
        val dm = Resources.getSystem().displayMetrics
        return dm?.widthPixels ?: 0
    }

    @JvmStatic
    fun getScreenHeight(context: Context?): Int {
        if (context == null) {
            return 0
        }
        val dm = context.resources.displayMetrics
        return dm?.heightPixels ?: 0
    }

    @JvmStatic
    fun getScreenHeight(): Int {
        val dm = Resources.getSystem().displayMetrics
        return dm?.heightPixels ?: 0
    }

    @JvmStatic
    fun dip2Px(context: Context, dipValue: Float): Float {
        val scale = context.resources.displayMetrics.density
        return dipValue * scale + 0.5f
    }

    @JvmStatic
    fun dip2Px(dipValue: Float): Float {
        val scale = Resources.getSystem().displayMetrics.density
        return dipValue * scale + 0.5f
    }

    @JvmStatic
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    @JvmStatic
    fun px2dip(pxValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    @JvmStatic
    fun sp2px(context: Context, spValue: Float): Float {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return spValue * fontScale + 0.5f
    }

    @JvmStatic
    fun sp2px(spValue: Float): Float {
        val fontScale = Resources.getSystem().displayMetrics.scaledDensity
        return spValue * fontScale + 0.5f
    }

    @JvmStatic
    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    @JvmStatic
    fun px2sp(pxValue: Float): Int {
        val fontScale = Resources.getSystem().displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }
}
